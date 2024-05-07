package io.github.strikeless.neptunium.feature.command.impl;

import io.github.strikeless.neptunium.feature.command.api.Command;
import io.github.strikeless.neptunium.feature.command.api.CommandInfo;
import io.github.strikeless.neptunium.util.NotifyUtil;

/**
 * @author Strikeless
 * @since 06.07.2022
 */
@CommandInfo(name = "ClientName", description = "Changes the name of Neptunium", usage = "[client name]")
public class ClientNameCommand extends Command {

    @Override
    public void onExecute(final String[] args) {
        if (args.length == 0) {
            NotifyUtil.chat("No client name provided!");
            return;
        }

        final String name = String.join(" ", args);
        neptunium.getBranding().setName(name);
    }
}
