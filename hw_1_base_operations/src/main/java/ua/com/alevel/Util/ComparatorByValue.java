package ua.com.alevel.Util;

import java.util.Comparator;
import java.util.Map;

public class ComparatorByValue implements Comparator<Character> {
    Map<Character, Integer> base;

    public ComparatorByValue(Map<Character, Integer> base) {
        this.base = base;
    }

    public int compare(Character a, Character b) {
        if(a.equals(b)) return 0;
        if (base.get(a) >= base.get(b)) {
            return -1;
        } else {
            return 1;
        }
    }
}