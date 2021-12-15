package ua.com.alevel.util;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class ArrayHelper {

    public static <T> T[] copyOf(Object[] original, int newLength, Class<? extends T[]> newType) {
        @SuppressWarnings("unchecked")
        T[] copy = (T[]) Array.newInstance(newType.getComponentType(), newLength);
        System.arraycopy(original, 0, copy, 0,
                Math.min(original.length, newLength));
        return copy;
    }

    public static <Entity> Entity[] invert(Entity[] array, Class<Entity> clazz) {
        List<Object> list = Arrays.asList(array);
        Collections.reverse(list);
        Entity[] obj = (Entity[]) Array.newInstance(clazz, 0);
        return list.toArray(obj);
    }
}