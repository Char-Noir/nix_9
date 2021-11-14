package ua.com.alevel.comparator;

import java.math.BigDecimal;
import java.util.Comparator;

public class NumberComparator<T extends Number> implements Comparator<T> {

    public int compare(T a, T b) throws ClassCastException {
        return new BigDecimal(String.valueOf(a)).compareTo(new BigDecimal(String.valueOf(b)));
    }
}