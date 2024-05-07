package io.github.strikeless.neptunium.util.visual.opengl.shader;

import io.github.strikeless.neptunium.Neptunium;
import io.github.strikeless.neptunium.event.impl.RenderBeginEvent;
import io.github.strikeless.neptunium.event.impl.RenderEndEvent;
import io.github.strikeless.neptunium.event.listener.RenderBeginListener;
import io.github.strikeless.neptunium.event.listener.RenderEndListener;
import io.github.strikeless.neptunium.util.interfaces.Initializable;
import io.github.strikeless.neptunium.util.interfaces.InstanceAccessor;
import io.github.strikeless.neptunium.util.visual.opengl.shader.system.FBOShader;
import io.github.strikeless.neptunium.util.visual.opengl.shader.system.Shader;
import io.github.strikeless.neptunium.util.visual.opengl.shader.system.impl.ChromaShader;
import lombok.Getter;

@Getter
public class ShaderManager implements Initializable, InstanceAccessor, RenderBeginListener, RenderEndListener {

    private final ChromaShader chromaShader;

    private final Shader[] shaders = new Shader[] {
            chromaShader = new ChromaShader()
    };

    @Override
    public void init() {
        for (final Shader shader : shaders) {
            shader.init();
            shader.disable();
        }

        Neptunium.INSTANCE.getEventBus().register(this);
    }

    @Override
    public void uninit() {
        Neptunium.INSTANCE.getEventBus().unregister(this);

        for (final Shader shader : shaders) {
            shader.detach();
            shader.deleteShader();
        }
    }

    @Override
    public void onRenderBegin(final RenderBeginEvent event) {
        for (final Shader shader : shaders) {
            shader.update();
        }
    }

    @Override
    public void onRenderEnd(final RenderEndEvent event) {
        for (final Shader shader : shaders) {
            if (shader instanceof FBOShader) {
                // NOTE: I can't think of any scenario where you shouldn't call readAll manually after drawing your content with the shader.
                ((FBOShader) shader).readAll();
            }
        }
    }
}
