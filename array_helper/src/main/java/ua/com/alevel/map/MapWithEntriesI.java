package ua.com.alevel.map;

public interface MapWithEntriesI<Value> {

    void add(String name, String title, Value element);

    boolean contains(String name);

    MapEntry<Value> get(String name);

    boolean remove(String name);
}
