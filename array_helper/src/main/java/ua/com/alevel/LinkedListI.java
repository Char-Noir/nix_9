package ua.com.alevel;

public interface LinkedListI<E> {
    /**
     * Inserts the specified element at the beginning.
     */
    void addFirst(E element);

    /**
     * Appends the specified element to the end.
     */
    void addLast(E element);

    /**
     * Removes the first element.
     */
    void removeFirst();

    /**
     * Removes the last element.
     */
    void removeLast();

    /**
     * Returns the first element.
     */
    E getFirst();

    /**
     * Returns the last element.
     */
    E getLast();

    /**
     * Returns the first occurrence of the specified element.
     * Returns null if no such element.
     * (use 'equals' method to check an occurrence)
     */
    E get(E element);

    /**
     * Removes the first occurrence of the specified element.
     * Returns true if this list contained the specified element.
     * (use 'equals' method to check an occurrence)
     */
    boolean remove(E element);
}
