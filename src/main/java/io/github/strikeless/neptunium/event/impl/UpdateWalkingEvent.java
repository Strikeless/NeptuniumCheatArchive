package io.github.strikeless.neptunium.event.impl;

import io.github.strikeless.neptunium.event.api.CancellableEvent;
import io.github.strikeless.neptunium.event.listener.UpdateWalkingListener;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Strikeless
 * @since 06.07.2022
 */
@Getter
@Setter
@AllArgsConstructor
public class UpdateWalkingEvent extends CancellableEvent<UpdateWalkingListener> {

    private double posX, posY, posZ;

    private float yaw, pitch;

    private boolean onGround;

    @Override
    public void consume(final UpdateWalkingListener listener) {
        listener.onUpdateWalking(this);
    }
}
