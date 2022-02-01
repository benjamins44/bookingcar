package bco.bookingcar.exceptions;

public class MissingMandatoryValueException extends BusinessException {
    public MissingMandatoryValueException(String attributeName) {
        super(BusinessException.builder()
                .message(StandardMessage.MANDATORY_NULL)
                .argument("attributeName", attributeName)
        );
    }
}
