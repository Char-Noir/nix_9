package ua.com.alevel.map;

import ua.com.alevel.ArraySet;

public class MapWithEntries<Value> implements MapWithEntriesI<Value> {

    ArraySet<MapEntry<Value>> arraySet = new ArraySet<>();

    @Override
    public void add(String name, String title, Value element) {
        arraySet.add(new MapEntry<>(name, title, element));
    }

    @Override
    public boolean contains(String name) {
        return arraySet.contains(new MapEntry<>(name));
    }

    @Override
    public  MapEntry<Value> get(String name) {
        return arraySet.get(new MapEntry<>(name));
    }

    @Override
    public boolean remove(String name) {
        return arraySet.remove(new MapEntry<>(name));
    }
}
