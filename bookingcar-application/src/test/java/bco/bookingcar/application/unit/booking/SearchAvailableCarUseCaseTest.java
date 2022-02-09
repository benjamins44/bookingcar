package bco.bookingcar.application.unit.booking;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bco.bookingcar.application.SearchAvailableCarsUseCase;
import bco.bookingcar.application.booking.AvailableCar;
import bco.bookingcar.application.booking.SearchAvailableCarsUseCaseImpl;
import bco.bookingcar.application.booking.SearchAvailableCarsPresenter;
import bco.bookingcar.application.booking.SearchAvailableCarsResponse;
import bco.bookingcar.domain.BookingCar;
import bco.bookingcar.domain.booking.BookingCarImpl;
import bco.bookingcar.domain.ports.StoreBookedCars;
import bco.bookingcar.domain.ports.StoreCars;
import bco.bookingcar.domain.shared.Period;
import bco.bookingcar.domain.unit.InjectDomainObjects;
import bco.bookingcar.domain.unit.booking.StoreBookedCarUtils;
import bco.bookingcar.domain.unit.car.CarFactory;

import static org.assertj.core.api.Assertions.assertThat;

@InjectDomainObjects
@DisplayName("Search Available Car UseCase test")
public class SearchAvailableCarUseCaseTest implements SearchAvailableCarsPresenter<List<AvailableCar>> {

    private SearchAvailableCarsResponse searchAvailableCarsResponse;
    private SearchAvailableCarsUseCase searchAvailableCarsUseCase;
    private StoreCars storeCars;
    private StoreBookedCars storeBookedCars;

    @BeforeEach
    void setup(StoreCars storeCars, StoreBookedCars storeBookedCars) {
        this.storeBookedCars = storeBookedCars;
        this.storeCars = storeCars;
        BookingCar bookingCar = new BookingCarImpl(storeCars, storeBookedCars);
        this.searchAvailableCarsUseCase = new SearchAvailableCarsUseCaseImpl(bookingCar, storeCars);
    }

    @Override
    public void present(SearchAvailableCarsResponse response) {
        this.searchAvailableCarsResponse = response;
    }

    @Override
    public List<AvailableCar> viewModel() {
        return searchAvailableCarsResponse.getAvailableCarList();
    }

    @Test
    @DisplayName("All cars are available if none are booked")
    void all_cars_are_available_if_none_are_booked() {
        var nbOfCars = 5;
        storeCars.addAll(CarFactory.buildCars(nbOfCars));

        searchAvailableCarsUseCase.execute(SearchAvailableCarsCriteriasFactory.build(), this);

        assertThat(viewModel().size()).isEqualTo(nbOfCars);
    }

    @Test
    @DisplayName("4 of 5 cars are available if one is booked during the period")
    void four_of_5_cars_are_available_if_one_is_booked_during_the_period() {
        var nbOfCars = 5;
        var cars = storeCars.addAll(CarFactory.buildCars(nbOfCars));
        var criterias = SearchAvailableCarsCriteriasFactory.build();
        StoreBookedCarUtils.changeAndSaveBookedPeriodOfCars(cars, List.of(criterias.getPeriod()), storeBookedCars);

        searchAvailableCarsUseCase.execute(criterias, this);

        assertThat(viewModel().size()).isEqualTo(nbOfCars - 1);
    }

    @Test
    void all_cars_are_available_if_one_is_booked_after_the_period() {
        var nbOfCars = 5;
        var cars = storeCars.addAll(CarFactory.buildCars(nbOfCars));
        var criterias = SearchAvailableCarsCriteriasFactory.build();
        StoreBookedCarUtils.changeAndSaveBookedPeriodOfCars(cars, List.of(Period.builder().startDateTime(criterias.getPeriod().getEndDateTime().plusHours(1L))
                .endDateTime(criterias.getPeriod().getEndDateTime().plusHours(2L)).build()), storeBookedCars);

        searchAvailableCarsUseCase.execute(criterias, this);

        assertThat(viewModel().size()).isEqualTo(nbOfCars);
    }

    @Test
    void all_cars_are_available_if_one_is_booked_before_the_period() {
        var nbOfCars = 5;
        var cars = storeCars.addAll(CarFactory.buildCars(nbOfCars));
        var criterias = SearchAvailableCarsCriteriasFactory.build();
        StoreBookedCarUtils.changeAndSaveBookedPeriodOfCars(cars,
                List.of(Period.builder().startDateTime(criterias.getPeriod().getStartDateTime().minusHours(2L))
                        .endDateTime(criterias.getPeriod().getStartDateTime().minusHours(1L)).build()), storeBookedCars);

        searchAvailableCarsUseCase.execute(criterias, this);

        assertThat(viewModel().size()).isEqualTo(nbOfCars);
    }

    @Test
    void three_of_5_cars_are_available_if_two_are_booked_during_the_period() {
        var nbOfCars = 5;
        var cars = storeCars.addAll(CarFactory.buildCars(nbOfCars));

        var criterias = SearchAvailableCarsCriteriasFactory.build();
        StoreBookedCarUtils.changeAndSaveBookedPeriodOfCars(cars,
                List.of(Period.builder().startDateTime(criterias.getPeriod().getStartDateTime().minusHours(2L))
                                .endDateTime(criterias.getPeriod().getStartDateTime().plusHours(1L)).build(),
                        Period.builder().startDateTime(criterias.getPeriod().getEndDateTime().minusHours(2L))
                                .endDateTime(criterias.getPeriod().getEndDateTime().plusHours(1L)).build()), storeBookedCars);

        searchAvailableCarsUseCase.execute(criterias, this);

        assertThat(viewModel().size()).isEqualTo(nbOfCars - 2);
    }
}

