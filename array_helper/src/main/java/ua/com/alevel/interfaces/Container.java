package ua.com.alevel.interfaces;

import ua.com.alevel.entity.BaseEntity;

import java.util.Iterator;

public interface Container<Entity extends BaseEntity> extends Iterable<Entity> {

    /**
     * Removes all elements.
     */
    void clear();

    /**
     * Returns the number of elements.
     */
    int size();

    /**
     * Returns a string representation of this container.
     */
    String toString();

    /**
     * Returns an array containing all elements of this container.
     */
    BaseEntity[] toArray();

    /**
     * Returns an iterator over elements.
     * Iterator must implements the remove method.
     */
    Iterator<Entity> iterator();
}
