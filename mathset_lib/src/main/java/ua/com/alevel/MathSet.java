package ua.com.alevel;

import ua.com.alevel.comparator.NumberComparator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathSet extends ArraySet<Number> {
    public MathSet(int c) {
        super(c);
    }

    public MathSet(Number[] numbers) {
        super(numbers);
    }

    public MathSet(Number[] ...numbers) {
        this(numbers[0]);
        for (int i = 1; i < numbers.length; i++) {
            this.setCapacity(this.getCapacity()+numbers[i].length);
            join(numbers[i]);
        }
        this.setCapacity(this.getSize());
    }

    public MathSet(MathSet mathSet) {
        super(mathSet);
    }

    public MathSet(MathSet... mathSets) {
        this(mathSets[0]);
        for (int i = 1; i < mathSets.length; i++) {
            this.setCapacity(this.getCapacity()+mathSets[i].getCapacity());
            join(mathSets[i]);
        }
        this.setCapacity(this.getSize());
    }

    public MathSet() {
        this(10);
    }

    public MathSet(ArraySet<Number> cut) {
        super(cut);
    }

    public void add(Number... number) {
        for (Number value : number) {
            add(value);
        }
    }

    public MathSet cut(int startIndex, int endIndex){
        return new MathSet(super.cut(startIndex,endIndex));
    }

    public void join(MathSet ms) {
        Number[] array =ms.toArray();
        join(array);
    }

    public void join(MathSet... mathSets) {
        for (MathSet mathSet : mathSets) {
            join(mathSet);
        }
    }

    public void intersection(MathSet ms) {
        super.intersection(ms);
    }

    public void intersection(MathSet... mathSets) {
        for (MathSet mathSet : mathSets) {
            intersection(mathSet);
        }
    }

    public void sortDesc() {
        sortDesc(0, super.getSize()-1);
    }

    public void sortDesc(int startIndex, int endIndex) {
        super.sort(startIndex, endIndex, false, new NumberComparator<Number>());
    }

    public void sortAsc() {
        sortAsc(0, super.getSize()-1);
    }

    public void sortAsc(int startIndex, int endIndex) {
        super.sort(startIndex, endIndex, true, new NumberComparator<Number>());
    }

    public int getIndex(Number value) {
        Number[] set = toArray();
        NumberComparator<Number> number = new NumberComparator<>();
        for (int i = 0; i < getSize(); i++) {
            if (number.compare(value,set[i])==0) {
                return i;
            }
        }
        return -1;
    }

    public void sortDesc(Number number) {
        int startIndex = getIndex(number);
        if(startIndex<0){
            throw new IllegalArgumentException("There are no this number");
        }
        System.out.println(startIndex);
        super.sort(startIndex,getSize()-1, false, new NumberComparator<>());
    }

    public void sortAsc(Number number) {
        super.sort(number, true, new NumberComparator<>());
    }

    public Number getMax() {
        return super.getBound(new NumberComparator<Number>(), true);
    }

    public Number getMin() {
        return super.getBound(new NumberComparator<Number>(), false);
    }

    public Number getAverage() {
        return getSum().divide(new BigDecimal(super.getSize()), 5, RoundingMode.HALF_UP);
    }

    public BigDecimal getSum() {
        Number[] numbers = toArray();
        BigDecimal sum = new BigDecimal(0);
        for (Number number : numbers) {
            sum=sum.add(new BigDecimal(String.valueOf(number)));
        }
        return sum;
    }

    public Number getMedian() {
        MathSet ms = new MathSet(this);
        ms.sortAsc();
        int size = ms.getSize();
        int med = size / 2;
        if (size % 2 == 1) {
            return get(med);
        } else {
            Number a = get(med);
            Number b = get(med - 1);
            ms.clear();
            ms.add(a);
            ms.add(b);
            return ms.getAverage();
        }
    }

    @Override
    public Number[] toArray() {
        Number[] numbers = new Number[getSize()];
        Object[] objects = super.toArray();
        for(int i = 0; i < numbers.length; i++) {
            if(objects[i] instanceof Number){
                numbers[i]= (Number) objects[i];
            }
        }
        return numbers;
    }

    public Number[] toArray(int startIndex, int endIndex) {
        MathSet ms = cut(startIndex,endIndex);
        return ms.toArray();
    }
}