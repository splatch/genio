package org.genio.core;

import java.io.OutputStream;
import java.util.Iterator;

import org.genio.api.CompositeGenerator;
import org.genio.api.GeneratorException;

import com.google.common.collect.Lists;

public class DefaultCompositeGenerator<T> implements CompositeGenerator<T> {

    private final Iterable<T> generators;

    public DefaultCompositeGenerator(T ... generators) {
        this.generators = Lists.newArrayList(generators);
    }

    public Object generate(OutputStream output) throws GeneratorException {
        return null;
    }

    public Iterator<T> iterator() {
        return generators.iterator();
    }

}
