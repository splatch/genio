package org.genio.api;

import java.io.IOException;

public class GeneratorException extends Exception {

    public GeneratorException(IOException e) {
        super(e);
    }

}
