package io.github.strikeless.neptunium.util.visual.opengl;

import io.github.strikeless.neptunium.util.ClientInstance;
import io.github.strikeless.neptunium.util.visual.RenderUtils;
import net.minecraft.client.shader.Framebuffer;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.EXTPackedDepthStencil;

import static org.lwjgl.opengl.GL11.*;

public class Stencil implements ClientInstance {

    public void beginWrite() {
        prepareFBO();

        // Stop writing to the color buffer
        glColorMask(false, false, false, false);

        // Start writing to the stencil buffer
        glStencilMask(0xFF);

        // Clear the stencil buffer
        glClearStencil(0x00);
        glClear(GL_STENCIL_BUFFER_BIT);

        RenderUtils.enable(GL_STENCIL_TEST);

        // Write everything (ref and mask are ignored and have no effect)
        glStencilFunc(GL_ALWAYS, 1, 0xFF);
        glStencilOp(GL_KEEP, GL_REPLACE, GL_REPLACE);
    }

    public void endWrite() {
        // Start writing to the color buffer again
        glColorMask(true, true, true, true);

        // Stop writing to the stencil buffer
        glStencilMask(0x00);

        RenderUtils.disable(GL_STENCIL_TEST);
    }

    public void beginRead() {
        // Enable stencil testing
        RenderUtils.enable(GL_STENCIL_TEST);

        // Keep everything in the stencil buffer
        glStencilOp(GL_KEEP, GL_KEEP, GL_KEEP);

        // Only pass fragments with a stencil value of 1
        glStencilFunc(GL_EQUAL, 1, 0xFF);
    }

    public void endRead() {
        // Disable stencil testing
        RenderUtils.disable(GL_STENCIL_TEST);

        glClear(GL_STENCIL_BUFFER_BIT);
    }

    private void prepareFBO() {
        // Taken from rice clarinet, thanks for the free code alan
        final Framebuffer fbo = mc.getFramebuffer();
        if (fbo != null && fbo.depthBuffer > -1) {
            EXTFramebufferObject.glDeleteRenderbuffersEXT(mc.getFramebuffer().depthBuffer);
            final int stencilDepthBufferID = EXTFramebufferObject.glGenRenderbuffersEXT();
            EXTFramebufferObject.glBindRenderbufferEXT(EXTFramebufferObject.GL_RENDERBUFFER_EXT, stencilDepthBufferID);
            EXTFramebufferObject.glRenderbufferStorageEXT(EXTFramebufferObject.GL_RENDERBUFFER_EXT, EXTPackedDepthStencil.GL_DEPTH_STENCIL_EXT, mc.displayWidth, mc.displayHeight);
            EXTFramebufferObject.glFramebufferRenderbufferEXT(EXTFramebufferObject.GL_FRAMEBUFFER_EXT, EXTFramebufferObject.GL_STENCIL_ATTACHMENT_EXT, EXTFramebufferObject.GL_RENDERBUFFER_EXT, stencilDepthBufferID);
            EXTFramebufferObject.glFramebufferRenderbufferEXT(EXTFramebufferObject.GL_FRAMEBUFFER_EXT, EXTFramebufferObject.GL_DEPTH_ATTACHMENT_EXT, EXTFramebufferObject.GL_RENDERBUFFER_EXT, stencilDepthBufferID);
            fbo.depthBuffer = -1;

            System.out.println("cocking");
        }
    }
}
