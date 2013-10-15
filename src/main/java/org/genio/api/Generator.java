package org.genio.api;

import java.io.Writer;

public interface Generator<T> {

    T generate(Writer output) throws GeneratorException;

}
