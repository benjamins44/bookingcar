package bco.bookingcar.application.unit.booking;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bco.bookingcar.exceptions.MissingMandatoryValueException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class SearchAvailableCarsRequestTest {
    @Test
    @DisplayName("Period must not be null")
    void period_must_not_be_null() {
        assertThatThrownBy(() -> SearchAvailableCarsCriteriasFactory.build().withPeriod(null)).isInstanceOf(MissingMandatoryValueException.class);
    }

    @Test
    @DisplayName("customerId must not be null")
    void customerId_must_not_be_null() {
        assertThatThrownBy(() -> SearchAvailableCarsCriteriasFactory.build().withCustomerId(null)).isInstanceOf(MissingMandatoryValueException.class);
    }
}