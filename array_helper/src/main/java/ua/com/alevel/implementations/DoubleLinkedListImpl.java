package ua.com.alevel.implementations;

import ua.com.alevel.entity.BaseEntity;
import ua.com.alevel.interfaces.DoubleLinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleLinkedListImpl<Entity extends BaseEntity> implements DoubleLinkedList<Entity> {

    final Node<Entity> head = new Node<>();
    final Node<Entity> tail = new Node<>();
    private final Class<Entity> _class;

    public DoubleLinkedListImpl(Class<Entity> aClass) {
        _class = aClass;
        head.next = tail;
        tail.prev = head;
    }

    @Override
    public void addFirst(Entity element) {
        Node<Entity> newNode = new Node<>(element);
        Node<Entity> first = head.next;
        first.prev = newNode;
        newNode.next = first;
        newNode.prev = head;
        head.next = newNode;
    }

    @Override
    public void addLast(Entity element) {
        Node<Entity> newNode = new Node<>(element);
        Node<Entity> last = tail.prev;
        newNode.prev = last;
        last.next = newNode;
        tail.prev = newNode;
        newNode.next = tail;
    }

    @Override
    public void removeFirst() {
        if (head.next != tail) {
            Node<Entity> temp = head.next;
            temp.next.prev = head;
            head.next = temp.next;
            System.gc();
        }
    }


    @Override
    public void removeLast() {
        if (head.next != tail) {
            Node<Entity> temp = tail.prev;
            temp.prev.next = tail;
            tail.prev = temp.prev;
        }
    }

    @Override
    public Entity getFirst() {
        return head.next.data;
    }

    @Override
    public Entity getLast() {
        return tail.prev.data;
    }

    @Override
    public Entity get(Entity element) {
        if (head.next != tail) {
            Node<Entity> current = head.next;
            while (current != tail) {
                if (current.data.equals(element)) {
                    return current.data;
                }
                current = current.next;
            }
        }
        return null;
    }

    protected Node<Entity> getNode(Entity element) {
        if (head.next != tail) {
            Node<Entity> current = head.next;
            while (current != tail) {
                if (current.data.equals(element)) {
                    return current;
                }
                current = current.next;
            }
        }
        return tail;
    }

    protected Node<Entity> getNode(long id) {
        if (head.next != tail) {
            Node<Entity> current = head.next;
            while (current != tail) {
                if (current.data.getId() == id) {
                    return current;
                }
                current = current.next;
            }
        }
        return tail;
    }

    public boolean update(Entity element) {
        Node<Entity> current = getNode(element);
        if (current != null) {
            current.data = element;
            return true;
        } else {
            throw new RuntimeException("User not found");
        }
    }

    /**
     * Removes the first occurrence of the specified element.
     * Returns true if this list contained the specified element.
     * (use 'equals' method to check an occurrence)
     */
    @Override
    public Entity get(long id) {
        if (head.next != tail) {
            Node<Entity> current = head.next;
            while (current != tail) {
                if (current.data.getId() == id) {
                    return current.data;
                }
                current = current.next;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Entity element) {
        if (element == null) {
            return false;
        }
        if (head.next != tail) {
            Node<Entity> current = head.next;
            while (current != tail) {
                if (current.data.equals(element)) {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                    return true;
                }
                current = current.next;
            }
        }
        return false;
    }

    @Override
    public boolean remove(long id) {

        if (head.next != tail) {
            Node<Entity> current = head.next;
            while (current != tail) {
                if (current.data.getId() == id) {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                    return true;
                }
                current = current.next;
            }
        }
        return false;

    }


    @Override
    public void clear() {
        head.next = tail;
        tail.prev = head;
        Node<Entity> temp = head.next;
        while (temp.next != null) {
            temp.prev = null;
            temp = temp.next;
            temp.prev.next = null;
        }
        System.gc();
    }

    @Override
    public int size() {
        int size = 0;
        if (head.next != tail) {
            Node<Entity> current = head.next;
            while (current != tail) {
                size++;
                current = current.next;
            }
        }
        return size;
    }

    @Override
    public String toString() {
        Node<Entity> current = head.next;
        if (head.next == tail)
            return "Empty\n";
        StringBuilder s;
        s = new StringBuilder(": ");
        while (current != tail) {
            //Print each node and then go to next.
            s.append("[ ").append(current.data).append("], ");
            current = current.next;
        }
        s.append("}");
        return s.toString();
    }

    @Override
    public Entity[] toArray() {
        Entity[] array = (Entity[]) java.lang.reflect.Array.newInstance(_class, size());
        Node<Entity> current = head.next;
        for (int i = 0; i < this.size(); i++) {
            array[i] = current.data;
            current = current.next;
        }
        return array;
    }

    @Override
    public Iterator<Entity> iterator() {
        return new IteratorImpl();
    }

    static class Node<Entity extends BaseEntity> {
        protected Node<Entity> next;
        protected Node<Entity> prev;
        protected Entity data;

        protected Node(Entity item) {
            this.data = item;
        }

        protected Node() {
        }

    }

    class IteratorImpl implements Iterator<Entity> {

        protected Node<Entity> current;
        protected boolean b;

        protected IteratorImpl() {
            current = head;
        }

        public boolean hasNext() {
            return current.next != tail;
// ...
        }

        /**
         * returns the next element
         * if there is no element to return this method
         * <p>
         * must throw java.util.NoSuchElementException
         */
        public Entity next() {

            if (hasNext()) {
                current = current.next;
                b = true;
                return current.data;
            } else throw new NoSuchElementException();
        }

        /**
         * removes the element that was last returned by the next method
         */
        public void remove() {
            if (b) {
                current.prev.next = current.next;
                current.next.prev = current.prev;
                b = false;
            } else throw new IllegalStateException("Cannot remove element twice");
        }
    }

}
