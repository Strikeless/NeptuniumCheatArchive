package io.github.strikeless.neptunium.util.visual.opengl.shader.system.uniform;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import static org.lwjgl.opengl.GL20.*;

/**
 * Shader system ported to Minecraft from my LWJGL3 game engine.
 * Noticeably simplified from my original one (many methods and attribute system removed)
 *
 * @author Strikeless
 * @since 28.11.2021
 */
@RequiredArgsConstructor
public class ShaderUniformInstance {

    @Getter
    private final ShaderUniform uniform;

    @Getter
    private final int location;

    public void set(final boolean value) {
        glUniform1f(this.location, value ? 1.0F : 0.0F);
    }

    public void set(final float value) {
        glUniform1f(this.location, value);
    }

    public void set(final Vector2f value) {
        glUniform2f(this.location, value.x, value.y);
    }

    public void set(final Vector3f value) {
        glUniform3f(this.location, value.x, value.y, value.z);
    }

    /*public void set(final Matrix4f value) {
        try (final MemoryStack stack = MemoryStack.stackPush()) {
            final FloatBuffer fb = stack.mallocFloat(16);
            value.get(fb);
            glUniformMatrix4fv(location, false, fb);
        }
    }*/
}
