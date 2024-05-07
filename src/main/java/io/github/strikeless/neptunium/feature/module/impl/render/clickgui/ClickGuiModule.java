package io.github.strikeless.neptunium.feature.module.impl.render.clickgui;

import io.github.strikeless.neptunium.event.impl.KeyInputEvent;
import io.github.strikeless.neptunium.event.impl.ModuleInvalidateEvent;
import io.github.strikeless.neptunium.event.listener.KeyInputListener;
import io.github.strikeless.neptunium.event.listener.ModuleInvalidateListener;
import io.github.strikeless.neptunium.feature.module.api.Module;
import io.github.strikeless.neptunium.feature.module.api.ModuleCategory;
import io.github.strikeless.neptunium.feature.module.api.ModuleInfo;
import io.github.strikeless.neptunium.feature.module.api.bind.impl.ToggleBind;
import org.lwjgl.input.Keyboard;

@ModuleInfo(name = "ClickGUI", category = ModuleCategory.RENDER, description = "Shows a GUI where you can tweak your client")
public class ClickGuiModule extends Module implements KeyInputListener, ModuleInvalidateListener {

    @Override
    public void init() {
        super.init();
        this.setBind(new ToggleBind(this, Keyboard.KEY_RSHIFT));
    }

    @Override
    public void onModuleInvalidate(final ModuleInvalidateEvent event) {

    }

    @Override
    public void onKeyInput(final KeyInputEvent event) {
        if (event.isState() && event.getKeyCode() == Keyboard.KEY_ESCAPE) {
            this.setEnabled(false);
        }
    }

    @Override
    public void onEnabled() {
        super.onEnabled();
    }

    @Override
    public void onDisabled() {
        super.onDisabled();
    }
}
