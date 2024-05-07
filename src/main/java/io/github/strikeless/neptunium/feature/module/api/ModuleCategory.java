package io.github.strikeless.neptunium.feature.module.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Strikeless
 * @since 05.07.2022
 */
@Getter
@AllArgsConstructor
public enum ModuleCategory {

    MOVEMENT("Movement"),
    COMBAT("Combat"),
    PLAYER("Player"),
    WORLD("World"),
    EXPLOIT("Exploit"),
    RENDER("Render");

    private final String name;
}
