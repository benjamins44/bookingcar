package bco.bookingcar.domain.unit.booking;

import bco.bookingcar.exceptions.MissingMandatoryValueException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DisplayName("BookedCar entity tests")
public class BookedCarTest {

    @Nested
    @DisplayName("BookedCar entity invariant tests")
    class InvariantTest {

        @Test
        @DisplayName("Check valid object")
        void check_valid_object() {
            assertThatCode(BookedCarFactory::build).doesNotThrowAnyException();
        }

        @Test
        @DisplayName("Id car must not be null")
        void id_car_must_not_be_null() {
            assertThatThrownBy(() -> BookedCarFactory.build().withIdCar(null)).isInstanceOf(MissingMandatoryValueException.class);
        }

        @Test
        @DisplayName("Id customer must not be null")
        void id_customer_must_not_be_null() {
            assertThatThrownBy(() -> BookedCarFactory.build().withIdCustomer(null)).isInstanceOf(MissingMandatoryValueException.class);
        }

        @Test
        @DisplayName("Period must not be null")
        void period_must_not_be_null() {
            assertThatThrownBy(() -> BookedCarFactory.build().withPeriod(null)).isInstanceOf(MissingMandatoryValueException.class);
        }
    }
}
