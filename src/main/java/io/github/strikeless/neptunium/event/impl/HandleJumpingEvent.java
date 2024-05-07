package io.github.strikeless.neptunium.event.impl;

import io.github.strikeless.neptunium.event.api.CancellableEvent;
import io.github.strikeless.neptunium.event.listener.HandleJumpingListener;
import io.github.strikeless.neptunium.util.interfaces.InstanceAccessor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Strikeless
 * @since 15.07.2022
 */
@Getter
@Setter
@AllArgsConstructor
public class HandleJumpingEvent extends CancellableEvent<HandleJumpingListener> implements InstanceAccessor {

    /**
     * Whether the player is trying to jump by input
     */
    private boolean jumping;

    /**
     * Set to 10 when the player jumps, decreased every tick, set to 0 when no jump input is given.
     */
    private int jumpTicks;

    /**
     * Whether a jump was forced by a listener of the event. By default, this is always false.
     * Setting this to true has the same effect as calling the jump method in this event.
     *
     * Note that cancelling this event also cancels this forced jump, thus you should set jumping to false
     * instead of cancelling the event, unless this kind of behaviour is your intent.
     */
    private boolean forcedJump;

    @Override
    public void consume(final HandleJumpingListener listener) {
        listener.onHandleJumping(this);
    }

    /**
     * Forces a jump without checking onGround or anything of that kind.
     * Setting forcedJump to true has the same effect as calling this method.
     *
     * Note that cancelling this event also cancels this forced jump, thus you should set jumping to false
     * instead of cancelling the event, unless this kind of behaviour is your intent.
     */
    public void jump() {
        this.forcedJump = true;
    }
}
