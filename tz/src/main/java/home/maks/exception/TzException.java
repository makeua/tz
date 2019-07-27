package home.maks.exception;

public class TzException extends RuntimeException {

    public TzException() {
        super();
    }

    public TzException(String message) {
        super(message);
    }

    public TzException(String message, Throwable cause) {
        super(message, cause);
    }

    public TzException(Throwable cause) {
        super(cause);
    }
}
