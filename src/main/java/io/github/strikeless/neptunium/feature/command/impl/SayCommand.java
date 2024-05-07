package io.github.strikeless.neptunium.feature.command.impl;

import io.github.strikeless.neptunium.feature.command.api.Command;
import io.github.strikeless.neptunium.feature.command.api.CommandInfo;

@CommandInfo(name = "Say", description = "Says something in chat", usage = "[what to say]")
public class SayCommand extends Command {

    @Override
    public void onExecute(final String[] args) {
        if (args.length == 0) throw new IndexOutOfBoundsException();

        // I swear to god if some anticheat starts checking for if a message was sent at the wrong time of tick
        mc.thePlayer.sendChatMessage(String.join(" ", args));
    }
}
