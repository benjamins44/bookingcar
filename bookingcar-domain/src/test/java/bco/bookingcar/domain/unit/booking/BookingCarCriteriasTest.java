package bco.bookingcar.domain.unit.booking;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class BookingCarCriteriasTest {
    @Test
    @DisplayName("Period must not be null")
    void period_must_not_be_null() {
        assertThatThrownBy(() -> SearchAvailableCarsCriteriasFactory.build().withPeriod(null)).isInstanceOf(NullPointerException.class);
    }
}