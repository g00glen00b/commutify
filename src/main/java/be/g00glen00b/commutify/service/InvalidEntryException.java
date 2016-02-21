package be.g00glen00b.commutify.service;

public class InvalidEntryException extends RuntimeException {
    public InvalidEntryException() {
    }

    public InvalidEntryException(String message) {
        super(message);
    }
}
