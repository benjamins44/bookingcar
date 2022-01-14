package bco.bookingcar.domain.unit.booking;

import bco.bookingcar.domain.SearchAvailableCars;
import bco.bookingcar.domain.booking.BcoSearchAvailableCars;
import bco.bookingcar.domain.ports.StoreBookedCar;
import bco.bookingcar.domain.ports.StoreCars;
import bco.bookingcar.domain.shared.Period;
import bco.bookingcar.domain.unit.InjectDomainObjects;
import bco.bookingcar.domain.unit.car.CarFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@InjectDomainObjects
@DisplayName("Search available cars test")
class BcoSearchAvailableCarsTest {
    private StoreBookedCar storeBookedCar;
    private StoreCars storeCars;
    private SearchAvailableCars searchAvailableCars;

    @BeforeEach
    void setup(StoreBookedCar storeBookedCar, StoreCars storeCars) {
        this.storeBookedCar = storeBookedCar;
        this.storeCars = storeCars;
        searchAvailableCars = new BcoSearchAvailableCars(storeCars, storeBookedCar);
    }

    @Nested
    @DisplayName("Search available cars")
    class SearchAvailableCarsTest {
        @Test
        @DisplayName("All cars are available if none are booked")
        void all_cars_are_available_if_none_are_booked() {
            var nbOfCars = 5;
            storeCars.addAll(CarFactory.buildCars(nbOfCars));

            var availableCars = searchAvailableCars.search(SearchAvailableCarsCriteriasFactory.build());

            assertThat(availableCars.size()).isEqualTo(nbOfCars);
        }

        @Test
        @DisplayName("4 of 5 cars are available if one is booked during the period")
        void four_of_5_cars_are_available_if_one_is_booked_during_the_period() {
            var nbOfCars = 5;
            var cars = storeCars.addAll(CarFactory.buildCars(nbOfCars));
            var criterias = SearchAvailableCarsCriteriasFactory.build();
            StoreBookedCarUtils.changeAndSaveBookedPeriodOfCars(cars, List.of(criterias.getPeriod()), storeBookedCar);

            var availableCars = searchAvailableCars.search(criterias);

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

            var availableCars = searchAvailableCars.search(criterias);

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

            var availableCars = searchAvailableCars.search(criterias);

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

            var availableCars = searchAvailableCars.search(criterias);

            assertThat(availableCars.size()).isEqualTo(nbOfCars - 2);
        }
    }
}