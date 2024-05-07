package io.github.strikeless.neptunium.feature.module.api;

import io.github.strikeless.neptunium.event.impl.KeyInputEvent;
import io.github.strikeless.neptunium.event.impl.ModuleInvalidateEvent;
import io.github.strikeless.neptunium.event.listener.KeyInputListener;
import io.github.strikeless.neptunium.feature.api.FeatureManager;
import io.github.strikeless.neptunium.util.interfaces.InstanceAccessor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Strikeless
 * @since 05.07.2022
 */
public class ModuleManager extends FeatureManager<Module> implements InstanceAccessor, KeyInputListener {

    @Override
    public void init() {
        super.init(Module.class);
        neptunium.getEventBus().register(this);
    }

    @Override
    public void uninit() {
        super.uninit();
        neptunium.getEventBus().unregister(this);
    }

    @Override
    public void add(final Module feature) {
        super.add(feature);

        final ModuleInvalidateEvent event = new ModuleInvalidateEvent(feature, ModuleInvalidateEvent.InvalidationType.MODULES_CHANGED);
        event.dispatch();
    }

    @Override
    public void remove(final Module feature) {
        super.remove(feature);

        final ModuleInvalidateEvent event = new ModuleInvalidateEvent(feature, ModuleInvalidateEvent.InvalidationType.MODULES_CHANGED);
        event.dispatch();
    }

    @Override
    public void onKeyInput(final KeyInputEvent event) {
        // Try to find a module with a binding of the pressed key
        final Module module = this.get(m -> m.isBound() && m.getBind().getKeyCode() == event.getKeyCode());
        if (module == null) return;

        // Let the binding handle the key press/release
        module.getBind().handleState(event.isState());
    }

    public List<Module> getByCategory(final ModuleCategory category) {
        return this.getAll().stream()
                .filter(module -> module.getCategory() == category)
                .collect(Collectors.toList());
    }
}
