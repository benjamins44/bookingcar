package bco.bookingcar.application.unit.booking;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bco.bookingcar.application.booking.BookCarRequest;
import bco.bookingcar.domain.unit.shared.PeriodFactory;
import bco.bookingcar.exceptions.MissingMandatoryValueException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class BookCarRequestTest {
    @Test
    @DisplayName("car id must not be null")
    void carId_must_not_be_null() {
        assertThatThrownBy(() -> validObject.withCarId(null)).isInstanceOf(MissingMandatoryValueException.class);
    }

    @Test
    @DisplayName("customer id must not be null")
    void customerId_must_not_be_null() {
        assertThatThrownBy(() -> validObject.withCustomerId(null)).isInstanceOf(MissingMandatoryValueException.class);
    }

    @Test
    @DisplayName("period must not be null")
    void period_must_not_be_null() {
        assertThatThrownBy(() -> validObject.withPeriod(null)).isInstanceOf(MissingMandatoryValueException.class);
    }

    private final BookCarRequest validObject = BookCarRequest.builder()
            .carId(UUID.randomUUID())
            .customerId(UUID.randomUUID())
            .period(PeriodFactory.build())
            .build();
}