package bco.bookingcar.application.unit.booking;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bco.bookingcar.exceptions.MissingMandatoryValueException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DisplayName("PlanningBookedCar entity invariant tests")
class PlanningBookedCarTest {
    @Test
    @DisplayName("customer must not be null")
    void customer_must_not_be_null() {
        assertThatThrownBy(() -> PlanningBookedCarFactory.build().withCustomer(null)).isInstanceOf(MissingMandatoryValueException.class);
    }

    @Test
    @DisplayName("period must not be null")
    void period_must_not_be_null() {
        assertThatThrownBy(() -> PlanningBookedCarFactory.build().withPeriod(null)).isInstanceOf(MissingMandatoryValueException.class);
    }
}