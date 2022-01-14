package bco.bookingcar.domain.unit.booking;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DisplayName("BookingCarAttempt tests")
public class BookingCarAttemptTest {

    @Nested
    @DisplayName("BookingCarAttempt invariant tests")
    class InvariantTest {

        @Test
        @DisplayName("Check valid object")
        void check_valid_object() {
            BookingCarAttemptFactory.build();
        }

        @Test
        @DisplayName("Car must not be null")
        void car_must_not_be_null() {
            assertThatThrownBy(() -> BookingCarAttemptFactory.build().withCar(null)).isInstanceOf(NullPointerException.class);
        }

        @Test
        @DisplayName("Customer must not be null")
        void customer_must_not_be_null() {
            assertThatThrownBy(() -> BookingCarAttemptFactory.build().withCustomer(null)).isInstanceOf(NullPointerException.class);
        }

        @Test
        @DisplayName("Period must not be null")
        void period_must_not_be_null() {
            assertThatThrownBy(() -> BookingCarAttemptFactory.build().withPeriod(null)).isInstanceOf(NullPointerException.class);
        }
    }
}
