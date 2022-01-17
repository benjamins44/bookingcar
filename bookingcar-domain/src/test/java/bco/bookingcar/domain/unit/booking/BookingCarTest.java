package bco.bookingcar.domain.unit.booking;

import bco.bookingcar.domain.BookingCar;
import bco.bookingcar.domain.booking.BcoBookingCar;
import bco.bookingcar.domain.booking.BookedCar;
import bco.bookingcar.domain.booking.BookingCarAttempt;
import bco.bookingcar.domain.booking.CarNotAvailableException;
import bco.bookingcar.domain.secondary.StoreBookedCar;
import bco.bookingcar.domain.secondary.StoreCars;
import bco.bookingcar.domain.shared.Period;
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
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@InjectDomainObjects
@DisplayName("Booking car test")
class BookingCarTest {
    private StoreBookedCar storeBookedCar;
    private StoreCars storeCars;
    private BookingCar bookingCar;

    @BeforeEach
    void setup(StoreBookedCar storeBookedCar, StoreCars storeCars) {
        this.storeBookedCar = storeBookedCar;
        this.storeCars = storeCars;
        bookingCar = new BcoBookingCar(storeCars, storeBookedCar);
    }

    @Nested
    @DisplayName("Book")
    @InjectDomainObjects
    class BookTest {
        @Test
        void customer_can_booking_a_car_when_all_cars_are_available() throws CarNotAvailableException {
            BookedCar bookedCar = bookingCar.book(BookingCarAttemptFactory.build());

            assertThat(bookedCar.getId()).isNotNull();
        }

        @Test
        void customer_can_not_booking_a_car_when_it_is_booked_on_the_period() {
            var nbOfCars = 5;
            var cars = storeCars.addAll(CarFactory.buildCars(nbOfCars));
            var bookedPeriod = PeriodFactory.build();
            var carBooked = StoreBookedCarUtils.changeAndSaveBookedPeriodOfCars(cars, List.of(bookedPeriod), storeBookedCar);

            Assertions.assertThatThrownBy(() ->
                    bookingCar.book(BookingCarAttempt.builder()
                            .car(carBooked.get(0))
                            .customer(CustomerFactory.build().withId(UUID.randomUUID()))
                            .period(bookedPeriod)
                            .build())
            ).isInstanceOf(CarNotAvailableException.class);
        }
    }
}