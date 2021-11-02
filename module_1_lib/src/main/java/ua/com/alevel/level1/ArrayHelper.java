package ua.com.alevel.level1;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArrayHelper {
    public static int calcUniqueEntry(int[] array) {
        Set<Integer> uniqueEntries = new HashSet<>();
        for (int entry :
                array) {
            uniqueEntries.add(entry);
        }
        return uniqueEntries.size();
    }

    public static int calcUniqueEntry(List<Integer> array) {
        Set<Integer> uniqueEntries = new HashSet<>(array);
        return uniqueEntries.size();
    }
}
