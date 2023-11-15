package org.cource.application.exception;

public class SubscribtionNotFoundException extends RuntimeException {

    public SubscribtionNotFoundException() {}
    public SubscribtionNotFoundException(String message) {
        super(message);
    }
}
