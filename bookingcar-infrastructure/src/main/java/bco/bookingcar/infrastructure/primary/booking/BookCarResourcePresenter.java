package bco.bookingcar.infrastructure.primary.booking;

import org.springframework.stereotype.Component;

import bco.bookingcar.application.booking.BookCarPresenter;
import bco.bookingcar.application.booking.BookCarResponse;
import bco.bookingcar.infrastructure.primary.shared.PeriodResource;

@Component
public class BookCarResourcePresenter implements BookCarPresenter<BookedCarResource> {

    private BookedCarResource viewModel;

    @Override
    public void present(BookCarResponse response) {
        var bookedCar = response.getBookedCar();
        this.viewModel = BookedCarResource.builder()
                .id(bookedCar.getId())
                .idCar(bookedCar.getIdCar())
                .idCustomer(bookedCar.getIdCustomer())
                .period(PeriodResource.fromDomain(bookedCar.getPeriod()))
                .build();
    }

    @Override
    public BookedCarResource viewModel() {
        return viewModel;
    }
}
