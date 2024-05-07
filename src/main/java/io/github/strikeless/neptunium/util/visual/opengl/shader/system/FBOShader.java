package io.github.strikeless.neptunium.util.visual.opengl.shader.system;

import io.github.strikeless.neptunium.util.interfaces.InstanceAccessor;
import io.github.strikeless.neptunium.util.visual.RenderUtil;
import io.github.strikeless.neptunium.util.visual.opengl.Stencil;
import io.github.strikeless.neptunium.util.visual.opengl.shader.system.uniform.ShaderUniform;

public abstract class FBOShader extends Shader implements InstanceAccessor {

    private final Stencil stencil = new Stencil();

    private boolean used;

    public FBOShader(final String vertexName, final String fragmentName, final ShaderUniform... uniforms) {
        super(vertexName, fragmentName, uniforms);
    }

    public void enableWrite() {
        stencil.beginWrite();
        used = true;
    }

    public void disableWrite() {
        stencil.endWrite();
    }

    public void enableRead() {
        stencil.beginRead();
    }

    public void disableRead() {
        stencil.endRead();
    }

    public void readAll() {
        if (used) {
            enableRead();
            enable();
            RenderUtil.rectangle(
                    0.0F, 0.0F,
                    (float) mc.displayWidth / RenderUtil.getScaledResolution().getScaleFactor(),
                    (float) mc.displayHeight / RenderUtil.getScaledResolution().getScaleFactor()
            );
            disable();
            disableRead();

            used = false;
        }
    }
}
