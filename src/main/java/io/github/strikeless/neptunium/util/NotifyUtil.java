package io.github.strikeless.neptunium.util;

import io.github.strikeless.neptunium.util.interfaces.InstanceAccessor;
import lombok.experimental.UtilityClass;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

/**
 * @author Strikeless
 * @since 06.07.2022
 */
@UtilityClass
public class NotifyUtil implements InstanceAccessor {

    public void info(final String content) {
        // TODO: notifications
        chat(content);
    }

    public void warn(final String content) {
        // TODO: notifications
        chat(content);
    }

    public void error(final String content) {
        // TODO: notifications
        chat(content);
    }

    public void chat(final String... content) {
        chatRaw("");
        for (final String c : content) chatRaw(getPrefix().appendText(c));
        chatRaw("");
    }

    public void chat(final IChatComponent... chatComponents) {
        chatRaw("");
        for (final IChatComponent c : chatComponents) chatRaw(getPrefix().appendSibling(c));
        chatRaw("");
    }

    public void chatRaw(final String content) {
        chatRaw(new ChatComponentText(content));
    }

    public void chatRaw(final IChatComponent chatComponent) {
        if (mc.ingameGUI == null || mc.ingameGUI.getChatGUI() == null) return;
        mc.ingameGUI.getChatGUI().printChatMessage(chatComponent);
    }

    private ChatComponentText getPrefix() {
        return new ChatComponentText(
                EnumChatFormatting.AQUA + neptunium.getBranding().getName()
                        + EnumChatFormatting.DARK_AQUA + " > "
                        + EnumChatFormatting.RESET
        );
    }
}
