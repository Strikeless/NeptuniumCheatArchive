package io.github.strikeless.neptunium.ui.engine.node.impl;

import io.github.strikeless.neptunium.ui.engine.node.Node;
import io.github.strikeless.neptunium.util.visual.color.NeptuniumColor;
import lombok.Builder;
import lombok.NonNull;

import java.util.function.Supplier;

@Builder
public class BlankNode extends Node {

    public static class BlankNodeBuilder extends NodeBuilder {

        @Override
        public NodeBuilder color(final Supplier<@NonNull NeptuniumColor> color) {
            throw new UnsupportedOperationException("Can't set color of blank node");
        }
    }

    @Override
    public void draw() {
    }
}
