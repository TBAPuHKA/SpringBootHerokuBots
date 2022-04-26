package wtf.bot.hs.exception;

public class AppException extends RuntimeException {

    public AppException(String msg) {
        super(msg);
    }

    public AppException(Throwable t) {
        super(t);
    }

    public AppException() {
        super();
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }
}
