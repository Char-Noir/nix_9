package ua.com.alevel.util;

import java.lang.reflect.Array;
import java.util.*;
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

    public static String findFirstUnique(List<String> ints) {
        Map<String, Integer> m = new HashMap<>();
        for (String s : ints) {
            if (m.containsKey(s)) {
                m.put(s, m.get(s) + 1);
            } else {
                m.put(s, 1);
            }
        }
        // Traverse array again and return
        // first element with count 1.
        for (String anInt : ints)
            if (m.get(anInt) == 1)
                return anInt;
        return null;
    }
}