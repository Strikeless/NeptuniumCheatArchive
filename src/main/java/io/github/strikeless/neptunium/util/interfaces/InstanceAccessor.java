package io.github.strikeless.neptunium.util.interfaces;

import io.github.strikeless.neptunium.Neptunium;
import net.minecraft.client.Minecraft;

/**
 * @author Strikeless
 * @since 05.07.2022
 */
// Yes the interface's name is from Rise 6, you can seethe about it
public interface InstanceAccessor {

    Minecraft mc = Minecraft.getMinecraft();

    Neptunium neptunium = Neptunium.INSTANCE;
}
