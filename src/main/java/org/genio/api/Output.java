package org.genio.api;

import java.io.InputStream;

public interface Output<T> {

    InputStream generate(CompositeGenerator<T> generator);

}
