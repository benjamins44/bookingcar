package bco.bookingcar.validation;

import bco.bookingcar.exceptions.*;

import java.math.BigDecimal;

public final class Assert {

    public static void notNull(String field, Object input) throws BusinessException {
        if (input == null) {
            throw new MissingMandatoryValueException(field);
        }
    }

    public static void notBlank(String field, String input) throws BusinessException {
        notNull(field, input);
        if (field != null && input.isBlank()) {
            throw new StringEmptyValueException(input);
        }
    }

    public static void isPositive(String field, BigDecimal number) throws BusinessException {
        if (field != null && BigDecimal.ZERO.compareTo(number) >= 0) {
            throw new NumberNotPositiveValueException(field);
        }
    }

    public static void isNotNegative(String field, BigDecimal number) throws BusinessException {
        if (field != null && BigDecimal.ZERO.compareTo(number) < 0) {
            throw new NumberNegativeValueException(field);
        }
    }

    public static DefaultAsserter field(String fieldName, Object value) {
        return new DefaultAsserter(fieldName, value);
    }

    public static class DefaultAsserter {
        private final String fieldName;
        private final Object value;

        private DefaultAsserter(String fieldName, Object value) {
            this.fieldName = fieldName;
            this.value = value;
        }

        public DefaultAsserter notNull() throws BusinessException {
            Assert.notNull(fieldName, value);
            return this;
        }
    }

    public static StringAsserter field(String fieldName, String value) {
        return new StringAsserter(fieldName, value);
    }

    public static class StringAsserter {
        private final String fieldName;
        private final String value;

        private StringAsserter(String fieldName, String value) {
            this.fieldName = fieldName;
            this.value = value;
        }

        public StringAsserter notBlank() throws BusinessException {
            Assert.notBlank(fieldName, value);
            return this;
        }

        public StringAsserter notNull() throws BusinessException {
            Assert.notNull(fieldName, value);
            return this;
        }
    }

    public static NumberAsserter field(String fieldName, BigDecimal value) {
        return new NumberAsserter(fieldName, value);
    }

    public static NumberAsserter field(String fieldName, Long value) {
        return new NumberAsserter(fieldName, value != null ? BigDecimal.valueOf(value) : null);
    }

    public static NumberAsserter field(String fieldName, Integer value) {
        return new NumberAsserter(fieldName, value != null ? BigDecimal.valueOf(value) : null);
    }

    public static class NumberAsserter {
        private final String fieldName;
        private final BigDecimal value;

        private NumberAsserter(String fieldName, BigDecimal value) {
            this.fieldName = fieldName;
            this.value = value;
        }

        public NumberAsserter notNull() throws BusinessException {
            Assert.notNull(fieldName, value);
            return this;
        }

        public NumberAsserter isPositive() throws BusinessException {
            Assert.isPositive(fieldName, value);
            return this;
        }

        public NumberAsserter isNotNegative() throws BusinessException {
            Assert.isNotNegative(fieldName, value);
            return this;
        }
    }
}
