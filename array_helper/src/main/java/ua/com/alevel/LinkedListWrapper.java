package ua.com.alevel;

import java.util.*;

public class LinkedListWrapper<E> {

    Node<E> head = new Node<>();
    Node<E> tail = new Node<>();

    public LinkedListWrapper() {
        head.setNext(tail);
        tail.setPrev(head);
    }
   /* Node<E> head = new Node(null);
    Node<E> tail = new Node(null);

    public LinkedListWrapper(){
        head.next=tail;
        head.prev=null;
        tail.prev=head;
        tail.next=null;
    }

    public void addFirst(E element) {
        Node newNode = new Node(element);
        Node first = head.next;
        first.prev=newNode;
        newNode.next=first;
        newNode.prev=head;
        head.next=newNode;
    }

    public void addLast(E element) {
        Node newNode = new Node(element);
        Node last = tail.prev;
        newNode.prev=last;
        last.next=newNode;
        tail.prev=newNode;
        newNode.next=tail;
    }


    public void removeFirst() {
        if(head.next!=tail){
            Node temp = head.next;
            temp.next.prev=head;
            head.next=temp.next;
            System.gc();
        }
    }


    public void removeLast() {
        if(head.next!=tail){
            Node temp = tail.prev;
            temp.prev.next=tail;
            tail.prev=temp.prev;
            System.gc();
        }
    }

    public E getFirst() {
        return head.next.data;
    }

    public E getLast() {
        return tail.prev.data;
    }

    *//**
     * Returns the first occurrence of the specified element.
     * Returns null if no such element.
     * (use 'equals' method to check an occurrence)
     *
     * @param element
     *//*
    @Override
    public Object get(Object element) {
        return null;
    }

    *//**
     * Removes the first occurrence of the specified element.
     * Returns true if this list contained the specified element.
     * (use 'equals' method to check an occurrence)
     *
     * @param element
     *//*
    @Override
    public boolean remove(Object element) {
        return false;
    }

    public E get(E element) {
        if(head.next!=tail){
            Node current = head.next;
            while (current!=tail)
            {
                if (current.data.equals(element))
                {
                    return current.data;
                }
                current=current.next;
            }
        }
        return null;
    }

    public boolean remove(E element) {
        if (head.next != tail) {
            Node current = head.next;
            while (current != tail) {
                if(current.data==element) {
                    current.prev.next=current.next;
                    current.next.prev=current.prev;
                    System.gc();
                    return true;
                }
                current=current.next;
            }
        }
        return false;
    }

    public void clear() {
        head.next=tail;
        tail.prev=head;
        Node temp=head.next;
        while(temp.next!=null){
            temp.prev=null;
            temp=temp.next;
            temp.prev.next=null;
        }
        System.gc();
    }

    public int size() {
        int size=0;
        if (head.next!=tail){
            Node current = head.next;
            while(current!=tail){
                size++;
                current=current.next;
            }
        }
        return size;
    }

    public String toString() {
        Node current = head.next;
        if (head.next == tail)
            return "Doubly linked list is empty\n";
        StringBuilder s;
        s = new StringBuilder("Nodes of doubly linked list: ");
        while (current != tail) {
            //Print each node and then go to next.
            s.append("[ ").append(current.data).append("], ");
            current = current.next;
        }s.append("}");
        return s.toString();
    }

    public E[] toArray() {
        E[] array=new E[this.size()];
        Node current = head.next;
        for(int i=0;i<this.size();i++){
            array[i]=current.data;
            current=current.next;
        }
        return array;
    }

    public Iterator<E> iterator() {
        return new IteratorImpl();
    }

    class IteratorImpl implements Iterator<E> {

        protected Node<E> current;
        protected boolean b;

        public IteratorImpl(){
            current=head;
        }

        public boolean hasNext() {
            return current.next!=tail;
// ...
        }
        *//**
     * returns the next element
     * if there is no element to return this method
     * must throw java.util.NoSuchElementException
     *//*
        public E next() {

            if(hasNext()){
                current=current.next;
                b=true;
                return current.data;
            }
            else throw new NoSuchElementException();
        }
        */

    /**
     * removes the element that was last returned by the next method
     *//*
        public void remove() {
            if(b){
                current.prev.next=current.next;
                current.next.prev=current.prev;
                b=false;
                System.gc();
            }else throw new IllegalStateException("Hello, my sweet World!");
        }
    }*/

    static class Node<E> {
        protected Node<E> next;
        protected Node<E> prev;
        protected E data;

        protected Node(E item) {
            this.data = item;
        }

        protected Node() {
        }

        public void setNext(Node<E> _next) {
            if (_next == null) {
                throw new NullPointerException("Next element need to be not null. If you want to reset next to null use removeNext() method");
            }
            this.next = _next;
        }

        public void setPrev(Node<E> _prev) {
            if (_prev == null) {
                throw new NullPointerException("Previous element need to be not null. If you want to reset previous to null use removePrev() method");
            }
            this.prev = _prev;
        }
    }

}