package io.github.strikeless.neptunium.ui.engine.node.impl;

import io.github.strikeless.neptunium.ui.engine.node.Node;
import io.github.strikeless.neptunium.util.interfaces.InstanceAccessor;
import io.github.strikeless.neptunium.util.visual.font.FontSize;
import io.github.strikeless.neptunium.util.visual.font.renderer.NeptuniumFontRenderer;
import lombok.Builder;
import lombok.NonNull;

import java.util.function.Supplier;

@Builder
public class TextNode extends Node implements InstanceAccessor {

    @NonNull
    private Supplier<@NonNull String> text;

    @Builder.Default
    @NonNull
    private Supplier<@NonNull Align> align = () -> Align.LEFT;

    private Supplier<@NonNull String> fontName;

    @Builder.Default
    @NonNull
    private Supplier<@NonNull FontSize> fontSize = () -> FontSize.NORMAL;

    @Builder.Default
    @NonNull
    private Supplier<@NonNull Boolean> shadowed = () -> false;

    public enum Align {
        LEFT,
        CENTER,
        RIGHT
    }

    public static class TextNodeBuilder extends NodeBuilder {

        public TextNodeBuilder textConst(final String text) {
            return this.text(() -> text);
        }

        public TextNodeBuilder alignConst(final Align align) {
            return this.align(() -> align);
        }

        public TextNodeBuilder fontNameConst(final String fontName) {
            return this.fontName(() -> fontName);
        }

        public TextNodeBuilder fontSizeConst(final FontSize fontSize) {
            return this.fontSize(() -> fontSize);
        }

        public TextNodeBuilder shadowedConst(final boolean shadowed) {
            return this.shadowed(() -> shadowed);
        }
    }

    @Override
    public void draw() {
        final String text = this.text.get();
        final Align align = this.align.get();
        final String fontName = this.fontName == null ? neptunium.getFontManager().getDefaultFont() : this.fontName.get();
        final FontSize size = this.fontSize.get();
        final boolean shadowed = this.shadowed.get();

        final NeptuniumFontRenderer fontRenderer = neptunium.getFontManager().getFont(fontName, size);

        final float x;
        final float y = this.getScaledY();
        final float width = this.getScaledWidth();
        final float height = this.getScaledHeight();

        switch (align) {
            case LEFT: {
                x = this.getScaledX();
                break;
            }
            case CENTER: {
                x = this.getScaledX() + this.getScaledWidth() / 2.0F;
                break;
            }
            case RIGHT: {
                x = this.getScaledX() + this.getScaledWidth() - fontRenderer.getWidth(text);
                break;
            }
            default: {
                throw new IllegalStateException("Unhandled text align");
            }
        }

        // TODO: Scissoring with the width and height

        this.useColor(() -> {
            if (shadowed) {
                fontRenderer.drawStringShadowed(text, x, y);
            } else {
                fontRenderer.drawString(text, x, y);
            }
        });
    }
}
