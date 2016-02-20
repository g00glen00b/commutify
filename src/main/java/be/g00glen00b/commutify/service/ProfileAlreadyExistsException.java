package be.g00glen00b.commutify.service;

public class ProfileAlreadyExistsException extends RuntimeException {
    public ProfileAlreadyExistsException() {
    }

    public ProfileAlreadyExistsException(String message) {
        super(message);
    }
}
