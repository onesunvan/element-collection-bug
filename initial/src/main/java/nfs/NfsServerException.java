package nfs;


public class NfsServerException extends RuntimeException {
    public NfsServerException() {
    }

    public NfsServerException(String message) {
        super(message);
    }

    public NfsServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public NfsServerException(Throwable cause) {
        super(cause);
    }
}
