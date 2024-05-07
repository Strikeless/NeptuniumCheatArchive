package io.github.strikeless.neptunium.feature.module.impl.render;

import io.github.strikeless.neptunium.Neptunium;
import io.github.strikeless.neptunium.feature.module.api.Module;
import io.github.strikeless.neptunium.feature.module.api.ModuleCategory;
import io.github.strikeless.neptunium.feature.module.api.ModuleInfo;
import io.github.strikeless.neptunium.feature.module.api.bind.impl.HoldBind;
import io.github.strikeless.neptunium.ui.engine.node.Node;
import io.github.strikeless.neptunium.ui.engine.node.NodeAlign;
import io.github.strikeless.neptunium.ui.engine.node.color.NodeColor;
import io.github.strikeless.neptunium.ui.engine.node.color.impl.StaticNodeColor;
import io.github.strikeless.neptunium.ui.engine.node.impl.LabelNode;
import io.github.strikeless.neptunium.ui.engine.node.impl.PanelNode;
import org.lwjgl.input.Keyboard;

import java.awt.*;

/**
 * @author Strikeless
 * @since 16.07.2022
 */
@ModuleInfo(name = "NodeTest", description = "node testing", category = ModuleCategory.RENDER)
public class NodeTestModule extends Module {

    private Node node;

    @Override
    public void init() {
        super.init();
        this.setEnabled(true);
        this.setBind(new HoldBind(this, Keyboard.KEY_G));
    }

    @Override
    public void onEnabled() {
        super.onEnabled();

        node = PanelNode.builder()
                .horizontalAlign(NodeAlign.BEGIN)
                .verticalAlign(NodeAlign.BEGIN)
                .margin(5.0F)
                .width(0.1F)
                .height(0.1F)
                .color(new StaticNodeColor(0.1F, 0.1F, 0.1F, 0.5F))
                .build()
                .addChildren(
                        LabelNode.builder()
                                .horizontalAlign(NodeAlign.BEGIN)
                                .verticalAlign(NodeAlign.BEGIN)
                                .margin(2.0F)
                                .color(new StaticNodeColor(Color.GREEN))
                                .text(Neptunium.INSTANCE.getBranding().getName())
                                .build(),

                        LabelNode.builder()
                                .horizontalAlign(NodeAlign.END)
                                .verticalAlign(NodeAlign.CENTER)
                                .margin(2.0F)
                                .color(new StaticNodeColor(Color.MAGENTA))
                                .text(Neptunium.INSTANCE.getBranding().getVersion())
                                .build()
                );

        neptunium.getUiEngine().showNodes(node);
    }

    @Override
    public void onDisabled() {
        super.onDisabled();
        neptunium.getUiEngine().hideNodes(node);
    }
}
