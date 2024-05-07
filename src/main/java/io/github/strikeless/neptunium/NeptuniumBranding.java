package io.github.strikeless.neptunium;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Strikeless
 * @since 15.07.2022
 */
// Only made this as non-final values in enums seems to be discouraged, and we are using an enum instance.
@Data
@AllArgsConstructor
public class NeptuniumBranding {

    private final String version;

    private String name;
}
