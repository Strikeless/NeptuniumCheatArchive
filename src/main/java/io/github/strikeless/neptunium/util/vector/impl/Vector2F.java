package io.github.strikeless.neptunium.util.vector.impl;

import io.github.strikeless.neptunium.util.vector.Vector2;

/**
 * @author Strikeless
 * @since 07.07.2022
 */
public class Vector2F extends Vector2<Float> {

    public static final Vector2F IDENTITY = new Vector2F(0.0F, 0.0F);

    public Vector2F(final float x, final float y) {
        super(x, y);
    }
}
