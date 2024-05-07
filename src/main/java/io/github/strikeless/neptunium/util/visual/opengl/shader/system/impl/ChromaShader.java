package io.github.strikeless.neptunium.util.visual.opengl.shader.system.impl;

import io.github.strikeless.neptunium.util.visual.RenderUtil;
import io.github.strikeless.neptunium.util.visual.opengl.shader.system.FBOShader;
import io.github.strikeless.neptunium.util.visual.opengl.shader.system.uniform.ShaderUniform;
import net.minecraft.client.Minecraft;
import org.lwjgl.util.vector.Vector2f;

public class ChromaShader extends FBOShader {

    private final long initialTime = System.currentTimeMillis();

    public ChromaShader() {
        super("simple.vert", "chroma.frag", ShaderUniform.TIME, ShaderUniform.RESOLUTION);
    }

    @Override
    public void update() {
        enable();
        getUniform(ShaderUniform.TIME).set((System.currentTimeMillis() - initialTime) / 1000.0F);
        getUniform(ShaderUniform.RESOLUTION).set(new Vector2f(RenderUtil.getScaledResolution().getScaledWidth(), RenderUtil.getScaledResolution().getScaledHeight()));
        disable();
    }
}
