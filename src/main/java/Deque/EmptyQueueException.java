package Deque;

/**
 * Thrown by methods to indicate when a Queue or Deque is Empty.
 */
public class EmptyQueueException extends RuntimeException {

    /**
     * Constructs a new EmptyQueueException with null as its error message string.
     */
    EmptyQueueException() {
        this(null);
    }


    /** Constructs a new EmptyQueueException with custom message as its error message string.
     * @param message error message
     */
    EmptyQueueException(String message) {
        super(message);
    }
}