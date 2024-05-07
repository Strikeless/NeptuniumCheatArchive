package io.github.strikeless.neptunium.feature.command.impl;

import io.github.strikeless.neptunium.feature.command.api.Command;
import io.github.strikeless.neptunium.feature.command.api.CommandInfo;
import io.github.strikeless.neptunium.feature.module.api.Module;
import io.github.strikeless.neptunium.feature.module.api.bind.ModuleBind;
import io.github.strikeless.neptunium.feature.module.api.bind.impl.HoldBind;
import io.github.strikeless.neptunium.feature.module.api.bind.impl.ToggleBind;
import io.github.strikeless.neptunium.util.NotifyUtil;
import org.lwjgl.input.Keyboard;

import java.util.Locale;

/**
 * @author Strikeless
 * @since 17.07.2022
 */
@CommandInfo(name = "Bind", description = "Sets a key-bind for the specified module", usage = "[module name] [key name] (toggle/hold)")
public class BindCommand extends Command {

    @Override
    public void onExecute(final String[] args) {
        switch (args.length) {
            case 0: {
                NotifyUtil.warn("No module name provided!");
                break;
            }
            case 1: {
                NotifyUtil.warn("No key name provided!");
                break;
            }
            default: {
                final Module module = neptunium.getModuleManager().get(args[0]);
                if (module == null) {
                    NotifyUtil.warn("No module found with the name \"" + args[0] + "\"!");
                    break;
                }

                final int keyCode = Keyboard.getKeyIndex(args[1].toUpperCase(Locale.ENGLISH));
                if (keyCode == 0) {
                    NotifyUtil.warn("No key found with the name \"" + args[1] + "\"!");
                    break;
                }

                final ModuleBind bind;
                switch (args.length >= 3 ? args[2].toLowerCase(Locale.ENGLISH) : "toggle") {
                    case "toggle": {
                        bind = new ToggleBind(module, keyCode);
                        break;
                    }
                    case "hold": {
                        bind = new HoldBind(module, keyCode);
                        break;
                    }
                    default: {
                        NotifyUtil.warn("Unrecognized bind mode \"" + args[2] + "\"!");
                        return;
                    }
                }

                module.setBind(bind);
                NotifyUtil.chat("Module " + module.getName() + " bound to key " + Keyboard.getKeyName(keyCode));
                break;
            }
        }
    }
}
