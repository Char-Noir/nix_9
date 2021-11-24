package ua.com.alevel.interfaces;

import ua.com.alevel.logic.entity.BaseEntity;

public interface DoubleLinkedList<Entity extends BaseEntity> extends Container<Entity> {

    void addFirst(Entity element);

    void addLast(Entity element);

    default void add(Entity element) {
        addLast(element);
    }

    default boolean contains(Entity element) {
        return (get(element) != null);
    }

    default boolean contains(long id) {
        return (get(id) != null);
    }

    boolean update(Entity element);

    void removeFirst();

    void removeLast();

    Entity getFirst();

    Entity getLast();

    Entity get(Entity element);

    Entity get(long id);

    boolean remove(Entity element);

    boolean remove(long id);
}
