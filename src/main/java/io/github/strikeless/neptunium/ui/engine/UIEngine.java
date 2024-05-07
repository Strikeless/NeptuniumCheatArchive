package io.github.strikeless.neptunium.ui.engine;

import io.github.strikeless.neptunium.event.impl.RenderGuiEvent;
import io.github.strikeless.neptunium.event.listener.RenderGuiListener;
import io.github.strikeless.neptunium.ui.engine.node.Node;
import io.github.strikeless.neptunium.ui.engine.node.impl.PanelNode;
import io.github.strikeless.neptunium.util.interfaces.Initializable;
import io.github.strikeless.neptunium.util.interfaces.InstanceAccessor;

import java.util.Arrays;

/**
 * @author Strikeless
 * @since 15.07.2022
 */
public class UIEngine implements InstanceAccessor, Initializable, RenderGuiListener {

    /**
     * The base node works as a parent for every top-level node.
     * This kind of approach is used just to simplify things.
     */
    // A panel node with no color is used to emulate a blank node.
    private final Node baseNode = PanelNode.builder().build();

    @Override
    public void init() {
        neptunium.getEventBus().register(this);
    }

    @Override
    public void uninit() {
        neptunium.getEventBus().unregister(this);
        this.baseNode.getChildren().clear();
    }

    public void showNodes(final Node... nodes) {
        this.baseNode.getChildren().addAll(Arrays.asList(nodes));
    }

    public void hideNodes(final Node... nodes) {
        this.baseNode.getChildren().addAll(Arrays.asList(nodes));
    }

    @Override
    public void onRenderGUI(final RenderGuiEvent event) {
        this.baseNode.drawRecursively();
    }
}
