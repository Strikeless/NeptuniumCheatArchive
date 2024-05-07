package io.github.strikeless.neptunium.feature.module.impl.movement.flight;

import io.github.strikeless.neptunium.event.impl.UpdateEvent;
import io.github.strikeless.neptunium.event.impl.UpdateWalkingEvent;
import io.github.strikeless.neptunium.event.listener.UpdateListener;
import io.github.strikeless.neptunium.event.listener.UpdateWalkingListener;
import io.github.strikeless.neptunium.feature.module.api.Mode;
import io.github.strikeless.neptunium.setting.impl.BooleanSetting;
import io.github.strikeless.neptunium.setting.impl.NumberSetting;
import io.github.strikeless.neptunium.util.MoveUtil;

/**
 * @author Strikeless
 * @since 07.07.2022
 */
public class VanillaFlightMode extends Mode implements UpdateListener, UpdateWalkingListener {

    private final NumberSetting speedSetting = new NumberSetting(this, "Speed", 1.0D, 0.0D, 10.0D);

    private final BooleanSetting changePacketGroundSetting = new BooleanSetting(this, "Change Packet Ground", true);
    private final BooleanSetting packetGroundSetting = new BooleanSetting(changePacketGroundSetting, "Packet Ground", true);

    public VanillaFlightMode() {
        super("Vanilla");
    }

    @Override
    public void onUpdate(final UpdateEvent event) {
        MoveUtil.strafe(speedSetting.getValue().doubleValue());
        MoveUtil.verticalStrafe(speedSetting.getValue().doubleValue());
    }

    @Override
    public void onUpdateWalking(final UpdateWalkingEvent event) {
        if (changePacketGroundSetting.getValue()) {
            event.setOnGround(packetGroundSetting.getValue());
        }
    }
}
