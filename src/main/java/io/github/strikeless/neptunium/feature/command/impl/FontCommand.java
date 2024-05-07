package io.github.strikeless.neptunium.feature.command.impl;

import io.github.strikeless.neptunium.feature.command.api.Command;
import io.github.strikeless.neptunium.feature.command.api.CommandInfo;
import io.github.strikeless.neptunium.util.NotifyUtil;

import java.util.Set;

@CommandInfo(name = "Font", description = "Changes the default font that Neptunium uses", usage = "(font name), lists all fonts with no arguments")
public class FontCommand extends Command {
    @Override
    public void onExecute(final String[] args) {
        if (args.length == 0) {
            final Set<String> fontNames = neptunium.getFontManager().getFontNames();
            final String[] content = new String[1 + fontNames.size()];

            content[1] = "Here's a list of available fonts:";

            int index = 1;
            for (final String fontName : fontNames) {
                content[++index] = "  " + fontName;
            }

            NotifyUtil.chat(content);
        } else {
            final String fontName = String.join(" ", args);

            neptunium.getFontManager().loadCustomFont(fontName); // Ensure that the font is loaded if it exists
            if (!neptunium.getFontManager().isFontLoaded(fontName)) {
                NotifyUtil.warn("Font '" + fontName + "' does not exist!");
            } else {
                neptunium.getFontManager().setDefaultFont(fontName);
                NotifyUtil.chat("Font changed to '" + fontName + "'.");
            }
        }
    }
}
