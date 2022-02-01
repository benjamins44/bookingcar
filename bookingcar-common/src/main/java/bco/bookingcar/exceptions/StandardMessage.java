package bco.bookingcar.exceptions;

public enum StandardMessage implements MessageException {
    INTERNAL_SERVER_ERROR("error.internal-server"),
    MANDATORY_NULL("error.mandatory-null"),
    MANDATORY_BLANK("error.mandatory-blank"),
    NUMBER_NOT_POSITIVE("error.number-not-positive"),
    NUMBER_NEGATIVE("error.number-negative");

    private final String messageKey;

    private StandardMessage(String code) {
        this.messageKey = code;
    }

    @Override
    public String getMessageKey() {
        return messageKey;
    }
}
