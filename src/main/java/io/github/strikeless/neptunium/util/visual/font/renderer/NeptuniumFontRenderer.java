package io.github.strikeless.neptunium.util.visual.font.renderer;

import java.util.Optional;

public abstract class NeptuniumFontRenderer {

    private float cachedFontHeight = -1.0F;

    public abstract float getWidth(final String text);

    public abstract float getHeight(final String text);

    public float getMaxHeight() {
        if (cachedFontHeight == -1.0F) {
            cachedFontHeight = getHeight("qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890[](){}!?#");
        }

        return cachedFontHeight;
    }

    public abstract float drawString(final String text, final float x, final float y);

    public abstract float drawStringShadowed(final String text, final float x, final float y);

    public float drawStringCentered(final String text, final float x, final float y) {
        return drawString(text, x + getWidth(text) / 2.0F, y);
    }

    public float drawStringShadowedCentered(final String text, final float x, final float y) {
        return drawStringShadowed(text, x + getWidth(text) / 2.0F, y);
    }
}
