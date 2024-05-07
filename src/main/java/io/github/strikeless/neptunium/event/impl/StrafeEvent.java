package io.github.strikeless.neptunium.event.impl;

import io.github.strikeless.neptunium.event.api.CancellableEvent;
import io.github.strikeless.neptunium.event.listener.StrafeListener;
import io.github.strikeless.neptunium.util.MoveUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Strikeless
 * @since 07.07.2022
 */
@Getter
@Setter
@AllArgsConstructor
public class StrafeEvent extends CancellableEvent<StrafeListener> {

    private float strafe, forward, friction;

    @Override
    public void consume(final StrafeListener listener) {
        listener.onStrafe(this);
    }

    public void strafe() {
        strafe(this.friction);
    }

    public void strafe(final float speed) {
        // Stop moving so that instead of adding to the current motion, we effectively set the motion of the player
        MoveUtil.stopHorizontal();

        this.friction = speed;
    }
}
