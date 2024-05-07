package io.github.strikeless.neptunium.ui.engine.node.impl;

import io.github.strikeless.neptunium.ui.engine.node.Node;
import io.github.strikeless.neptunium.util.visual.RenderUtil;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class PanelNode extends Node {

    @Override
    public void draw() {
        this.useColor(() -> RenderUtil.rectangle(this.getScaledX(), this.getScaledY(), this.getScaledWidth(), this.getScaledHeight()));
    }
}
