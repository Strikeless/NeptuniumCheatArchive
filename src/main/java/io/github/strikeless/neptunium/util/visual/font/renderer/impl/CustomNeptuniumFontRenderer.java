package io.github.strikeless.neptunium.util.visual.font.renderer.impl;

import io.github.strikeless.neptunium.event.impl.RenderTextEvent;
import io.github.strikeless.neptunium.util.ResourceUtil;
import io.github.strikeless.neptunium.util.interfaces.InstanceAccessor;
import io.github.strikeless.neptunium.util.visual.RenderUtil;
import io.github.strikeless.neptunium.util.visual.font.FontSize;
import io.github.strikeless.neptunium.util.visual.font.renderer.NeptuniumFontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.optifine.render.GlBlendState;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Pattern;

import static org.lwjgl.opengl.GL11.*;

/**
 * @author Strikeless
 * @since 17.07.2022
 */
// yeah i use slick util, the fuck are you gonna do about it?
public class CustomNeptuniumFontRenderer extends NeptuniumFontRenderer implements InstanceAccessor {

    private static final Pattern NEWLINE_PATTERN = Pattern.compile("(\r|\n|\r\n|\n\r)");

    private static final Pattern COLOR_PATTERN = Pattern.compile("§[0123456789eabdfklmnor]");

    private static final int[] COLORS = new int[32];

    static {
        for (int i = 0; i < COLORS.length; ++i) {
            final int shadow = (i >> 3 & 1) * 85;
            int red = (i >> 2 & 1) * 170 + shadow;
            int green = (i >> 1 & 1) * 170 + shadow;
            int blue = (i & 1) * 170 + shadow;

            if (i == 6) {
                red += 85;
            }

            if (i >= 16) {
                red /= 4;
                green /= 4;
                blue /= 4;
            }

            COLORS[i] = (red & 255) << 16 | (green & 255) << 8 | blue & 255;
        }
    }

    private final UnicodeFont font;

    public CustomNeptuniumFontRenderer(final String name, final FontSize size) throws FontFormatException, IOException, SlickException {
        this(createAwtFont(ResourceUtil.getNeptuniumAsset("fonts/" + name)), size.getSize());
    }

    public CustomNeptuniumFontRenderer(final Font font) throws SlickException {
        this(font, font.getSize2D());
    }

    private static Font createAwtFont(final InputStream stream) throws IOException, FontFormatException {
        final Font font = Font.createFont(Font.TRUETYPE_FONT, stream);

        // Register the font since by default it's not going to exist in many systems
        GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);

        return font;
    }

    private static String stripCodes(final String str) {
        return COLOR_PATTERN.matcher(str).replaceAll("");
    }

    public CustomNeptuniumFontRenderer(final Font font, final float size) throws SlickException {
        final Font awtFont = font.deriveFont(size);
        this.font = new UnicodeFont(awtFont);

        this.font.addAsciiGlyphs();
        this.font.getEffects().add(new ColorEffect(Color.WHITE));
        this.font.loadGlyphs();
    }

    @Override
    public float getWidth(final String text) {
        return font.getWidth(stripCodes(text)) / (float) RenderUtil.getScaledResolution().getScaleFactor();
    }

    @Override
    public float getHeight(final String text) {
        return font.getHeight(stripCodes(text)) / (float) RenderUtil.getScaledResolution().getScaleFactor();
    }

    @Override
    public float drawString(final String text, final float x, final float y) {
        return this.drawString(text, x, y, true);
    }

    @Override
    public float drawStringShadowed(final String text, final float x, final float y) {
        // This shouldn't interfere with any shader colors since they should multiply by gl color.
        final GlStateManager.Color oldColor = RenderUtil.color(0.0F, 0.0F, 0.0F, 0.0F);
        this.drawString(text, x + 0.5F, y + 0.5F, false);
        RenderUtil.color(oldColor);

        return this.drawString(text, x, y);
    }

    private float drawString(final String text, float x, float y, final boolean allowCodes) {
        RenderUtil.pushMatrix();

        final float scaleFactor = RenderUtil.getScaledResolution().getScaleFactor();
        RenderUtil.scale(1.0F / scaleFactor);
        x *= scaleFactor;
        y *= scaleFactor;

        // Set the proper gl caps and blending mode
        final boolean wasLightingEnabled = RenderUtil.isEnabled(GL_LIGHTING);
        final boolean wasTexture2DEnabled = RenderUtil.isEnabled(GL_TEXTURE_2D);
        final boolean wasAlphaTestEnabled = RenderUtil.isEnabled(GL_ALPHA_TEST);
        final GlBlendState oldGLBlendState = RenderUtil.copyGLBlendState();
        if (wasLightingEnabled) GlStateManager.disableLighting();
        if (!wasTexture2DEnabled) GlStateManager.enableTexture2D();
        if (!wasAlphaTestEnabled) GlStateManager.enableAlpha();
        RenderUtil.beginBlend(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        org.newdawn.slick.Color slickColor = new org.newdawn.slick.Color(RenderUtil.getRGBColor());
        final org.newdawn.slick.Color originalSlickColor = slickColor;

        final float originalX = x;
        for (final String line : NEWLINE_PATTERN.split(text)) {
            x = originalX;

            // Make a new StringBuilder, this is used because of our way of handling color codes.
            final StringBuilder bulkBuilder = new StringBuilder();

            for (int i = 0; i < line.length(); ++i) {
                boolean forceBulkDraw = false;

                handleChar:
                {
                    final char chr = line.charAt(i);

                    parseCodes:
                    {
                        // If the code invoker character is found and there is a following character on the line...
                        if (allowCodes && chr == '§' && i < line.length() - 2) {
                            // ...then get the possible code, and it's index in the codes we can handle
                            final char code = line.charAt(i + 1);
                            final int codeIndex = "0123456789abcdefr".indexOf(code);

                            // If this isn't a code (or a code that we cannot handle), continue drawing it as usual.
                            if (codeIndex < 0) break parseCodes;

                            // Set the color accordingly or reset it respectively to the code.
                            if (codeIndex < 16) slickColor = new org.newdawn.slick.Color(COLORS[codeIndex]);
                            else slickColor = originalSlickColor;

                            // Move the index once to the right, force a bulk draw, and skip to the next character
                            // We actually need to move the index twice, but we're effectively doing that by skipping.
                            i += 1;
                            forceBulkDraw = true;
                            break handleChar;
                        }
                    }

                    // Add the character to our bulk builder.
                    bulkBuilder.append(chr);
                }

                // Force a bulk draw if we're at the end of the line (or if it has already been forced)
                forceBulkDraw = forceBulkDraw || i >= line.length() - 1;

                // Draw the bulk if a bulk draw was forced
                if (forceBulkDraw) {
                    final String str = bulkBuilder.toString();
                    bulkBuilder.setLength(0);

                    x += this.drawStringSlick(str, x, y, slickColor);
                }
            }

            // Add to our y-position so that the next line isn't on the same y-position
            y += this.getHeight(line);
        }

        // Restore gl caps
        RenderUtil.restoreGLBlendState(oldGLBlendState);
        if (wasLightingEnabled) GlStateManager.enableLighting();
        if (!wasTexture2DEnabled) GlStateManager.disableTexture2D();
        if (!wasAlphaTestEnabled) GlStateManager.disableAlpha();

        // Pop our matrix
        RenderUtil.popMatrix();

        return (x - originalX) / scaleFactor;
    }

    private float drawStringSlick(String text, float x, float y, final org.newdawn.slick.Color slickColor) {
        final RenderTextEvent renderTextEvent = new RenderTextEvent(text, x, y);
        renderTextEvent.dispatch();

        text = renderTextEvent.getText();
        x = Math.round(renderTextEvent.getX());
        y = Math.round(renderTextEvent.getY());

        this.font.drawString(x, y, text, slickColor);
        return this.getWidth(text);
    }
}
