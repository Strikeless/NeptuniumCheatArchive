package io.github.strikeless.neptunium.feature.module.api.bind.impl;

import io.github.strikeless.neptunium.feature.module.api.Module;
import io.github.strikeless.neptunium.feature.module.api.bind.ModuleBind;

/**
 * @author Strikeless
 * @since 07.07.2022
 */
public class HoldBind extends ModuleBind {

    public HoldBind(final Module module, final int keyCode) {
        super(module, keyCode);
    }

    @Override
    public void handleState(final boolean state) {
        super.getModule().setEnabled(state);
    }
}
