package app.manager.client.exeption;

public class ResourceExistedException extends RuntimeException {
    public ResourceExistedException(String message) {
        super(message);
    }
}
