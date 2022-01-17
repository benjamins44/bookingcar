package bco.bookingcar.application.unit.booking;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DisplayName("AvailableCar entity invariant tests")
class AvailableCarTest {
    @Test
    @DisplayName("Id car must not be null")
    void id_car_must_not_be_null() {
        assertThatThrownBy(() -> AvailableCarFactory.build().withIdCar(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("Period must not be null")
    void period_must_not_be_null() {
        assertThatThrownBy(() -> AvailableCarFactory.build().withPeriod(null)).isInstanceOf(NullPointerException.class);
    }
}