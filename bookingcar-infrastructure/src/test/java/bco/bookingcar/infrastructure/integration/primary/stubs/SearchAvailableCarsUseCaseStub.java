package bco.bookingcar.infrastructure.integration.primary.stubs;

import java.util.List;

import bco.bookingcar.annotation.Stub;
import bco.bookingcar.application.SearchAvailableCarsUseCase;
import bco.bookingcar.application.booking.AvailableCar;
import bco.bookingcar.application.booking.SearchAvailableCarsPresenter;
import bco.bookingcar.application.booking.SearchAvailableCarsRequest;
import bco.bookingcar.application.booking.SearchAvailableCarsResponse;

@Stub
public class SearchAvailableCarsUseCaseStub implements SearchAvailableCarsUseCase {

    private List<AvailableCar> availableCars = List.of();

    public void reset() {
        this.availableCars = List.of();
    }

    public void setAvailableCars(List<AvailableCar> availableCars) {
        this.availableCars = availableCars;
    }

    @Override
    public void execute(SearchAvailableCarsRequest request, SearchAvailableCarsPresenter presenter) {
        presenter.present(
                SearchAvailableCarsResponse.builder()
                        .availableCarList(availableCars)
                        .request(request)
                        .build()
        );
    }
}
