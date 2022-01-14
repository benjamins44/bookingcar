package bco.bookingcar.domain.unit.booking;

import bco.bookingcar.domain.booking.BookedCar;
import bco.bookingcar.domain.booking.BookingCarAttempt;
import bco.bookingcar.domain.booking.CarNotAvailableException;
import bco.bookingcar.domain.ports.StoreBookedCar;
import bco.bookingcar.domain.ports.StoreCars;
import bco.bookingcar.domain.unit.InjectDomainObjects;
import bco.bookingcar.domain.unit.car.CarFactory;
import bco.bookingcar.domain.unit.customer.CustomerFactory;
import bco.bookingcar.domain.unit.shared.PeriodFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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

    @Nested
    @DisplayName("Book")
    @InjectDomainObjects
    class BookTest {

        private StoreBookedCar storeBookedCar;
        private StoreCars storeCars;

        @BeforeEach
        void setup(StoreBookedCar storeBookedCar, StoreCars storeCars) {
            this.storeBookedCar = storeBookedCar;
            this.storeCars = storeCars;
        }

        @Test
        void customer_can_booking_a_car_when_all_cars_are_available() throws CarNotAvailableException {
            var newBookedCar = BookingCarAttemptFactory.build();
            BookedCar bookedCar = newBookedCar.book(storeBookedCar);

            assertThat(bookedCar.getId()).isNotNull();
        }

        @Test
        void customer_can_not_booking_a_car_when_it_is_reserved_on_the_period() {
            var nbOfCars = 5;
            var cars = storeCars.addAll(CarFactory.buildCars(nbOfCars));
            var bookedPeriod = PeriodFactory.build();
            var carsModified = StoreBookedCarUtils.changeAndSaveBookedPeriodOfCars(cars, List.of(bookedPeriod), storeBookedCar);

            Assertions.assertThatThrownBy(() -> {

                        var bookingCarAttempt = BookingCarAttempt.builder()
                                .car(carsModified.get(0))
                                .customer(CustomerFactory.build())
                                .period(bookedPeriod)
                                .build();
                        bookingCarAttempt.book(storeBookedCar);
                    }
            ).isInstanceOf(CarNotAvailableException.class);
        }
    }

}
