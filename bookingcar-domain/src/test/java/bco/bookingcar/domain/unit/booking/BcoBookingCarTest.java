package bco.bookingcar.domain.unit.booking;

import bco.bookingcar.domain.BookingCar;
import bco.bookingcar.domain.booking.BcoBookingCar;
import bco.bookingcar.domain.booking.BookedCar;
import bco.bookingcar.domain.booking.BookingCarAttempt;
import bco.bookingcar.domain.booking.CarNotAvailableException;
import bco.bookingcar.domain.ports.StoreBookedCar;
import bco.bookingcar.domain.ports.StoreCars;
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
class BcoBookingCarTest {
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
    @DisplayName("Search available cars")
    class BookingCarTest {
        @Test
        @DisplayName("All cars are available if none are booked")
        void all_cars_are_available_if_none_are_booked() {
            var nbOfCars = 5;
            storeCars.addAll(CarFactory.buildCars(nbOfCars));

            var availableCars = bookingCar.search(SearchAvailableCarsCriteriasFactory.build());

            assertThat(availableCars.size()).isEqualTo(nbOfCars);
        }

        @Test
        @DisplayName("4 of 5 cars are available if one is booked during the period")
        void four_of_5_cars_are_available_if_one_is_booked_during_the_period() {
            var nbOfCars = 5;
            var cars = storeCars.addAll(CarFactory.buildCars(nbOfCars));
            var criterias = SearchAvailableCarsCriteriasFactory.build();
            StoreBookedCarUtils.changeAndSaveBookedPeriodOfCars(cars, List.of(criterias.getPeriod()), storeBookedCar);

            var availableCars = bookingCar.search(criterias);

            assertThat(availableCars.size()).isEqualTo(nbOfCars - 1);
        }

        @Test
        void all_cars_are_available_if_one_is_booked_after_the_period() {
            var nbOfCars = 5;
            var cars = storeCars.addAll(CarFactory.buildCars(nbOfCars));
            var criterias = SearchAvailableCarsCriteriasFactory.build();
            StoreBookedCarUtils.changeAndSaveBookedPeriodOfCars(cars,
                    List.of(Period.builder()
                            .startDateTime(criterias.getPeriod().getEndDateTime().plusHours(1L))
                            .endDateTime(criterias.getPeriod().getEndDateTime().plusHours(2L))
                            .build()),
                    storeBookedCar
            );

            var availableCars = bookingCar.search(criterias);

            assertThat(availableCars.size()).isEqualTo(nbOfCars);
        }

        @Test
        void all_cars_are_available_if_one_is_booked_before_the_period() {
            var nbOfCars = 5;
            var cars = storeCars.addAll(CarFactory.buildCars(nbOfCars));
            var criterias = SearchAvailableCarsCriteriasFactory.build();
            StoreBookedCarUtils.changeAndSaveBookedPeriodOfCars(cars,
                    List.of(Period.builder()
                            .startDateTime(criterias.getPeriod().getStartDateTime().minusHours(2L))
                            .endDateTime(criterias.getPeriod().getStartDateTime().minusHours(1L))
                            .build()),
                    storeBookedCar
            );

            var availableCars = bookingCar.search(criterias);

            assertThat(availableCars.size()).isEqualTo(nbOfCars);
        }

        @Test
        void three_of_5_cars_are_available_if_two_are_booked_during_the_period() {
            var nbOfCars = 5;
            var cars = storeCars.addAll(CarFactory.buildCars(nbOfCars));

            var criterias = SearchAvailableCarsCriteriasFactory.build();
            StoreBookedCarUtils.changeAndSaveBookedPeriodOfCars(cars,
                    List.of(Period.builder()
                                    .startDateTime(criterias.getPeriod().getStartDateTime().minusHours(2L))
                                    .endDateTime(criterias.getPeriod().getStartDateTime().plusHours(1L))
                                    .build(),
                            Period.builder()
                                    .startDateTime(criterias.getPeriod().getEndDateTime().minusHours(2L))
                                    .endDateTime(criterias.getPeriod().getEndDateTime().plusHours(1L))
                                    .build()
                    ),
                    storeBookedCar
            );

            var availableCars = bookingCar.search(criterias);

            assertThat(availableCars.size()).isEqualTo(nbOfCars - 2);
        }
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