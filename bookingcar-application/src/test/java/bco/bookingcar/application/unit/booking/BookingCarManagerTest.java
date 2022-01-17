package bco.bookingcar.application.unit.booking;

import bco.bookingcar.application.booking.BcoBookingCarManager;
import bco.bookingcar.application.car.BcoCarManager;
import bco.bookingcar.application.car.CarNotFoundException;
import bco.bookingcar.application.customer.BcoCustomerManager;
import bco.bookingcar.application.customer.CustomerNotFoundException;
import bco.bookingcar.application.primary.BookingCarManager;
import bco.bookingcar.domain.BookingCar;
import bco.bookingcar.domain.booking.BcoBookingCar;
import bco.bookingcar.domain.booking.CarNotAvailableException;
import bco.bookingcar.domain.car.Car;
import bco.bookingcar.domain.secondary.StoreBookedCar;
import bco.bookingcar.domain.secondary.StoreCars;
import bco.bookingcar.domain.secondary.StoreCustomers;
import bco.bookingcar.domain.shared.Period;
import bco.bookingcar.domain.unit.InjectDomainObjects;
import bco.bookingcar.domain.unit.booking.StoreBookedCarUtils;
import bco.bookingcar.domain.unit.car.CarFactory;
import bco.bookingcar.domain.unit.customer.CustomerFactory;
import bco.bookingcar.domain.unit.shared.PeriodFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@InjectDomainObjects
@DisplayName("Booking car test")
public class BookingCarManagerTest {

    private BookingCarManager bookingCarManager;
    private StoreCars storeCars;
    private StoreBookedCar storeBookedCar;
    private StoreCustomers storeCustomers;

    @BeforeEach
    void setup(StoreCars storeCars, StoreBookedCar storeBookedCar, StoreCustomers storeCustomers) {
        this.storeBookedCar = storeBookedCar;
        this.storeCars = storeCars;
        this.storeCustomers = storeCustomers;
        BookingCar bookingCar = new BcoBookingCar(storeCars, storeBookedCar);
        this.bookingCarManager = new BcoBookingCarManager(bookingCar, storeCars, new BcoCarManager(storeCars), new BcoCustomerManager(storeCustomers));
    }

    @Nested
    @DisplayName("Search available cars")
    class SearchavailableCarTest {
        @Test
        @DisplayName("All cars are available if none are booked")
        void all_cars_are_available_if_none_are_booked() {
            var nbOfCars = 5;
            storeCars.addAll(CarFactory.buildCars(nbOfCars));

            var availableCars = bookingCarManager.search(SearchAvailableCarsCriteriasFactory.build());

            assertThat(availableCars.size()).isEqualTo(nbOfCars);
        }

        @Test
        @DisplayName("4 of 5 cars are available if one is booked during the period")
        void four_of_5_cars_are_available_if_one_is_booked_during_the_period() {
            var nbOfCars = 5;
            var cars = storeCars.addAll(CarFactory.buildCars(nbOfCars));
            var criterias = SearchAvailableCarsCriteriasFactory.build();
            StoreBookedCarUtils.changeAndSaveBookedPeriodOfCars(cars, List.of(criterias.getPeriod()), storeBookedCar);

            var availableCars = bookingCarManager.search(criterias);

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

            var availableCars = bookingCarManager.search(criterias);

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

            var availableCars = bookingCarManager.search(criterias);

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

            var availableCars = bookingCarManager.search(criterias);

            assertThat(availableCars.size()).isEqualTo(nbOfCars - 2);
        }
    }

    @Nested
    @DisplayName("Book service test")
    class BookTest {

        private UUID idCustomer;
        private List<Car> cars;

        @BeforeEach
        void setup() {
            idCustomer = storeCustomers.add(CustomerFactory.build()).getId();
            var nbOfCars = 5;
            cars = storeCars.addAll(CarFactory.buildCars(nbOfCars));
        }

        @Test
        void customer_can_book_an_available_car() throws CarNotAvailableException, CarNotFoundException, CustomerNotFoundException {
            var period = PeriodFactory.build();
            var idCar = cars.get(0).getId();
            bookingCarManager.book(idCar, idCustomer, period);


            assertThat(storeBookedCar.getAll(period).stream().anyMatch(bookedCar ->
                            bookedCar.getIdCustomer().equals(idCustomer) &&
                                    bookedCar.getIdCar().equals(idCar)
                    )
            ).isTrue();
        }

        @Test
        void customer_can_not_book_an_unavailable_car() {
            var car = cars.get(0);
            var period = PeriodFactory.build();
            StoreBookedCarUtils.changeAndSaveBookedPeriodOfCars(cars,
                    List.of(Period.builder()
                            .startDateTime(period.getStartDateTime().minusHours(2L))
                            .endDateTime(period.getStartDateTime().plusHours(1L))
                            .build()),
                    storeBookedCar
            );

            assertThatThrownBy(() -> bookingCarManager.book(car.getId(), idCustomer, period))
                    .isInstanceOf(CarNotAvailableException.class);
        }
    }
}
