package bco.bookingcar.application.unit.booking;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bco.bookingcar.exceptions.MissingMandatoryValueException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DisplayName("PlanningCar entity invariant tests")
class PlanningCarTest {
    @Test
    @DisplayName("Car must not be null")
    void car_must_not_be_null() {
        assertThatThrownBy(() -> PlanningCarFactory.build().withCar(null)).isInstanceOf(MissingMandatoryValueException.class);
    }

    @Test
    @DisplayName("PlanningBookedCar must not be null")
    void planning_booked_car_must_not_be_null() {
        assertThatThrownBy(() -> PlanningCarFactory.build().withPlanningBookedCar(null)).isInstanceOf(MissingMandatoryValueException.class);
    }
}