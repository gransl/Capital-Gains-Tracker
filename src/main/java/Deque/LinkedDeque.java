package Deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedDeque<T> implements DequeInterface<T>{
    /** the first node in the deque */
    private DLNode<T> front;
    /** the last node in the deque */
    private DLNode<T> back;
    /** the size of the deque */
    private int size;

    public LinkedDeque() {
        front = null;
        back = null;
        size = 0;
    }

    /**
     * Adds a new entry to the front of back of this deque.
     *
     * @param newEntry An object to be added.
     */
    @Override
    public void addToFront(T newEntry) {
        if (isEmpty()) {
            DLNode<T> newNode = new DLNode(newEntry); //calling node constructor that sets front&back to null
            front = back = newNode;
        } else {
            DLNode<T> newNode = new DLNode(null, newEntry, front);
            front.setPreviousNode(newNode);
            front = newNode;
        }
        size = size + 1;
    }

    @Override
    public void addToBack(T newEntry) {
        if (isEmpty()) {
            DLNode<T> newNode = new DLNode(newEntry); //calling node constructor that sets front&back to null
            front = back = newNode;
        } else {
            DLNode<T> newNode = new DLNode(back, newEntry, null);
            back.setNextNode(newNode);
            back = newNode;
        }
        size = size + 1;
    }

    /**
     * Removes and returns the front entry of this deque.
     *
     * @return The object at the front of the deque.
     * @throws EmptyQueueException if the deque is empty before the operation.
     */
    @Override
    public T removeFront() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }

        T oldFrontData = getFront();
        DLNode oldFront = front; //TODO: Do I need this? Does this get Garbage Collected w/o? pt 1
        front = front.getNextNode();

        if (front == null) { // if the deque is now empty
            back = null;
        } else {
            oldFront.setNextNode(null); //TODO: Do I need this? Does this get Garbage Collected w/o? pt 2
            front.setPreviousNode(null); // sever connection to previous front node
        }

        size = size - 1;

        return oldFrontData;
    }

    /**
     * Removes and returns the back entry of this deque.
     *
     * @return The object at the back of the deque.
     * @throws EmptyQueueException if the deque is empty before the operation.
     */
    @Override
    public T removeBack() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }

        T oldBackData = back.getData();
        DLNode<T> oldBackNode = back; //TODO: Do I need this? Does this get Garbage Collected w/o? pt 3
        back = back.getPreviousNode();

        if (back == null) {
            front = null;
        } else {
            oldBackNode.setPreviousNode(null); // severing old connection forwards //TODO: Do I need this pt 4
            back.setNextNode(null); // severing the connection to previous back node
        }

        size = size - 1;

        return oldBackData;
    }

    /**
     * Detects whether this deque is empty.
     *
     * @return True if deque is empty, or false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return (front == null);
    }

    /**
     * Returns the front entry's data.
     *
     * @return Entry data for front node.
     * @throws EmptyQueueException if deque is empty
     */
    @Override
    public T getFront() {
        if (front == null) {
            throw new EmptyQueueException();
        }
        return front.getData();
    }

    /**
     * Returns the back entry's data.
     *
     * @return Entry data for back node.
     * @throws EmptyQueueException if deque is empty
     */
    @Override
    public T getBack() {
        if (back == null) {
            throw new EmptyQueueException();
        }
        return back.getData();
    }

    /**
     * removes all the entries from the queue
     *
     * I chose this implementation rather than front = null, back = null because even though the user could no longer
     * access the items in the deque, I believe they still exist in memory because there are still references to the
     * nodes. I believe this method (and with the way I crafted the remove methods) all the references to the nodes
     * should be removed.
     *
     * Time Complexity: O(n)
     */
    @Override
    public void clear() {
        while (!isEmpty()) {
            removeFront();
        }
    }

    /**
     * This method gets and returns the size of the deque
     *
     * @return the number of nodes in the deque
     */
    public int size() {
        return size;
    }

    /**
     * Creates iterators to iterate through deque.
     *
     * @return Returns an iterator for use.
     */
    @Override
    public Iterator<T> iterator() {
        return new LinkedDequeIterator();
    }

    //TODO: why is this here? Same method different name?
    @Override
    public Iterator<T> getIterator() {
        return new LinkedDequeIterator();
    }

    /**
     * Iterator object for the  LinkedDeque
     */
    private class LinkedDequeIterator implements Iterator<T> {
        DLNode<T> current;

        /**
         * Constructor. Sets front of the deque to the first element of the iteration
         */
        LinkedDequeIterator() {
            current = front;
        }

        /**
         * This method determines if there is another element in the iteration
         *
         * @return true if there are more elements in the iteration, false if otherwise
         */
        public boolean hasNext() {
            return current != null;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        public T next() {
            if (current == null) {
                throw new NoSuchElementException("Iterator has reached the end of deque.");
            }

            T currentData = current.getData();
            current = current.getNextNode();

            return currentData;
        }
    }

    /** individual nodes of the LinkedDeque
     * @param <T> generic of type T
     */
    //TODO: Consider preconditions for setters and getters
    private static class DLNode<T> {
        private DLNode<T> previousNode;
        private T nodeData;
        private DLNode<T> nextNode;

        DLNode(T newNodeData) {
            this(null, newNodeData, null);
        }

        DLNode(DLNode<T> previousNode, T nodeData, DLNode<T> nextNode) {
            setPreviousNode(previousNode);
            setData(nodeData);
            setNextNode(nextNode);
        }

        public T getData() {
            return nodeData;
        }

        public void setData(T newNodeData){
            nodeData = newNodeData;
        }

        public DLNode<T> getPreviousNode(){
            return previousNode;
        }

        public void setPreviousNode(DLNode<T> previousNode) {
            this.previousNode = previousNode;
        }

        public DLNode<T> getNextNode(){
            return nextNode;
        }

        public void setNextNode(DLNode<T> nextNode) {
            this.nextNode = nextNode;
        }

    }
}
