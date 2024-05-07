package io.github.strikeless.neptunium.event.api;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.reflect.TypeToken;
import io.github.strikeless.neptunium.util.interfaces.Initializable;
import org.atteo.classindex.ClassIndex;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The most autistic, over-engineered event bus known to man ™
 *
 * @author Strikeless
 * @since 06.07.2022
 */
@SuppressWarnings("UnstableApiUsage")
public class EventBus implements Initializable {

    // A list of all the listener interface classes
    private final List<Class<?>> listenerClasses = new ArrayList<>();

    // A multimap of all the registered listeners mapped to their listener type
    private final Multimap<TypeToken<?>, Object> listenerMap = ArrayListMultimap.create();

    @Override
    public void init() {
        // I'm assuming this can cope with obfuscation because it's ClassIndex?
        // no, no it can't (without an obfuscator that also remaps usages in plain files)
        ClassIndex.getPackageClasses("io.github.strikeless.neptunium.event.listener")
                .forEach(listenerClasses::add);
    }

    @Override
    public void uninit() {
        this.listenerMap.clear();
        this.listenerClasses.clear();
    }

    public void register(final Object listener) {
        final Class<?> klass = listener.getClass();

        // Get a collection of TypeTokens of listeners that the given listener implements
        // and then add the listener to our map to every type we just got.
        this.getListenerTypes(klass).forEach(type -> {
            this.listenerMap.put(type, listener);
        });
    }

    public void unregister(final Object listener) {
        final Class<?> klass = listener.getClass();

        // Get a collection of TypeTokens of listeners that the given listener implements
        // and then remove the listener from our map in every type we just got.
        this.getListenerTypes(klass).forEach(type -> {
            this.listenerMap.remove(type, listener);
        });
    }

    public void dispatch(final Event<?> event) {
        // Get every listener for the event's listener type
        // TODO: concurrency without cloning
        final Collection<Object> listeners = new ArrayList<>(this.listenerMap.get(event.getListenerTypeToken()));

        // Consume the event with every listener we just got
        // NOTE: We might want to not continue this chain if the event gets cancelled, but we should make event priorities first.
        listeners.forEach(event::consumeObject);
    }

    private Collection<TypeToken<?>> getListenerTypes(final Class<?> klass) {
        final Collection<TypeToken<?>> types = new ArrayList<>();

        // Loop over every listener interface class
        listenerClasses.forEach(listenerClass -> {
            // Confirm that the given listener implements the interface
            if (!listenerClass.isAssignableFrom(klass)) return;

            // Add the TypeToken of the listener interface class to our collection
            types.add(TypeToken.of(listenerClass));
        });

        // At last, return our collection with the types
        return types;
    }
}
