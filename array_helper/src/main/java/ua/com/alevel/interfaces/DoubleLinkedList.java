package ua.com.alevel.interfaces;

import ua.com.alevel.entity.BaseEntity;

public interface DoubleLinkedList<Entity extends BaseEntity> extends Container<Entity> {

    /**
     * Inserts the specified element at the beginning.
     */
    void addFirst(Entity element);

    /**
     * Appends the specified element to the end.
     */
    void addLast(Entity element);

    /**
     * Appends the specified element to the end.
     */
    default void add(Entity element) {
        addLast(element);
    }

    default boolean contains(Entity element) {
        return (get(element) != null);
    }

    /**
     * Removes the first element.
     */
    default boolean contains(long id) {
        return (get(id) != null);
    }

    boolean update(Entity element);

    void removeFirst();

    /**
     * Removes the last element.
     */
    void removeLast();

    /**
     * Returns the first element.
     */
    Entity getFirst();

    /**
     * Returns the last element.
     */
    Entity getLast();

    /**
     * Returns the first occurrence of the specified element.
     * Returns null if no such element.
     * (use 'equals' method to check an occurrence)
     */
    Entity get(Entity element);

    /**
     * Removes the first occurrence of the specified element.
     * Returns true if this list contained the specified element.
     * (use 'equals' method to check an occurrence)
     */
    Entity get(long id);

    boolean remove(Entity element);

    boolean remove(long id);
}
