package io.github.strikeless.neptunium.feature.command.impl;

import io.github.strikeless.neptunium.feature.command.api.Command;
import io.github.strikeless.neptunium.feature.command.api.CommandInfo;
import io.github.strikeless.neptunium.feature.command.api.CommandManager;
import io.github.strikeless.neptunium.util.NotifyUtil;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;

/**
 * @author Strikeless
 * @since 06.07.2022
 */
@CommandInfo(name = "Help", description = "Shows a list of commands and how to use them", usage = "")
public class HelpCommand extends Command {

    @Override
    public void onExecute(final String[] args) {
        final CommandManager commandManager = neptunium.getCommandManager();
        final ChatComponentText[] content = new ChatComponentText[2 + commandManager.size()];

        content[0] = new ChatComponentText("Hover over a command to see more info about it");
        content[1] = new ChatComponentText("Here's a list of commands:");

        int index = 1;
        for (final Command command : commandManager.getAll()) {
            final ChatComponentText chatComponent = new ChatComponentText("  " + command.getName());
            chatComponent.getChatStyle().setChatHoverEvent(
                    new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText(
                            "Description: " + command.getDescription()
                                    + "\nUsage: " + commandManager.getPrefix() + command.getName() + " " + command.getUsage()
                                    + "\n\nIn usages, [] means required, () means optional"
                    ))
            );

            content[++index] = chatComponent;
        }

        NotifyUtil.chat(content);
    }
}
