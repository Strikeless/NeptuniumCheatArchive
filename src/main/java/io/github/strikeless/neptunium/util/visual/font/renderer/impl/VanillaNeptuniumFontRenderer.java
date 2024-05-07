package io.github.strikeless.neptunium.util.visual.font.renderer.impl;

import io.github.strikeless.neptunium.util.interfaces.InstanceAccessor;
import io.github.strikeless.neptunium.util.visual.RenderUtil;
import io.github.strikeless.neptunium.util.visual.font.FontSize;
import io.github.strikeless.neptunium.util.visual.font.renderer.NeptuniumFontRenderer;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.awt.*;

@Getter
@AllArgsConstructor
public class VanillaNeptuniumFontRenderer extends NeptuniumFontRenderer implements InstanceAccessor {

    private final FontSize size;

    @Override
    public float getWidth(final String text) {
        return 0;
    }

    @Override
    public float getHeight(final String text) {
        return 0;
    }

    @Override
    public float drawString(final String text, float x, float y) {
        final float multiplier = beginVanillaScale();
        x /= multiplier;
        y /= multiplier;

        final float width = mc.fontRendererObj.drawString(text, (int) x, (int) y, Color.WHITE.getRGB()) - x;
        endVanillaScale();

        return width;
    }

    @Override
    public float drawStringShadowed(final String text, float x, float y) {
        final float multiplier = beginVanillaScale();
        x /= multiplier;
        y /= multiplier;

        final float width = mc.fontRendererObj.drawStringWithShadow(text, (int) x, (int) y, Color.WHITE.getRGB()) - x;
        endVanillaScale();

        return width;
    }

    private float beginVanillaScale() {
        final float multiplier = size.getVanillaMultiplier();

        RenderUtil.pushMatrix();
        RenderUtil.scale(multiplier);

        return multiplier;
    }

    private void endVanillaScale() {
        RenderUtil.popMatrix();
    }
}
