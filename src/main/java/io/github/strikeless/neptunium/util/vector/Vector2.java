package io.github.strikeless.neptunium.util.vector;

import lombok.Data;

/**
 * @author Strikeless
 * @since 07.07.2022
 */
@Data
public class Vector2<T extends Number> {

    private final T x, y;
}
