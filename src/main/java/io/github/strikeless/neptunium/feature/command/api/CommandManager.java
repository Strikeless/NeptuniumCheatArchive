package io.github.strikeless.neptunium.feature.command.api;

import io.github.strikeless.neptunium.event.impl.SendChatEvent;
import io.github.strikeless.neptunium.event.listener.SendChatListener;
import io.github.strikeless.neptunium.feature.api.FeatureManager;
import io.github.strikeless.neptunium.util.NotifyUtil;
import io.github.strikeless.neptunium.util.interfaces.InstanceAccessor;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

/**
 * @author Strikeless
 * @since 05.07.2022
 */
@Getter
@Setter
public class CommandManager extends FeatureManager<Command> implements InstanceAccessor, SendChatListener {

    private String prefix = ".";

    @Override
    public void init() {
        super.init(Command.class);
        neptunium.getEventBus().register(this);
    }

    @Override
    public void uninit() {
        super.uninit();
        neptunium.getEventBus().unregister(this);
    }

    @Override
    public void onSendChat(final SendChatEvent event) {
        final String message = event.getMessage();
        if (!this.isCommand(message)) return;

        event.setCancelled(true);
        this.execute(message);
    }

    public boolean isCommand(final String message) {
        return message.startsWith(this.prefix) && !message.startsWith(this.prefix + this.prefix);
    }

    public void execute(String message) {
        message = message.substring(prefix.length()).trim();

        final String[] args = message.split(" ");
        if (args.length == 0) return;

        final Command command = this.get(args[0]);
        if (command == null) {
            NotifyUtil.chat("Command not found!");
            return;
        }

        // Call the command's onExecute method with the first argument removed from args, as that's the command name.
        command.onExecute(Arrays.copyOfRange(args, 1, args.length));
    }
}
