package org.genio.core;

import java.io.IOException;
import java.io.Writer;

import org.genio.api.GeneratorException;
import org.genio.api.ValueGenerator;

public class NumberGenerator implements ValueGenerator<Long> {

    private final String name;
    private final long minimum;
    private final long maximum;

    public NumberGenerator(String name, long minimum, long maximum) {
        this.name = name;
        this.minimum = minimum;
        this.maximum = maximum;
    }

    public Long generate(Writer output) throws GeneratorException {
        long number = RangeUtil.generate(minimum, maximum);
        try {
            output.write("" + number);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return number;
    }

    public String getName() {
        return name;
    }

}
