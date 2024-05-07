package io.github.strikeless.neptunium.util;

import lombok.experimental.UtilityClass;

import java.io.InputStream;

/**
 * @author Strikeless
 * @since 17.07.2022
 */
@UtilityClass
public class ResourceUtil {

    public InputStream getNeptuniumAsset(final String path) {
        return ResourceUtil.class.getResourceAsStream("/assets/neptunium/" + path);
    }
}
