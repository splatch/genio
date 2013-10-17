package org.genio.core;

import java.io.IOException;
import java.io.Writer;

import org.apache.commons.lang.RandomStringUtils;
import org.genio.api.GeneratorException;
import org.genio.api.ValueGenerator;

public class StringGenerator implements ValueGenerator<String> {

    
    private final String name;
    private final int length;

    public StringGenerator(String name, int length) {
        this.name = name;
        this.length = length;
    }

    public String generate(Writer output) throws GeneratorException {
        try {
            output.write(RandomStringUtils.randomAlphabetic(length));
        } catch (IOException e) {
            throw new GeneratorException(e);
        }
        return null;
    }

    public String getName() {
        return name;
    }

}
