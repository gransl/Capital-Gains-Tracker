package Deque;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class LinkedDequeTest {
    static LinkedDeque<Double> doubleDeque;
    static LinkedDeque<Integer> emptyDeque;

    @BeforeAll
    static void setUp() {
        emptyDeque = new LinkedDeque<>();
        doubleDeque = new LinkedDeque<>();
        doubleDeque.addToFront(100.2);
        doubleDeque.addToFront(13.3);
        doubleDeque.addToBack(43.75);
        doubleDeque.addToBack(20.342);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addToFront() {
        assertEquals(13.3, doubleDeque.getFront());
    }

    @Test
    void addToBack() {
        assertEquals(20.342, doubleDeque.getBack());
    }

    @Test
    void removeFront() {
        LinkedDeque<Integer> intDeque = new LinkedDeque<>();
        intDeque.addToFront(13);
        intDeque.addToFront(43);
        intDeque.addToFront(2000);
        assertEquals(2000, intDeque.removeFront());
        assertEquals(43, intDeque.getFront());
        assertEquals(13, intDeque.getBack());
        assertEquals(43, intDeque.removeFront());
        assertEquals(13, intDeque.getFront());
        assertEquals(13, intDeque.getBack());
        assertEquals(13, intDeque.removeFront());
        assertThrows(EmptyQueueException.class, () -> intDeque.removeFront());

    }

    @Test
    void removeBack() {
        LinkedDeque<String> stringDeque = new LinkedDeque<>();
        assertThrows(EmptyQueueException.class, () -> stringDeque.getFront());
        stringDeque.addToFront("goodbye");
        stringDeque.addToFront("good day");
        stringDeque.addToFront("hello");
        assertEquals("goodbye", stringDeque.removeBack());
        assertEquals("hello", stringDeque.getFront());
        assertEquals("good day", stringDeque.getBack());
        assertEquals("good day", stringDeque.removeBack());
        assertEquals("hello", stringDeque.getFront());
        assertEquals("hello", stringDeque.getBack());
        assertEquals("hello", stringDeque.removeBack());
        assertThrows(EmptyQueueException.class, () -> stringDeque.removeBack());
    }

    @Test
    void isEmpty() {
        LinkedDeque<String> stringDeque = new LinkedDeque<>();
        assertTrue(stringDeque.isEmpty());
        stringDeque.addToFront("calendar");
        assertFalse(stringDeque.isEmpty());
    }

    @Test
    void getFront() {
        LinkedDeque<String> stringDeque = new LinkedDeque<>();
        assertThrows(EmptyQueueException.class, () -> stringDeque.getFront());
        stringDeque.addToFront("goodbye");
        stringDeque.addToFront("good day");
        stringDeque.addToFront("hello");
        assertEquals("hello", stringDeque.getFront());
    }

    @Test
    void getBack() {
        LinkedDeque<String> stringDeque = new LinkedDeque<>();
        assertThrows(EmptyQueueException.class, () -> stringDeque.getBack());
        stringDeque.addToFront("goodbye");
        stringDeque.addToFront("good day");
        stringDeque.addToFront("hello");
        assertEquals("goodbye", stringDeque.getBack());
    }

    @Test
    void clear() {
        LinkedDeque<String> stringDeque = new LinkedDeque<>();
        stringDeque.addToBack("clock");
        stringDeque.addToFront("fire");
        stringDeque.addToBack("chair");
        stringDeque.clear();
        assertEquals(0, stringDeque.size());
    }

    @Test
    void size() {
        LinkedDeque<Integer> intDeque = new LinkedDeque<>();
        assertEquals(0,intDeque.size());
        intDeque.addToFront(13);
        assertEquals(1,intDeque.size());
        intDeque.addToBack(43);
        assertEquals(2 ,intDeque.size());
        intDeque.addToFront(2000);
        assertEquals(3,intDeque.size());
        intDeque.removeFront();
        intDeque.removeBack();
        assertEquals(1,intDeque.size());
        intDeque.removeFront();
        assertEquals(0,intDeque.size());
        assertEquals(4, doubleDeque.size());
    }

    @Test
    void iterator() {
        Iterator<Double> dblIterator1 = doubleDeque.iterator();
        assertTrue(dblIterator1.hasNext());
        assertEquals(13.3, dblIterator1.next());
        assertTrue(dblIterator1.hasNext());
        assertEquals(100.2, dblIterator1.next());
        assertTrue(dblIterator1.hasNext());
        assertEquals(43.75, dblIterator1.next());
        assertTrue(dblIterator1.hasNext());
        assertEquals(20.342, dblIterator1.next());
        assertFalse(dblIterator1.hasNext());
        assertThrows(NoSuchElementException.class, () -> dblIterator1.next());

    }

    @Test
    void getIterator() {
        Iterator<Double> dblIterator2 = doubleDeque.getIterator();
        Iterator<Double> dblIterator3 = doubleDeque.getIterator();
        assertTrue(dblIterator2.hasNext());
        assertEquals(13.3, dblIterator2.next());
        assertTrue(dblIterator2.hasNext());
        assertEquals(100.2, dblIterator2.next());
        assertTrue(dblIterator2.hasNext());
        assertEquals(43.75, dblIterator2.next());
        assertTrue(dblIterator3.hasNext());
        assertEquals(13.3, dblIterator3.next());
        assertTrue(dblIterator2.hasNext());
        assertEquals(20.342, dblIterator2.next());
        assertFalse(dblIterator2.hasNext());
        assertTrue(dblIterator3.hasNext());
    }

    private void throwException(){
        throw new EmptyQueueException();
    }

    @Test
    void extraLinkedDequeTest() {
        assertThrows(EmptyQueueException.class, () -> throwException());
    }
}