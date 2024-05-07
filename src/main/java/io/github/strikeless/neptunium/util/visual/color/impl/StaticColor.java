package io.github.strikeless.neptunium.util.visual.color.impl;

import io.github.strikeless.neptunium.util.visual.RenderUtil;
import io.github.strikeless.neptunium.util.visual.color.NeptuniumColor;
import lombok.RequiredArgsConstructor;
import net.minecraft.client.renderer.GlStateManager;

import java.awt.*;

@RequiredArgsConstructor
public class StaticColor extends NeptuniumColor {

    private final float red, green, blue, alpha;

    private GlStateManager.Color oldColor;

    public StaticColor(final Color awtColor) {
        this(awtColor.getRed() / 255.0F, awtColor.getGreen() / 255.0F, awtColor.getBlue() / 255.0F, awtColor.getAlpha() / 255.0F);
    }

    @Override
    public void enableColor() {
        this.oldColor = RenderUtil.getGlStateManagerColor();
        RenderUtil.color(this.red, this.green, this.blue, this.alpha);
    }

    @Override
    public void disableColor() {
        if (this.oldColor != null) {
            RenderUtil.color(this.oldColor);
        }
    }
}
