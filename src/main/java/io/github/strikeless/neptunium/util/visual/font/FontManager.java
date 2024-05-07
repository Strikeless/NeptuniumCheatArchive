package io.github.strikeless.neptunium.util.visual.font;

import io.github.strikeless.neptunium.util.interfaces.Initializable;
import io.github.strikeless.neptunium.util.visual.font.renderer.NeptuniumFontRenderer;
import io.github.strikeless.neptunium.util.visual.font.renderer.impl.CustomNeptuniumFontRenderer;
import io.github.strikeless.neptunium.util.visual.font.renderer.impl.VanillaNeptuniumFontRenderer;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FontManager implements Initializable {
    private static final String RESERVED_VANILLA_NAME = "vanilla";

    private final Map<String, NeptuniumFontRenderer[]> fontMap = new HashMap<>();

    @Getter
    @Setter
    private String defaultFont;

    private static NeptuniumFontRenderer[] initSizeArray() {
        return new NeptuniumFontRenderer[FontSize.values().length];
    }

    @Override
    public void init() {
        final NeptuniumFontRenderer[] sizeArray = initSizeArray();

        for (final FontSize size : FontSize.values()) {
            sizeArray[size.getIndex()] = new VanillaNeptuniumFontRenderer(size);
        }

        fontMap.put(RESERVED_VANILLA_NAME, sizeArray);
    }

    @Override
    public void uninit() {
        fontMap.clear();
    }

    public Set<String> getFontNames() {
        return fontMap.keySet();
    }

    public boolean isFontLoaded(final String name) {
        return fontMap.get(name.toLowerCase()) != null;
    }

    /**
     * Loads a custom font with the given name
     * @param name the font name
     * @return whether the font was added successfully
     */
    public boolean loadCustomFont(final String name) {
        if (this.fontMap.containsKey(name.toLowerCase())) return false;

        final NeptuniumFontRenderer[] sizeArray = initSizeArray();
        try {
            for (final FontSize size : FontSize.values()) {
                sizeArray[size.getIndex()] = new CustomNeptuniumFontRenderer(name, size);
            }
        } catch (final Exception ex) {
            ex.printStackTrace();
            return false;
        }
        fontMap.put(name.toLowerCase(), sizeArray);

        return true;
    }

    public NeptuniumFontRenderer getFont(final FontSize size) {
        return getFont(defaultFont, size);
    }

    public NeptuniumFontRenderer getFont(final String name, final FontSize size) {
        final NeptuniumFontRenderer font = fontMap.get(name.toLowerCase())[size.getIndex()];
        return font != null ? font : fontMap.get("vanilla")[size.getIndex()]; // Default to the vanilla font if the font wasn't found
    }
}
