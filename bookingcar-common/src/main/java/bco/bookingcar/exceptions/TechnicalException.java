package bco.bookingcar.exceptions;

import lombok.Getter;

@Getter
public class TechnicalException extends RuntimeException {
    private String message;
    private Throwable cause;

    public TechnicalException(TechnicalExceptionBuilder builder) {
        super((builder.message != null ? builder.message : StandardMessage.INTERNAL_SERVER_ERROR).getMessageKey(), builder.cause);
    }

    public TechnicalException(Exception cause) {
        super(StandardMessage.INTERNAL_SERVER_ERROR.getMessageKey(), cause);
    }

    public String getReason() {
        if (cause != null) return cause.getMessage();
        return null;
    }

    public static TechnicalExceptionBuilder builder() {
        return new TechnicalExceptionBuilder();
    }

    public static class TechnicalExceptionBuilder {
        private MessageException message;
        private Throwable cause;

        public TechnicalExceptionBuilder message(MessageException message) {
            this.message = message;
            return this;
        }

        public TechnicalExceptionBuilder cause(Throwable cause) {
            this.cause = cause;
            return this;
        }

        public TechnicalException build() {
            return new TechnicalException(this);
        }
    }
}
