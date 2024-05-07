package io.github.strikeless.neptunium.feature.command.api;

import io.github.strikeless.neptunium.feature.api.Feature;
import lombok.Getter;
import org.atteo.classindex.IndexSubclasses;

/**
 * @author Strikeless
 * @since 05.07.2022
 */
@Getter
@IndexSubclasses
public abstract class Command implements Feature {

    private final String name;

    private final String description;

    private final String usage;

    public Command() {
        final CommandInfo moduleInfo = this.getClass().getDeclaredAnnotation(CommandInfo.class);
        if (moduleInfo == null) {
            throw new IllegalStateException("Command " + this.getClass().getSimpleName() + " is not annotated!");
        }

        this.name = moduleInfo.name();
        this.description = moduleInfo.description();
        this.usage = moduleInfo.usage();
    }

    public Command(final String name, final String description, final String usage) {
        this.name = name;
        this.description = description;
        this.usage = usage;
    }

    public abstract void onExecute(final String[] args);
}
