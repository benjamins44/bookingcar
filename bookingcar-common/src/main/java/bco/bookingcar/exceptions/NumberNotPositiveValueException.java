package bco.bookingcar.exceptions;

public class NumberNotPositiveValueException extends BusinessException {
    public NumberNotPositiveValueException(String attributeName) {
        super(BusinessException.builder()
                .message(StandardMessage.NUMBER_NOT_POSITIVE)
                .argument("attributeName", attributeName)
        );
    }
}
