package bco.bookingcar.exceptions;

import java.io.Serializable;

public interface MessageException extends Serializable {
    String getMessageKey();
}
