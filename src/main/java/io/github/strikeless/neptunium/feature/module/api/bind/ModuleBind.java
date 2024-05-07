package io.github.strikeless.neptunium.feature.module.api.bind;

import io.github.strikeless.neptunium.feature.module.api.Module;
import lombok.Data;

/**
 * @author Strikeless
 * @since 07.07.2022
 */
// mr. over-engineer back at it again
@Data
public abstract class ModuleBind {

    private final Module module;

    private final int keyCode;

    public abstract void handleState(final boolean state);
}
