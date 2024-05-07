package io.github.strikeless.neptunium.feature.api;

import io.github.strikeless.neptunium.util.interfaces.Described;
import io.github.strikeless.neptunium.util.interfaces.Initializable;
import io.github.strikeless.neptunium.util.interfaces.InstanceAccessor;
import io.github.strikeless.neptunium.util.interfaces.Named;

/**
 * @author Strikeless
 * @since 05.07.2022
 */
public interface Feature extends InstanceAccessor, Named, Described, Initializable {

    @Override
    default void init() {
    }

    @Override
    default void uninit() {
    }
}
