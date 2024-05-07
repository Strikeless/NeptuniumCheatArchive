package io.github.strikeless.neptunium.feature.module.impl.movement.speed;

import io.github.strikeless.neptunium.event.impl.HandleJumpingEvent;
import io.github.strikeless.neptunium.event.impl.StrafeEvent;
import io.github.strikeless.neptunium.event.listener.HandleJumpingListener;
import io.github.strikeless.neptunium.event.listener.StrafeListener;
import io.github.strikeless.neptunium.feature.module.api.Mode;
import io.github.strikeless.neptunium.setting.impl.BooleanSetting;
import io.github.strikeless.neptunium.setting.impl.NumberSetting;

/**
 * @author Strikeless
 * @since 15.07.2022
 */
public class VanillaSpeedMode extends Mode implements StrafeListener, HandleJumpingListener {

    private final NumberSetting speedSetting = new NumberSetting(this, "Speed", 0.5D, 0.0D, 5.0D);

    private final BooleanSetting jumpSetting = new BooleanSetting(this, "Jump", true);

    private final BooleanSetting strafeSetting = new BooleanSetting(this, "Strafe", true);

    public VanillaSpeedMode() {
        super("Vanilla");
    }

    @Override
    public void onStrafe(final StrafeEvent event) {
        final float speed = speedSetting.getValue().floatValue();

        if (strafeSetting.getValue()) event.strafe(speed);
        else event.setFriction(speed);
    }

    @Override
    public void onHandleJumping(final HandleJumpingEvent event) {
        if (this.jumpSetting.getValue() && mc.thePlayer.onGround) {
            event.jump();
        }
    }
}
