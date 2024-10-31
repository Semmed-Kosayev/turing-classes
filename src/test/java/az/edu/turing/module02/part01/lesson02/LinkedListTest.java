package az.edu.turing.module02.part01.lesson02;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListTest {
    LinkedList<Integer> list = new LinkedList<>();

    @Test
    public void testAddHead() {
        assertEquals(0, list.size());
        list.addHead(10);
        assertEquals(10, list.getFirstNode().value);
        assertEquals(1, list.size());
    }

    @Test
    public void testAddTailEmptyList() {
        list.addTail(20);
        assertEquals(20, list.getFirstNode().value);
        assertEquals(1, list.size());
    }

    @Test
    public void testAddTailNonEmptyList() {
        list.addHead(10);
        list.addTail(20);
        assertEquals(20, list.getLastNode().value);
        assertEquals(2, list.size());
    }

    @Test
    public void testRemoveHead() {
        assertEquals(Optional.empty(), list.removeHead());
        list.addHead(10);
        list.addTail(40);
        assertEquals(Optional.of(10), list.removeHead());
        assertEquals(1, list.size());
        assertEquals(Optional.of(40), list.removeHead());
        assertEquals(0, list.size());
        assertNull(list.getFirstNode());
    }

    @Test
    public void testRemoveTail() {
        list.addHead(10);
        list.addTail(20);
        assertEquals(Optional.of(20), list.removeTail());
        assertEquals(1, list.size());
        assertEquals(Optional.of(10), list.removeTail());
        assertEquals(Optional.empty(), list.removeTail());
    }

    @Test
    public void testInsertAtValidIndex() {
        list.addHead(10);
        list.addTail(20);
        list.insert(1, 15);
        assertEquals(15, list.getFirstNode().next.value);
        assertEquals(3, list.size());
    }

    @Test
    public void testInsertAtHead() {
        list.addHead(20);
        list.insert(0, 10);
        assertEquals(10, list.getFirstNode().value);
        assertEquals(2, list.size());
    }

    @Test
    public void testInsertAtTail() {
        list.addHead(10);
        list.insert(2, 30); // Insert at index size (tail)
        assertEquals(30, list.getLastNode().value);
        assertEquals(2, list.size());
    }

    @Test
    public void testUpdateHead() {
        list.update(0, 20);
        list.addHead(10);
        assertEquals(10, list.getFirstNode().value);
    }

    @Test
    public void testUpdateTail() {
        list.addHead(10);
        list.addTail(30);
        list.update(1, 40);
        assertEquals(40, list.getLastNode().value);
    }

    @Test
    public void testUpdate() {
        list.addHead(10);
        list.addTail(0);
        list.addTail(30);
        list.update(1, 20);
        assertEquals(20, list.getFirstNode().next.value);
    }

    @Test
    public void testDeleteByIndex() {
        list.addHead(10);
        list.addTail(20);
        list.addTail(30);
        assertEquals(Optional.of(20), list.delete(1));
        assertEquals(2, list.size());
        assertEquals(Optional.empty(), list.delete(5)); // Out of bounds
    }

    @Test
    public void testDeleteByItem() {
        LinkedList<String> listOfStrings = new LinkedList<>();
        listOfStrings.addHead("10");
        listOfStrings.addTail("20");
        listOfStrings.addTail("30");
        assertTrue(listOfStrings.delete("20"));
        assertFalse(listOfStrings.delete("40")); // Item not found
        assertEquals(2, listOfStrings.size());
    }

    @Test
    public void testDeleteAll() {
        list.addHead(10);
        list.deleteAll();
        assertEquals(0, list.size());
        assertEquals(Optional.empty(), list.removeHead()); // Removing from empty list
    }

    @Test
    public void testToArray() {
        list.addHead(10);
        list.addTail(20);
        Object[] expectedArray = {10, 20};
        assertArrayEquals(expectedArray, list.toArray());
    }

    @Test
    public void testToString() {
        assertEquals("[]", list.toString());
        list.addHead(10);
        list.addTail(20);
        assertEquals("[10, 20]", list.toString());
    }

    @AfterEach
    public void resetList(){
        list.deleteAll();
    }
}
