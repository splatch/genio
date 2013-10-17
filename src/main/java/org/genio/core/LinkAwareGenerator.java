package org.genio.core;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;

import org.genio.api.GeneratorException;
import org.genio.api.ValueGenerator;

import com.google.common.collect.Iterables;
import com.google.common.collect.Multimap;

public class LinkAwareGenerator<K, T> implements ValueGenerator<T> {

    private final String name;
    private final LinkedGenerator<K> keySource;
    private final Multimap<K, T> values;

    public LinkAwareGenerator(String name, LinkedGenerator<K> keySource, Multimap<K, T> values) {
        this.name = name;
        this.keySource = keySource;
        this.values = values;
    }

    public T generate(Writer output) throws GeneratorException {
        K key = keySource.getKey();
        Collection<T> collection = values.get(key);
        int arrKey = (int) RangeUtil.generate(0, collection.size() - 1);
        T value = Iterables.get(collection, arrKey);
        try {
            output.write(value.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return value;
    }

    public String getName() {
        return name;
    }

}
