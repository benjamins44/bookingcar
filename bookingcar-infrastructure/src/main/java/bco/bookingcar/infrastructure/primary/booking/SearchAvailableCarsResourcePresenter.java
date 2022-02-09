package bco.bookingcar.infrastructure.primary.booking;

import org.springframework.stereotype.Component;

import bco.bookingcar.application.booking.SearchAvailableCarsPresenter;
import bco.bookingcar.application.booking.SearchAvailableCarsResponse;

@Component
public class SearchAvailableCarsResourcePresenter implements SearchAvailableCarsPresenter<SearchAvailableCarsResultResource> {

    private SearchAvailableCarsResultResource viewModel;

    @Override
    public void present(SearchAvailableCarsResponse response) {
        this.viewModel = SearchAvailableCarsResultResource.builder()
                .result(AvailableCarResource.fromDomain(response.getAvailableCarList(), response.getRequest().getCustomerId()))
                .period(response.getRequest().getPeriod())
                .customerId(response.getRequest().getCustomerId())
                .build();
    }

    @Override
    public SearchAvailableCarsResultResource viewModel() {
        return viewModel;
    }
}
