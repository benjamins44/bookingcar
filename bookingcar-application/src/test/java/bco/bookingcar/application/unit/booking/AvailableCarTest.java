package bco.bookingcar.application.unit.booking;

import bco.bookingcar.exceptions.BusinessException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DisplayName("AvailableCar invariant tests")
class AvailableCarTest {
    @Test
    @DisplayName("Car must not be null")
    void id_car_must_not_be_null() {
        assertThatThrownBy(() -> AvailableCarFactory.build().withCar(null)).isInstanceOf(BusinessException.class);
    }

    @Test
    @DisplayName("Period must not be null")
    void period_must_not_be_null() {
        assertThatThrownBy(() -> AvailableCarFactory.build().withPeriod(null)).isInstanceOf(BusinessException.class);
    }
}