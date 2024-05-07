package io.github.strikeless.neptunium.util.vector.impl;

import io.github.strikeless.neptunium.util.vector.Vector3;

/**
 * @author Strikeless
 * @since 07.07.2022
 */
public class Vector3F extends Vector3<Float> {

    public static final Vector3F IDENTITY = new Vector3F(0.0F, 0.0F, 0.0F);

    public Vector3F(final float x, final float y, final float z) {
        super(x, y, z);
    }
}
