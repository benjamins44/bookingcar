package bco.bookingcar.application.booking;

import java.util.List;
import java.util.stream.Collectors;

import bco.bookingcar.annotation.ApplicationService;
import bco.bookingcar.application.SearchAvailableCarsUseCase;
import bco.bookingcar.domain.BookingCar;
import bco.bookingcar.domain.ports.StoreCars;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@ApplicationService
public class SearchAvailableCarsUseCaseImpl implements SearchAvailableCarsUseCase, SearchAvailableCars {

    private BookingCar bookingCar;
    private StoreCars storeCars;

    @Override
    public void execute(SearchAvailableCarsRequest request, SearchAvailableCarsPresenter presenter) {
        var response = SearchAvailableCarsResponse.builder().availableCarList(searchAvailableCars(request)).request(request).build();
        presenter.present(response);
    }

    @Override
    public List<AvailableCar> searchAvailableCars(SearchAvailableCarsRequest request) {
        return storeCars.getAll().stream().filter(car -> !bookingCar.carIsBookedOn(car, request.getPeriod()))
                .map(car -> AvailableCar.builder().car(car).period(request.getPeriod()).build()).toList();
    }
}

