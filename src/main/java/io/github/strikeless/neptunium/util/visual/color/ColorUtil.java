package io.github.strikeless.neptunium.util.visual.color;

import lombok.experimental.UtilityClass;

import java.awt.*;

@UtilityClass
public class ColorUtil {

    public Color TRANSPARENT = new Color(0, 0, 0, 0);

    public Color ACCENT = new Color(73, 65, 217);
    public Color ACCENT_DARKER = new Color(55, 46, 153);
    public Color ACCENT_DARKEST = new Color(41, 34, 115);

    public Color BACKGROUND = new Color(38, 35, 41);
    public Color BACKGROUND_DARKER = new Color(27, 24, 30);
    public Color BACKGROUND_DARKEST = new Color(15, 12, 18);

    public Color FOREGROUND = new Color(241, 241, 241);
    public Color FOREGROUND_DARKER = new Color(168, 168, 168);
    public Color FOREGROUND_DARKEST = new Color(141, 141, 141);

    public Color alpha(final Color color, final int alpha) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
    }

    public String hexFromColor(final Color color) {
        return String.format("#%06X", color.getRGB() & 0x00FFFFFF);
    }

    public Color colorFromHex(final String hex) {
        return Color.decode(hex.replace("#", ""));
    }
}
