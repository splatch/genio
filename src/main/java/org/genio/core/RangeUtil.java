package org.genio.core;

public class RangeUtil {

    public static long generate(long minimum, long maximum) {
        return minimum + (long)(Math.random() * ((maximum - minimum) + 1));
    }

}
