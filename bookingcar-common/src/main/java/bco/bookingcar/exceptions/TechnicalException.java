package bco.bookingcar.exceptions;

public class TechnicalException extends RuntimeException {
    public TechnicalException(Exception exception) {
        super("Something is wrong !", exception);
    }
}
