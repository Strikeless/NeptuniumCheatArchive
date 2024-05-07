package io.github.strikeless.neptunium.feature.module.impl.movement.speed;

import io.github.strikeless.neptunium.feature.module.api.Module;
import io.github.strikeless.neptunium.feature.module.api.ModuleCategory;
import io.github.strikeless.neptunium.feature.module.api.ModuleInfo;
import io.github.strikeless.neptunium.setting.impl.ModeSetting;

/**
 * @author Strikeless
 * @since 07.07.2022
 */
@ModuleInfo(name = "Speed", description = "Makes you move faster", category = ModuleCategory.MOVEMENT)
public class SpeedModule extends Module {

    private final ModeSetting modeSetting = new ModeSetting(this, "Mode", new VanillaSpeedMode());
}
