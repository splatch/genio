package org.genio.core;

import java.io.IOException;
import java.io.Writer;
import java.util.Random;

import org.genio.api.GeneratorException;

public class MsisdnGenerator extends NumberGenerator {

    public MsisdnGenerator(String name, long minimum, long maximum) {
        super(name, minimum, maximum);
    }

    public Long generate(Writer output) throws GeneratorException {
        int prefix = new Random().nextInt(2);
        try {
            output.write("+" + prefix);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        super.generate(output);
        return null;
    }


}
