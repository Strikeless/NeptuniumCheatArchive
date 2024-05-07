package io.github.strikeless.neptunium.util.vector.impl;

import io.github.strikeless.neptunium.util.vector.Vector3;

/**
 * @author Strikeless
 * @since 07.07.2022
 */
public class Vector3D extends Vector3<Double> {

    public static final Vector3D IDENTITY = new Vector3D(0.0D, 0.0D, 0.0D);

    public Vector3D(final double x, final double y, final double z) {
        super(x, y, z);
    }
}
