package ua.com.alevel;

public interface MathSetI {
    void add(Number n);

    void add(Number... n);

    void join(MathSetI ms);

    void join(MathSetI... ms);

    void intersection(MathSetI ms);

    void intersection(MathSetI... ms);

    void sortDesc();

    void sortDesc(int firstIndex, int lastIndex);

    void sortDesc(Number value);

    void sortAsc();

    void sortAsc(int firstIndex, int lastIndex);

    void sortAsc(Number value);

    Number get(int index);

    Number getMax();

    Number getMin();

    Number getAverage();

    Number getMedian();

    Number[] toArray();

    Number[] toArray(int firstIndex, int lastIndex);

    MathSetI cut(int firstIndex, int lastIndex);

    void clear();

    void clear(Number[] numbers);
}
