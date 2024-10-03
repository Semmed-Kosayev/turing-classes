package az.edu.turing.module02.part01.lesson02;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;

public class LinkedList<T> {
    LinkNode<T> head;
    int size = 0;

    public void addHead(T item) {
        LinkNode<T> itemNode = new LinkNode<>(item);
        itemNode.next = head;
        head = itemNode;
        size++;
    }

    public void addTail(T item) {
        LinkNode<T> itemNode = new LinkNode<>(item);

        if (head == null) {
            head = itemNode;
            size++;
            return;
        }

        LinkNode<T> lastNode = getLastNode();
        lastNode.next = itemNode;
        size++;
    }

    public void removeHead() {
        if (head == null) throw new NoSuchElementException();

        head = head.next;
        size--;
    }

    public void removeTail() {
        if (head == null) throw new NoSuchElementException();

        if (size == 1) {
            head = null;
            size--;
            return;
        }

        LinkNode<T> nodeIterator = head;

        for (int i = 0; i < size - 2; i++) {
            nodeIterator = nodeIterator.next;
        }

        nodeIterator.next = null;
        size--;
    }

    public void insert(int index, T item) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();

        if (index == 0) {
            addHead(item);
            return;
        }

        LinkNode<T> nodeIterator = head;

        for (int i = 0; i < index - 1; i++) {
            nodeIterator = nodeIterator.next;
        }

        LinkNode<T> newNode = new LinkNode<>(item);
        newNode.next = nodeIterator.next;
        nodeIterator.next = newNode;
        size++;
    }

    public void update(int index, T item) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();

        if (index == 0) {
            head.value = item;
            return;
        }

        LinkNode<T> nodeIterator = head;

        for (int i = 0; i < index; i++) {
            nodeIterator = nodeIterator.next;
        }

        nodeIterator.value = item;
    }

    public T delete(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();

        T valueToBeDeleted;

        if (index == 0) {
            valueToBeDeleted = head.value;
            removeHead();
            return valueToBeDeleted;
        }

        LinkNode<T> nodeIterator = head;

        for (int i = 0; i < index - 1; i++) {
            nodeIterator = nodeIterator.next;
        }

        LinkNode<T> nodeToBeDeleted = nodeIterator.next;
        valueToBeDeleted = nodeToBeDeleted.value;
        nodeIterator.next = nodeToBeDeleted.next;

        size--;
        return valueToBeDeleted;
    }

    public boolean delete(T item) {
        if (head.value.equals(item)) {
            removeHead();
            return true;
        }

        LinkNode<T> current = head.next;
        LinkNode<T> previous = head;

        for (int i = 0; i < size - 1; i++) {
            if (current.value.equals(item)) {
                previous.next = current.next;
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }

        return false;
    }

    public void deleteAll() {
        head = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public LinkNode<T> getLastNode() {
        LinkNode<T> nodeIterator = head;

        while (nodeIterator.hasNext()) nodeIterator = nodeIterator.next;

        return nodeIterator;
    }

    public LinkNode<T> getFirstNode() {
        return head;
    }

    public Object[] toArray() {
        Object[] valueArray = new Object[size];

        if (size == 0) return valueArray;

        int i = 0;
        for (LinkNode<T> nodeIterator = head; nodeIterator != null; nodeIterator = nodeIterator.next) {
            valueArray[i++] = nodeIterator.value;
        }

        return valueArray;
    }

    @Override
    public String toString() {
        if (head == null) return "[]";

        StringBuilder sb = new StringBuilder();
        LinkNode<T> nodeIterator = head;

        sb.append('[');
        while (nodeIterator != null) {
            sb.append(nodeIterator.value);
            if (nodeIterator.hasNext()) sb.append(", ");
            nodeIterator = nodeIterator.next;
        }
        sb.append("]");

        return sb.toString();
    }
}
