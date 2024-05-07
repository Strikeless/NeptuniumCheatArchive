package io.github.strikeless.neptunium.util.visual;

import io.github.strikeless.neptunium.util.interfaces.InstanceAccessor;
import lombok.experimental.UtilityClass;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.optifine.render.GlBlendState;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

/**
 * @author Strikeless
 * @since 15.07.2022
 */
@UtilityClass
public class RenderUtil implements InstanceAccessor {

    // Trying to use GlStateManager wherever possible as that is what the game normally uses,
    // thus it's probably wise to let it do its thing.

    // TODO: Only create a new ScaledResolution upon a ResizeEvent
    public ScaledResolution getScaledResolution() {
        return new ScaledResolution(mc);
    }

    public boolean isEnabled(final int cap) {
        return GL11.glIsEnabled(cap);
    }

    public void enable(final int cap) {
        GL11.glEnable(cap);
    }

    public void disable(final int cap) {
        GL11.glDisable(cap);
    }

    public void begin(final int mode) {
        GlStateManager.glBegin(mode);
    }

    public void end() {
        GlStateManager.glEnd();
    }

    public GlBlendState copyGLBlendState() {
        final GlBlendState blendState = new GlBlendState();
        GlStateManager.getBlendState(blendState);
        return blendState;
    }

    public void restoreGLBlendState(final GlBlendState blendState) {
        GlStateManager.setBlendState(blendState);
    }

    public void beginBlend(final int sourceFactor, final int destinationFactor) {
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(sourceFactor, destinationFactor);
    }

    public void endBlend() {
        GlStateManager.disableBlend();
    }

    public void beginDraw() {
        disable(GL_CULL_FACE);
        disable(GL_TEXTURE_2D);
        beginBlend(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }

    public void endDraw() {
        endBlend();
        enable(GL_TEXTURE_2D);
        enable(GL_CULL_FACE);
    }

    public void pushMatrix() {
        GlStateManager.pushMatrix();
    }

    public void popMatrix() {
        GlStateManager.popMatrix();
    }

    public void scale(final float scale) {
        scale(scale, scale, scale);
    }

    public void scale(final float scaleX, final float scaleY, final float scaleZ) {
        GlStateManager.scale(scaleX, scaleY, scaleZ);
    }

    public void vertex(final float x, final float y) {
        GL11.glVertex2f(x, y);
    }

    public void vertex(final float x, final float y, final float z) {
        GL11.glVertex3f(x, y, z);
    }

    public void resetColor() {
        color(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public GlStateManager.Color getGlStateManagerColor() {
        return GlStateManager.colorState;
    }

    public Color getAWTColor() {
        final GlStateManager.Color glsmColor = getGlStateManagerColor();
        return new Color(glsmColor.red, glsmColor.green, glsmColor.blue, glsmColor.alpha);
    }

    public int getRGBColor() {
        // Could certainly be optimized but meh doesn't affect anything
        return getAWTColor().getRGB();
    }

    public GlStateManager.Color color(final Color color) {
        final GlStateManager.Color oldColor = GlStateManager.colorState;

        color(
                color.getRed() / 255.0F,
                color.getGreen() / 255.0F,
                color.getBlue() / 255.0F,
                color.getAlpha() / 255.0F
        );

        return oldColor;
    }

    public GlStateManager.Color color(final GlStateManager.Color color) {
        final GlStateManager.Color oldColor = GlStateManager.colorState;
        GlStateManager.color(color.red, color.green, color.blue, color.alpha);
        return oldColor;
    }

    public GlStateManager.Color color(final float red, final float green, final float blue, final float alpha) {
        final GlStateManager.Color oldColor = GlStateManager.colorState;
        GlStateManager.color(red, green, blue, alpha);
        return oldColor;
    }

    public void line(final float x, final float y, final float width, final float height) {
        beginDraw();
        begin(GL_LINES);

        vertex(x, y);
        vertex(x + width, y + height);

        end();
        endDraw();
    }

    public void rectangle(final float x, final float y, final float width, final float height) {
        beginDraw();
        begin(GL_QUADS);

        vertex(x, y);
        vertex(x, y + height);
        vertex(x + width, y + height);
        vertex(x + width, y);

        end();
        endDraw();
    }
}
