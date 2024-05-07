package io.github.strikeless.neptunium.feature.module.impl.movement.flight;

import io.github.strikeless.neptunium.feature.module.api.Module;
import io.github.strikeless.neptunium.feature.module.api.ModuleCategory;
import io.github.strikeless.neptunium.feature.module.api.ModuleInfo;
import io.github.strikeless.neptunium.setting.impl.ModeSetting;

/**
 * @author Strikeless
 * @since 06.07.2022
 */
@ModuleInfo(name = "Flight", description = "Allows you to fly", category = ModuleCategory.MOVEMENT)
public final class FlightModule extends Module {

    private final ModeSetting modeSetting = new ModeSetting(this, "Mode", new VanillaFlightMode());
}
