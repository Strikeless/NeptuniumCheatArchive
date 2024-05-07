package io.github.strikeless.neptunium.feature.module.impl.render.clickgui;

import io.github.strikeless.neptunium.ui.engine.node.Node;
import io.github.strikeless.neptunium.util.interfaces.InstanceAccessor;

import java.util.Collection;

public abstract class ClickGui implements InstanceAccessor {
    public abstract Collection<Node> getNodes();
}
