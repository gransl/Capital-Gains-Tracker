package Deque;

public class EmptyQueueException extends RuntimeException {
    EmptyQueueException() {
        this("The queue is empty");
    }

    EmptyQueueException(String message) {
        super(message);
    }
}