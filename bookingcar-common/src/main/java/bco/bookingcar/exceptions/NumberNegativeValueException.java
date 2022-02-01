package bco.bookingcar.exceptions;

public class NumberNegativeValueException extends BusinessException {
    public NumberNegativeValueException(String attributeName) {
        super(BusinessException.builder()
                .message(StandardMessage.NUMBER_NEGATIVE)
                .argument("attributeName", attributeName)
        );
    }
}
