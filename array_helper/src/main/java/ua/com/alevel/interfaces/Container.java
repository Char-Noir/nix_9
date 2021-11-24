package ua.com.alevel.interfaces;

import ua.com.alevel.logic.entity.BaseEntity;

import java.util.Iterator;

public interface Container<Entity extends BaseEntity> extends Iterable<Entity> {

    void clear();

    int size();

    String toString();

    Entity[] toArray();

    Iterator<Entity> iterator();

}
