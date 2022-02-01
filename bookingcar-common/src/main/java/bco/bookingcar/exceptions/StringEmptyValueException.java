package bco.bookingcar.exceptions;

public class StringEmptyValueException extends BusinessException {
    public StringEmptyValueException(String attributeName) {
        super(BusinessException.builder()
                .message(StandardMessage.MANDATORY_BLANK)
                .argument("attributeName", attributeName)
        );
    }
}
