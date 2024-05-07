package io.github.strikeless.neptunium.ui.engine.node;

import io.github.strikeless.neptunium.util.interfaces.InstanceAccessor;
import io.github.strikeless.neptunium.util.visual.RenderUtil;
import io.github.strikeless.neptunium.util.visual.color.NeptuniumColor;
import io.github.strikeless.neptunium.util.visual.color.impl.StaticColor;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author Strikeless
 * @since 15.07.2022
 */
@Data
@Builder
public abstract class Node implements InstanceAccessor {

    @Builder.Default
    @NonNull
    // TODO: Not all types of nodes (such as text nodes) should be allowed to have children.
    private List<Node> children = new ArrayList<>();

    @Builder.Default
    @NonNull
    private Supplier<@NonNull Float> x = () -> 0.0F;

    @Builder.Default
    @NonNull
    private Supplier<@NonNull Float> y = () -> 0.0F;

    @Builder.Default
    @NonNull
    private Supplier<@NonNull Float> width = () -> 1.0F;

    @Builder.Default
    @NonNull
    private Supplier<@NonNull Float> height = () -> 1.0F;

    private Supplier<@NonNull NeptuniumColor> color;

    public static class NodeBuilder {

        public void child(final Node node) {
            this.children$value.add(node);
        }

        /*
         * The supplier variants of the following methods are automatically implemented by lombok.
         */

        public NodeBuilder xConst(final float x) {
            return this.x(() -> x);
        }

        public NodeBuilder yConst(final float y) {
            return this.y(() -> y);
        }

        public NodeBuilder widthConst(final float width) {
            return this.width(() -> width);
        }

        public NodeBuilder heightConst(final float height) {
            return this.height(() -> height);
        }

        public NodeBuilder colorConst(final NeptuniumColor color) {
            return this.color(() -> color);
        }

        public NodeBuilder colorConst(final float red, final float green, final float blue, final float alpha) {
            return this.colorConst(new StaticColor(red, green, blue, alpha));
        }
    }

    public abstract void draw();

    public void drawRecursively() {
        // Draw this node itself. This should be done before drawing the children,
        // thus this method was created instead of just calling super.draw() in node implementations.
        this.draw();

        // Draw all our children recursively as well
        // TODO: Set appropriate viewport for children
        this.children.forEach(Node::drawRecursively);
    }

    /*
     * TODO: We should be scaling based on the current viewport so that children will have the right scale
     *  and we most certainly should not be scaling based on Minecraft's GUI scale...
     */

    public float getScaledX() {
        return this.x.get() * (float) RenderUtil.getScaledResolution().getScaledHeight_double();
    }

    public float getScaledY() {
        return this.y.get() * (float) RenderUtil.getScaledResolution().getScaledWidth_double();
    }

    public float getScaledWidth() {
        return this.width.get() * (float) RenderUtil.getScaledResolution().getScaledHeight_double();
    }

    public float getScaledHeight() {
        return this.height.get() * (float) RenderUtil.getScaledResolution().getScaledWidth_double();
    }

    protected void useColor(final Runnable coloredRunnable) {
        final Supplier<NeptuniumColor> colorSupplier = this.color;
        if (colorSupplier == null) {
            coloredRunnable.run();
        } else {
            final NeptuniumColor color = colorSupplier.get();

            color.enableColor();
            coloredRunnable.run();
            color.disableColor();
        }
    }
}
