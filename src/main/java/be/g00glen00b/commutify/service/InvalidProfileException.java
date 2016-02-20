package be.g00glen00b.commutify.service;

public class InvalidProfileException extends RuntimeException {
    public InvalidProfileException() {
    }

    public InvalidProfileException(String message) {
        super(message);
    }
}
