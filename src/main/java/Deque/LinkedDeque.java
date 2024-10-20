package Deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A collection that supports insertion, removal, and access at both ends.
 *
 * @param <T> a generic parameter of type T
 * @author Sandra Gran
 * @version 10-15-2024
 */
public class LinkedDeque<T> implements DequeInterface<T>{
    /** the first node in the deque */
    private DLNode<T> front;
    /** the last node in the deque */
    private DLNode<T> back;
    /** the size of the deque */
    private int size;

    /**
     * Creates an empty LinkedDeque object
     */
    public LinkedDeque() {
        front = null;
        back = null;
        size = 0;
    }

    /**
     * Adds a new entry to the front of this deque.
     *
     * @param newEntry An object to be added.
     */
    @Override
    public void addToFront(T newEntry) {
        if (isEmpty()) {
            DLNode<T> newNode = new DLNode<>(newEntry); //calling node constructor that sets front&back to null
            front = back = newNode;
        } else {
            DLNode<T> newNode = new DLNode<>(null, newEntry, front);
            front.setPreviousNode(newNode);
            front = newNode;
        }
        size = size + 1;
    }

    /**
     * Adds a new entry to the back of this deque.
     *
     * @param newEntry An object to be added.
     */
    @Override
    public void addToBack(T newEntry) {
        if (isEmpty()) {
            DLNode<T> newNode = new DLNode<>(newEntry); //calling node constructor that sets front&back to null
            front = back = newNode;
        } else {
            DLNode<T> newNode = new DLNode<>(back, newEntry, null);
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
            throw new EmptyQueueException("Deque is empty.");
        }

        T oldFrontData = getFront();
        DLNode<T> oldFront = front;
        front = front.getNextNode();

        if (front == null) { // if the deque is now empty
            back = null;
        } else {
            oldFront.setNextNode(null); //sever connect of old front node to disconnect this node entirely
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
            throw new EmptyQueueException("Deque is empty.");
        }

        T oldBackData = back.getData();
        DLNode<T> oldBackNode = back;
        back = back.getPreviousNode();

        if (back == null) {
            front = null;
        } else {
            oldBackNode.setPreviousNode(null); // sever the old connection forwards to disconnect this node entirely
            back.setNextNode(null); // sever the connection to previous back node
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
            throw new EmptyQueueException("Deque is empty.");
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
            throw new EmptyQueueException("Deque is empty.");
        }
        return back.getData();
    }

    /**
     * Removes all the entries from the queue.
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


    /**
     * Retrieves an iterator to iterate through deque.
     *
     * @return an iterator for use.
     */
    @Override
    public Iterator<T> getIterator() {
        return iterator();
    }

    /**
     * Iterator object for the LinkedDeque
     */
    private class LinkedDequeIterator implements Iterator<T> {
        /** current element in the iteration*/
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
    private static class DLNode<T> {

        /** node before this one in deque */
        private DLNode<T> previousNode;
        /** data stored in node */
        private T nodeData;
        /** node after this one in deque */
        private DLNode<T> nextNode;


        /**
         * Partial Constructor: Constructs new node with both pointers set to null
         *
         * @param newNodeData data to store in node
         */
        public DLNode(T newNodeData) {
            this(null, newNodeData, null);
        }

        /**
         * Full constructor: Can set node data, and both pointers to a specific node
         *
         * @param previousNode node previous to this node
         * @param nodeData data to store in node
         * @param nextNode node after this node
         */
        public DLNode(DLNode<T> previousNode, T nodeData, DLNode<T> nextNode) {
            setPreviousNode(previousNode);
            setData(nodeData);
            setNextNode(nextNode);
        }

        /**
         * returns data stored in node
         *
         * @return node data
         */
        public T getData() {
            return nodeData;
        }

        /**
         * sets the data stored in the node
         *
         * @param newNodeData data to store in node
         */
        public void setData(T newNodeData){
            nodeData = newNodeData;
        }

        /**
         * returns the node previous to this one
         *
         * @return previous node
         */
        public DLNode<T> getPreviousNode(){
            return previousNode;
        }

        /**
         * sets a node as the node previous to this one
         *
         * @param previousNode node to set to previous node
         */
        public void setPreviousNode(DLNode<T> previousNode) {
            this.previousNode = previousNode;
        }

        /**
         * returns the node next to this one
         *
         * @return node next to this one
         */
        public DLNode<T> getNextNode(){
            return nextNode;
        }

        /**
         * set a node as the node next to this one
         *
         * @param nextNode node tos et to the next node
         */
        public void setNextNode(DLNode<T> nextNode) {
            this.nextNode = nextNode;
        }

    }
}
