package io.github.strikeless.neptunium.util.visual.font;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FontSize {
    SMALL(0, 9.0F, 1.0F), // fuck small font as a wise man once said
    NORMAL(1, 18.0F, 1.0F),
    BIG(2, 27.0F, 1.5F);

    private final int index;

    private final float size;

    private final float vanillaMultiplier;
}
