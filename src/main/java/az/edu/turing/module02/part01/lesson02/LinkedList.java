package az.edu.turing.module02.part01.lesson02;

import java.util.Optional;

public class LinkedList<T> {

    private LinkNode<T> head;
    private int size = 0;

    public T addHead(final T item) {
        final LinkNode<T> itemNode = new LinkNode<>(item);
        itemNode.next = head;
        head = itemNode;
        size++;

        return item;
    }

    public T addTail(final T item) {
        final LinkNode<T> itemNode = new LinkNode<>(item);

        if (head == null) return addHead(item);

        getLastNode().next = itemNode;
        size++;
        return item;
    }

    public Optional<T> removeHead() {
        if (size == 0) return Optional.empty();

        final T removedValue = head.value;

        if (size == 1) {
            deleteAll();
            return Optional.of(removedValue);
        }

        head = head.next;
        size--;

        return Optional.of(removedValue);
    }

    public Optional<T> removeTail() {
        if (size == 0) return Optional.empty();

        return delete(size - 1);
    }

    public T insert(final int index, final T item) {
        if (index <= 0) return addHead(item);
        if (index >= size) return addTail(item);

        LinkNode<T> current = head;

        for (int i = 0; i < index - 1; i++) current = current.next;

        final LinkNode<T> newNode = new LinkNode<>(item);
        newNode.next = current.next;
        current.next = newNode;
        size++;

        return item;
    }

    public T update(final int index, final T item) {
        if (size == 0) return addHead(item);

        if (index <= 0) {
            head.value = item;
            return item;
        }

        if (index >= size) {
            getLastNode().value = item;
            return item;
        }

        LinkNode<T> current = head;

        for (int i = 0; i < index; i++) current = current.next;

        current.value = item;

        return item;
    }

    public Optional<T> delete(final int index) {
        if (index < 0 || index >= size) return Optional.empty();
        if (index == 0) return removeHead();

        final T valueToBeDeleted;

        LinkNode<T> current = head;

        for (int i = 0; i < index - 1; i++) current = current.next;

        LinkNode<T> nodeToBeDeleted = current.next;
        valueToBeDeleted = nodeToBeDeleted.value;

        current.next = nodeToBeDeleted.next;
        size--;
        return Optional.of(valueToBeDeleted);
    }

    public boolean delete(final T item) {
        if (size == 0) return false;
        if (head.value.equals(item)) return removeHead().isPresent();

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
        LinkNode<T> current = head;

        for (int i = 0; i < size - 1; i++) current = current.next;

        return current;
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