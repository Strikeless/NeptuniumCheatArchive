package io.github.strikeless.neptunium.util.visual.opengl.shader.system;

import io.github.strikeless.neptunium.util.ResourceUtil;
import io.github.strikeless.neptunium.util.visual.opengl.shader.system.uniform.ShaderUniform;
import io.github.strikeless.neptunium.util.visual.opengl.shader.system.uniform.ShaderUniformInstance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.opengl.GL20.*;

/**
 * Shader system ported to Minecraft from my LWJGL3 game engine.
 * Noticeably simplified from my original one (many methods and attribute system removed)
 *
 * @author Strikeless
 * @since 28.11.2021
 */
public abstract class Shader {

    private final String vertexName, fragmentName;

    private final ShaderUniform[] uniforms;

    private int programID, vertexID, fragmentID;

    private Map<ShaderUniform, ShaderUniformInstance> uniformInstances = new HashMap<>();

    public Shader(final String vertexName, final String fragmentName, final ShaderUniform... uniforms) {
        this.vertexName = vertexName;
        this.fragmentName = fragmentName;
        this.uniforms = uniforms;
    }

    private static int loadShader(final InputStream input, final int shaderType) throws IOException {
        // Get the contents of the file
        final StringBuilder programString = new StringBuilder();
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8))) {
            reader.lines().forEach(line -> programString.append(line).append("\n"));
        }

        // Let OpenGL create a new shader and get the shaders id
        final int shaderID = glCreateShader(shaderType);

        // Compile the shader and bind it to the program we just created
        glShaderSource(shaderID, programString);

        glCompileShader(shaderID);

        if (glGetShaderi(shaderID, GL_COMPILE_STATUS) != GL_TRUE) {
            throw new RuntimeException(glGetShaderInfoLog(shaderID, 256));
        }

        return shaderID;
    }

    private static int loadProgram(final int vertexID, final int fragmentID) {
        // Let OpenGL create a new program and get the program's id
        final int programID = glCreateProgram();

        // Attach both the vertex and the fragment shaders to the newly created program
        glAttachShader(programID, vertexID);
        glAttachShader(programID, fragmentID);

        // Link the program
        glLinkProgram(programID);
        if (glGetProgrami(programID, GL_LINK_STATUS) != GL_TRUE) {
            throw new RuntimeException(glGetProgramInfoLog(programID, 256));
        }

        // Use the program
        glUseProgram(programID);

        // Delete the already linked shaders
        glDeleteShader(vertexID);
        glDeleteShader(fragmentID);

        // Validate the program
        glValidateProgram(programID);
        if (glGetProgrami(programID, GL_VALIDATE_STATUS) != GL_TRUE) {
            throw new RuntimeException(glGetProgramInfoLog(programID, 256));
        }

        return programID;
    }

    public void init() {
        try {
            vertexID = loadShader(ResourceUtil.getNeptuniumAsset("shaders/vertex/" + vertexName), GL_VERTEX_SHADER);
        } catch (final Exception ex) {
            throw new RuntimeException("Unable to load vertex shader!", ex);
        }

        try {
            fragmentID = loadShader(ResourceUtil.getNeptuniumAsset("shaders/fragment/" + fragmentName), GL_FRAGMENT_SHADER);
        } catch (final Exception ex) {
            throw new RuntimeException("Unable to load fragment shader!", ex);
        }

        programID = loadProgram(vertexID, fragmentID);

        // Set up the uniforms
        setupUniforms(uniforms);
    }

    /**
     * Disables the shader
     */
    public void disable() {
        glUseProgram(0);
    }

    /**
     * Enables the shader
     */
    public void enable() {
        glUseProgram(programID);
    }

    /**
     * Detaches both the vertex and the fragment shader from the program
     */
    public void detach() {
        glDetachShader(programID, vertexID);
        glDetachShader(programID, fragmentID);
    }

    /**
     * Deletes the shader program and both the vertex and the fragment shader of the program.
     * Remember to detach the shaders first!
     */
    public void deleteShader() {
        glDeleteShader(vertexID);
        glDeleteShader(fragmentID);
        glDeleteProgram(programID);
    }

    public ShaderUniformInstance getUniform(final ShaderUniform uniform) {
        return this.uniformInstances.get(uniform);
    }

    public void update() {}

    private void setupUniforms(final ShaderUniform[] uniforms) {
        this.uniformInstances = new HashMap<>(uniforms.length);

        for (final ShaderUniform uniform : uniforms) {
            final int location = glGetUniformLocation(this.programID, uniform.getName());

            if (location < 0) {
                throw new IllegalStateException("Unable to find uniform: " + uniform.getName());
            }

            this.uniformInstances.put(uniform, new ShaderUniformInstance(uniform, location));
        }
    }
}
