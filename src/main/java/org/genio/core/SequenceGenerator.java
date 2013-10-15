package org.genio.core;

import java.io.IOException;
import java.io.Writer;

import org.genio.api.GeneratorException;
import org.genio.api.ValueGenerator;

public class SequenceGenerator implements ValueGenerator<Long> {

    private Long sequence;
    private final String name;

    public SequenceGenerator(String name) {
        this(name, 1L);
    }

    public SequenceGenerator(String name, Long startFrom) {
        this.name = name;
        this.sequence = startFrom;
    }

    public Long generate(Writer output) throws GeneratorException {
        try {
            output.write((sequence++).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getName() {
        return name;
    }

}
