package az.edu.turing.module02.part01.lesson02;

import java.util.Arrays;

public class LinkedListApp {

    public static void main(String[] args) {
        LinkedList<String> lst = new LinkedList<>();

        lst.addTail("S");
        lst.addTail("b");
        lst.addHead("F");

        System.out.println(lst);
        System.out.println(lst.size());

        lst.insert(1, "SALAM");
        System.out.println(lst);
        System.out.println(lst.size());

        lst.update(2, "Aleykum");
        System.out.println(lst);

//        lst.removeHead();
//        System.out.println(lst);
//        System.out.println(lst.size());
//
//        lst.removeTail();
//        System.out.println(lst);
//        System.out.println(lst.size());

        lst.delete(1);
        System.out.println(lst);
        System.out.println(lst.size());

        System.out.println(lst.delete("F"));
        System.out.println(lst);

        System.out.println(lst.delete(1));
        System.out.println(lst);

        lst.addTail("S");
        lst.addTail("b");
        lst.addHead("F");
        System.out.println(lst);
        System.out.println(lst.toArray());
        System.out.println(Arrays.toString(lst.toArray()));

        lst.deleteAll();
        System.out.println(lst);

        System.out.println(Arrays.toString(lst.toArray()));
    }
}