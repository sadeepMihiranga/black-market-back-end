package lk.sadeep.balckmarket.exception;

public class BadApiRequestException extends RuntimeException
{
    public BadApiRequestException(String message) {
        super(message);
    }

    public BadApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
