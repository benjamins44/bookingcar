package bco.bookingcar.exceptions;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class BusinessException extends Exception {
    private final Map<String, String> arguments;
    private final String message;
    private final Throwable cause;

    public BusinessException(BusinessExceptionBuilder builder) {
        super((builder.message != null ? builder.message : StandardMessage.INTERNAL_SERVER_ERROR).getMessageKey(), builder.cause);
        this.message = builder.message.getMessageKey();
        this.arguments = builder.arguments;
        this.cause = builder.cause;
    }

    public static BusinessExceptionBuilder builder() {
        return new BusinessExceptionBuilder();
    }

    public String getReason() {
        if (cause != null) return cause.getMessage();
        return null;
    }

    public static class BusinessExceptionBuilder {
        private final Map<String, String> arguments = new HashMap<>();
        private MessageException message;
        private Throwable cause;

        public BusinessExceptionBuilder argument(String key, Object value) {
            arguments.put(key, getStringValue(value));
            return this;
        }

        private String getStringValue(Object value) {
            if (value == null) {
                return "null";
            }

            return value.toString();
        }

        public BusinessExceptionBuilder message(MessageException message) {
            this.message = message;
            return this;
        }

        public BusinessExceptionBuilder cause(Throwable cause) {
            this.cause = cause;
            return this;
        }

        public BusinessException build() {
            return new BusinessException(this);
        }
    }
}
