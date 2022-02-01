package bco.bookingcar.domain.unit.booking;

import bco.bookingcar.exceptions.MissingMandatoryValueException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DisplayName("BookedCarCreatedEvent entity tests")
public class BookedCarCreatedEventTest {

    @Nested
    @DisplayName("BookedCarCreatedEvent invariant tests")
    class InvariantTest {

        @Test
        void check_valid_object() {
            assertThatCode(BookedCarCreatedEventFactory::build).doesNotThrowAnyException();
        }

        @Test
        void car_must_not_be_null() {
            assertThatThrownBy(() -> BookedCarCreatedEventFactory.build().withCar(null)).isInstanceOf(MissingMandatoryValueException.class);
        }

        @Test
        void customer_must_not_be_null() {
            assertThatThrownBy(() -> BookedCarCreatedEventFactory.build().withCustomer(null)).isInstanceOf(MissingMandatoryValueException.class);
        }

        @Test
        void period_must_not_be_null() {
            assertThatThrownBy(() -> BookedCarCreatedEventFactory.build().withPeriod(null)).isInstanceOf(MissingMandatoryValueException.class);
        }
    }
}
