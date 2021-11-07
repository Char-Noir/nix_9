package ua.com.alevel;

import java.util.Comparator;

public class ArraySet<Value> {

    private Value[] set;
    private int size;
    private int capacity;

    public ArraySet(int c) {
        capacity = c;
        set = (Value[]) new Object[capacity];
        size = 0;
    }

    public ArraySet() {
        this(10);
    }

    public ArraySet(Value[] arr) {
        this.capacity = Math.max(arr.length,10);
        this.size = arr.length;
        set = (Value[]) new Object[capacity];
        System.arraycopy(arr, 0, this.set, 0, size);
    }

    public ArraySet(ArraySet<Value> valueArraySet) {
        this.capacity = valueArraySet.capacity;
        this.size = valueArraySet.size;
        set = (Value[]) new Object[capacity];
        System.arraycopy(valueArraySet.set, 0, this.set, 0, size);
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Set capacity must be bigger than 0");
        }
        if (capacity == this.capacity) {
            return;
        }
        resizeArray(capacity);
    }

    private void resizeArray(int capacity) {
        if (capacity < this.capacity) {
            this.set = cutArr(capacity);
        } else {
            Value[] array = (Value[]) new Object[capacity];
            System.arraycopy(this.set, 0, array, 0, this.size);
            this.set = array;
        }
        this.capacity = capacity;
    }

    public boolean contains(Value x) {
        for (int i = 0; i < size; i++) {
            if (set[i].equals(x))
                return true;
        }
        return false;
    }

    public void add(Value x) {
        if (contains(x)) {
            return;
        }
        if (size == capacity) {
            return;
        }
        set[size] = x;
        size++;
    }

    public void join(ArraySet<Value> arraySet) {
        Value[] array = arraySet.toArray();
        join(array);
    }

    public void join(Value[] array) {
        for (Value value : array) {
            add(value);
        }
    }

    public void removeLast() {
        size--;
        set[size] = null;
    }

    public void remove(Value x) {
        if (!contains(x)) {
            return;
        }
        boolean isRemoved = false;
        for (int i = 0; i < size - 1; i++) {
            if (set[i].equals(x) || isRemoved) {
                set[i] = set[i + 1];
                isRemoved = true;
            }
        }
        size--;
    }

    public Value[] cutArr(int startIndex, int endIndex) {
        Value[] returnable = (Value[]) new Object[endIndex - startIndex];
        if (endIndex - startIndex >= 0)
            System.arraycopy(set, startIndex, returnable, 0, endIndex - startIndex);
        return returnable;
    }

    public Value[] cutArr(int endIndex) {
        return cutArr(0, endIndex);
    }

    public ArraySet<Value> cut(int startIndex, int endIndex) {
        return new ArraySet<>(cutArr(startIndex, endIndex));
    }

    public ArraySet<Value> cut(int endIndex) {
        return cut(0, endIndex);
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            set[i] = null;
        }
        size = 0;
    }

    public void clear(Value[] value) {
        clear();
        join(value);
    }

    public int getSize() {
        return size;
    }

    public Value[] toArray() {
        Value[] returnable = (Value[]) new Object[size];
        System.arraycopy(set, 0, returnable, 0, size);
        return returnable;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void intersection(ArraySet<Value> arraySet) {
        ArraySet<Value> as = new ArraySet<>(this.capacity);
        Value[] array = arraySet.toArray();
        for (Value value : array) {
            if (contains(value)) {
                as.add(value);
            }
        }
        this.size = as.size;
        this.set = as.toArray();
        resizeArray(as.capacity);
    }

    public void sort(int startIndex, int endIndex, boolean isDescending, Comparator<Value> comparator) {
        if(startIndex>=endIndex){
            throw new IllegalArgumentException("Start index mast be lower than end index");
        }
        if(startIndex<0){
            throw new IllegalArgumentException("Start index must be equal or more than 0");
        }
        if(endIndex>=size){
            throw new IllegalArgumentException("End index must be lower than size of set");
        }
        SortHelper<Value> sort = new SortHelper<>(comparator);
        sort.mergeSort(set, startIndex, endIndex, isDescending);
    }

    public void sort(boolean isDescending, Comparator<Value> comparator) {
        sort(0, size-1, isDescending, comparator);
    }

    public void sort(Value value, boolean isDescending, Comparator<Value> comparator) {
        int startIndex = getIndex(value);
        System.out.println(startIndex);
        if(startIndex==-1){
            throw new IllegalArgumentException("There are now this number");
        }
        sort(startIndex, size, isDescending, comparator);
    }

    public int getIndex(Value value) {
        System.out.println(value);
        for (int i = 0; i < size; i++) {
            System.out.println(set[i]);
            if (value.equals(set[i])) {
                return i;
            }
        }
        return -1;
    }

    public Value getBound(Comparator<Value> comparator, boolean isMax) {
        ArraySet<Value> as = new ArraySet<>(this);
        as.sort(!isMax, comparator);
        return as.get(0);
    }

    public Value get(int index) {
        if (index < 0 || index > size-1) {
            throw new IllegalArgumentException();
        }
        return set[index];
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(this.getClass().getSimpleName()).append('\n');
        stringBuilder.append("Size: ").append(size).append('\n');
        stringBuilder.append("Capacity: ").append(capacity).append('\n');
        stringBuilder.append('[');
        for(int i = 0; i < size; i++){
            stringBuilder.append(set[i].toString());
            if(i!=size-1){
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append(']').append('\n');
        return stringBuilder.toString();
    }

}