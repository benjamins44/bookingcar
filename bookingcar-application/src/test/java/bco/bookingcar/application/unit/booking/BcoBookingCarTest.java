package bco.bookingcar.application.unit.booking;

import bco.bookingcar.booking.BcoBookingCar;
import bco.bookingcar.car.BcoCarManager;
import bco.bookingcar.car.CarNotFoundException;
import bco.bookingcar.customer.BcoCustomerManager;
import bco.bookingcar.customer.CustomerNotFoundException;
import bco.bookingcar.domain.SearchAvailableCars;
import bco.bookingcar.domain.booking.BcoSearchAvailableCars;
import bco.bookingcar.domain.booking.CarNotAvailableException;
import bco.bookingcar.domain.car.Car;
import bco.bookingcar.domain.ports.StoreBookedCar;
import bco.bookingcar.domain.ports.StoreCars;
import bco.bookingcar.domain.ports.StoreCustomers;
import bco.bookingcar.domain.shared.Period;
import bco.bookingcar.domain.unit.InjectDomainObjects;
import bco.bookingcar.domain.unit.booking.SearchAvailableCarsCriteriasFactory;
import bco.bookingcar.domain.unit.booking.StoreBookedCarUtils;
import bco.bookingcar.domain.unit.car.CarFactory;
import bco.bookingcar.domain.unit.customer.CustomerFactory;
import bco.bookingcar.domain.unit.shared.PeriodFactory;
import bco.bookingcar.primary.BookingCar;
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
public class BcoBookingCarTest {

    private BookingCar bookingCar;
    private StoreCars storeCars;
    private StoreBookedCar storeBookedCar;
    private StoreCustomers storeCustomers;

    @BeforeEach
    void setup(StoreCars storeCars, StoreBookedCar storeBookedCar, StoreCustomers storeCustomers) {
        this.storeBookedCar = storeBookedCar;
        this.storeCars = storeCars;
        this.storeCustomers = storeCustomers;
        SearchAvailableCars searchAvailableCars = new BcoSearchAvailableCars(storeCars, storeBookedCar);
        this.bookingCar = new BcoBookingCar(searchAvailableCars, storeBookedCar, new BcoCarManager(storeCars), new BcoCustomerManager(storeCustomers));
    }

    @Nested
    @DisplayName("Search service test")
    class SearchTest {
        @Test
        void search_must_return_a_list_of_availabilities() {
            var nbOfCars = 5;
            var cars = storeCars.addAll(CarFactory.buildCars(nbOfCars));

            var availableCars = bookingCar.search(SearchAvailableCarsCriteriasFactory.build());

            assertThat(availableCars.size()).isEqualTo(nbOfCars);
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
            bookingCar.book(idCar, idCustomer, period);


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

            assertThatThrownBy(() -> bookingCar.book(car.getId(), idCustomer, period))
                    .isInstanceOf(CarNotAvailableException.class);
        }
    }
}

