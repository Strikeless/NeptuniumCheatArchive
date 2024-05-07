package io.github.strikeless.neptunium.util.visual.opengl.shader.system.uniform;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Shader system ported to Minecraft from my LWJGL3 game engine.
 * Noticeably simplified from my original one (many methods and attribute system removed)
 *
 * @author Strikeless
 * @since 28.11.2021
 */
@Getter
@RequiredArgsConstructor
public enum ShaderUniform {
    TIME("time", 0),
    RESOLUTION("resolution", 1);

    private final String name;

    private final int index;
}
