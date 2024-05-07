package io.github.strikeless.neptunium;

import io.github.strikeless.neptunium.event.api.EventBus;
import io.github.strikeless.neptunium.event.impl.ClientShutdownEvent;
import io.github.strikeless.neptunium.event.listener.ClientShutdownListener;
import io.github.strikeless.neptunium.feature.command.api.CommandManager;
import io.github.strikeless.neptunium.feature.module.api.ModuleManager;
import io.github.strikeless.neptunium.ui.engine.UIEngine;
import io.github.strikeless.neptunium.util.interfaces.Initializable;
import io.github.strikeless.neptunium.util.visual.font.FontManager;
import io.github.strikeless.neptunium.util.visual.opengl.shader.ShaderManager;
import lombok.Getter;
import net.minecraft.client.Minecraft;

/**
 * @author Strikeless
 * @since 05.07.2022
 */
@Getter
public enum Neptunium implements Initializable, ClientShutdownListener {

    INSTANCE;

    private final EventBus eventBus;
    private final ModuleManager moduleManager;
    private final CommandManager commandManager;
    private final FontManager fontManager;
    private final ShaderManager shaderManager;
    private final UIEngine uiEngine;

    private final Initializable[] initializables = {
            this.eventBus = new EventBus(),
            //this.uiEngine = new UIEngine(),
            this.moduleManager = new ModuleManager(),
            this.commandManager = new CommandManager(),
            this.fontManager = new FontManager(),
            this.shaderManager = new ShaderManager(),
            this.uiEngine = new UIEngine(),
    };

    private final NeptuniumBranding branding = new NeptuniumBranding("2.0", "Neptunium");

    @Override
    public void init() {
        for (final Initializable initializable : this.initializables) {
            initializable.init();
        }
    }

    @Override
    public void uninit() {
        for (final Initializable initializable : this.initializables) {
            initializable.uninit();
        }
    }


    @Override
    public void onClientShutdown(final ClientShutdownEvent event) {
        final Minecraft mc = Minecraft.getMinecraft();

        /*if (!(mc.currentScreen instanceof NeptuniumGoodbyeGui)) {
            mc.displayGuiScreen(new NeptuniumGoodbyeGui());
            event.setCancelled(true);
        }*/

        uninit();
    }
}
