package bco.bookingcar.infrastructure.primary.car;

import org.springframework.stereotype.Component;

import bco.bookingcar.application.car.GetCarPresenter;
import bco.bookingcar.application.car.GetCarResponse;

@Component
public class GetCarResourcePresenter implements GetCarPresenter<CarResource> {

    private CarResource viewModel;

    @Override
    public void present(GetCarResponse response) {
        this.viewModel = CarResource.fromDomain(response.getCar());
    }

    @Override
    public CarResource viewModel() {
        return viewModel;
    }
}
