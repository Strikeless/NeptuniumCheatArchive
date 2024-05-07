package io.github.strikeless.neptunium.util.vector.impl;

import io.github.strikeless.neptunium.util.vector.Vector2;

/**
 * @author Strikeless
 * @since 07.07.2022
 */
public class Vector2D extends Vector2<Double> {

    public static final Vector2D IDENTITY = new Vector2D(0.0D, 0.0D);

    public Vector2D(final double x, final double y) {
        super(x, y);
    }
}
