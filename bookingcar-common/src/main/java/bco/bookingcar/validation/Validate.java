package bco.bookingcar.validation;

import java.math.BigDecimal;

import static org.apache.commons.lang3.Validate.isTrue;

public interface Validate {

    static void isPositiveAndNotNull(Long id, String message) {
        if (id == null) {
            throw new NullPointerException();
        }
        isTrue(id > 0L, message);
    }

    static void isPositiveAndNotNull(BigDecimal id, String message) {
        if (id == null) {
            throw new NullPointerException();
        }
        isTrue(BigDecimal.ZERO.compareTo(id) < 0, message);
    }

    static void isPositiveAndNotNull(Integer id, String message) {
        if (id == null) {
            throw new NullPointerException();
        }
        isTrue(id > 0, message);
    }

    static void notNegativeAndNull(BigDecimal id, String message) {
        if (id == null) {
            throw new NullPointerException();
        }
        isTrue(BigDecimal.ZERO.compareTo(id) <= 0, message);
    }

    static void isPositive(Long id, String message) {
        if (id == null) {
            return;
        }
        isTrue(id > 0L, message);
    }
}
