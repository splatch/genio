package org.genio.core;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Random;

import org.genio.api.GeneratorException;
import org.genio.api.ValueGenerator;

import com.google.common.collect.Lists;

public class RangeGenerator<T> implements ValueGenerator<T> {

    private final String name;
    private final List<T> values;

    public RangeGenerator(String name, T ... values) {
        this(name, Lists.newArrayList(values));
    }

    public RangeGenerator(String name, List<T> values) {
        this.name = name;
        this.values = values;
    }

    public T generate(Writer output) throws GeneratorException {

        Random random = new Random();
        int index = random.nextInt(values.size());

        T value = values.get(index);
        try {
            output.write(value.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return value;
    }

    public String getName() {
        return name;
    }

}
