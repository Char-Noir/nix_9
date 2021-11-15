package ua.com.alevel.util;

import java.lang.reflect.Array;

public class ArrayHelper {

    public static <T> T[] copyOf(Object[] original, int newLength, Class<? extends T[]> newType) {
        @SuppressWarnings("unchecked")
        T[] copy = (T[]) Array.newInstance(newType.getComponentType(), newLength);
        System.arraycopy(original, 0, copy, 0,
                Math.min(original.length, newLength));
        return copy;
    }
}