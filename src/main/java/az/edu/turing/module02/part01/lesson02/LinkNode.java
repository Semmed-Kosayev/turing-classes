package az.edu.turing.module02.part01.lesson02;

public class LinkNode<T> {
    T value;
    LinkNode<T> next;

    public LinkNode(T value) {
        this.value = value;
    }

    public boolean hasNext() {
        return next != null;
    }
}
