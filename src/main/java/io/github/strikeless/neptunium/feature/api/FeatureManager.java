package io.github.strikeless.neptunium.feature.api;

import io.github.strikeless.neptunium.util.interfaces.Initializable;
import org.atteo.classindex.ClassIndex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Strikeless
 * @since 05.07.2022
 */
public abstract class FeatureManager<T extends Feature> implements Initializable {

    private final List<T> list = new ArrayList<>();

    protected void init(final Class<T> parentClass) {
        ClassIndex.getSubclasses(parentClass).forEach(klass -> {
            try {
                final T instance = klass.getConstructor().newInstance();
                this.add(instance);
            } catch (final ReflectiveOperationException ex) {
                throw new RuntimeException("Failed to instantiate feature", ex);
            }
        });
    }

    @Override
    public void uninit() {
        this.list.forEach(Feature::uninit);
        this.list.clear();
    }

    public void add(final T feature) {
        this.list.add(feature);
        feature.init();
    }

    public void remove(final T feature) {
        this.list.remove(feature);
    }

    public int size() {
        return this.list.size();
    }

    public T get(final int index) {
        return this.list.get(index);
    }

    public <B extends T> B get(final Class<B> klass) {
        return this.get(feature -> feature.getClass() == klass);
    }

    public <B extends T> B get(final String name) {
        return this.get(feature -> feature.getName().equalsIgnoreCase(name));
    }

    @SuppressWarnings("unchecked")
    public <B extends T> B get(final Predicate<? super T> predicate) {
        // Stream over each element in the list and filter them by the given predicate,
        // then find any element that passed the filter and return that or null if none exist.
        return (B) this.list.parallelStream()
                .filter(predicate)
                .findAny()
                .orElse(null);
    }

    public <B extends T> List<B> getAll(final String name) {
        return this.getAll(
                feature -> feature.getName().toLowerCase(Locale.ENGLISH)
                        .contains(name.toLowerCase(Locale.ENGLISH))
        );
    }

    public List<T> getAll() {
        return Collections.unmodifiableList(this.list);
    }

    @SuppressWarnings("unchecked")
    public <B extends T> List<B> getAll(final Predicate<? super T> predicate) {
        // Stream over each element in the list and filter them by the given predicate,
        // then collect them into a list and return that list.
        return (List<B>) this.list.parallelStream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
}
