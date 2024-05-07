package io.github.strikeless.neptunium.util;

import lombok.experimental.UtilityClass;

/**
 * @author Strikeless
 * @since 07.07.2022
 */
@UtilityClass
public class MathUtil {

    public int clamp(final int value, final int min, final int max) {
        return Math.min(Math.max(value, min), max);
    }

    public long clamp(final long value, final long min, final long max) {
        return Math.min(Math.max(value, min), max);
    }

    public float clamp(final float value, final float min, final float max) {
        return Math.min(Math.max(value, min), max);
    }

    public double clamp(final double value, final double min, final double max) {
        return Math.min(Math.max(value, min), max);
    }

    public int lerp(final int a, final int b, final float f) {
        return Math.round(a + f * (b - a));
    }

    public float lerp(final float a, final float b, final float f) {
        return a + f * (b - a);
    }

    public double lerp(final double a, final double b, final float f) {
        return a + f * (b - a);
    }
}
