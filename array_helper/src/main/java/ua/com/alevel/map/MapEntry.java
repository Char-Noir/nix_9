package ua.com.alevel.map;

import java.util.Objects;

public class MapEntry<Value> {
    String name;
    String title;
    Value pane;

    public MapEntry(String name, String title, Value pane) {
        this.name = name;
        this.title = title;
        this.pane = pane;
    }

    public MapEntry(String name, Value pane) {
        this(name,name,pane);
    }

    public MapEntry(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MapEntry<?> mapEntry = (MapEntry<?>) o;
        return name.equals(mapEntry.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name,title,pane);
    }

    public String getName() {
        return name;
    }

    public MapEntry<Value> setName(String name) {
        this.name = name;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public MapEntry<Value> setTitle(String title) {
        this.title = title;
        return this;
    }

    public Value getPane() {
        return pane;
    }

    public MapEntry<Value> setPane(Value pane) {
        this.pane = pane;
        return this;
    }
}
