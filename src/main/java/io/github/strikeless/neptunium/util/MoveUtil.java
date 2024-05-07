package io.github.strikeless.neptunium.util;

import io.github.strikeless.neptunium.util.interfaces.InstanceAccessor;
import io.github.strikeless.neptunium.util.vector.impl.Vector2D;
import lombok.experimental.UtilityClass;
import net.minecraft.util.MathHelper;

/**
 * @author Strikeless
 * @since 07.07.2022
 */
@UtilityClass
public class MoveUtil implements InstanceAccessor {

    public boolean isMovingHorizontally() {
        return mc.thePlayer.motionX != 0.0F || mc.thePlayer.motionZ != 0.0F;
    }

    public boolean hasHorizontalInput() {
        return mc.thePlayer.movementInput.moveForward != 0.0F || mc.thePlayer.movementInput.moveStrafe != 0.0F;
    }

    public double getHorizontalSpeed() {
        return Math.hypot(mc.thePlayer.motionX, mc.thePlayer.motionZ);
    }

    public void stopHorizontal() {
        mc.thePlayer.motionX = 0.0D;
        mc.thePlayer.motionZ = 0.0D;
    }

    public void strafe() {
        strafe(getHorizontalSpeed());
    }

    public void strafe(final double speed) {
        final Vector2D motion = getStrafe(speed);
        mc.thePlayer.motionX = motion.getX();
        mc.thePlayer.motionZ = motion.getY();
    }

    public Vector2D getStrafe(final double speed) {
        if (!hasHorizontalInput()) return Vector2D.IDENTITY;

        final float direction = (float) getStrafeRadians();
        return new Vector2D(
                -MathHelper.sin(direction) * speed,
                MathHelper.cos(direction) * speed
        );
    }

    public void verticalStrafe(final double speed) {
        mc.thePlayer.motionY = getVerticalStrafe(speed);
    }

    // TODO: better name for this
    public double getVerticalStrafe(final double speed) {
        return (
                (mc.thePlayer.movementInput.jump ? 1.0D : 0.0D)
                        - (mc.thePlayer.movementInput.sneak ? 1.0D : 0.0D)
        ) * speed;
    }

    public double getStrafeRadians() {
        final float strafe = inflateInput(mc.thePlayer.movementInput.moveStrafe);
        final float forward = inflateInput(mc.thePlayer.movementInput.moveForward);

        double yaw = mc.thePlayer.rotationYaw;

        if (strafe != 0.0D) {
            // -1.5 when moving backwards, -0.5 when moving forward
            final double forwardMultiplier = -1.0D + forward * 0.5D;

            // Add ±90 degrees depending on strafe input multiplied by
            // our forwardMultiplier in order to get diagonal strafing
            yaw += strafe * 90.0D * forwardMultiplier;
        } else if (forward < 0.0D) {
            // Add 180 degrees if moving backwards
            yaw += 180.0D;
        }

        return Math.toRadians(yaw);
    }

    private float inflateInput(final float input) {
        if (input < 0.0F) return -1.0F;
        if (input > 0.0F) return 1.0F;
        return 0.0F;
    }
}
