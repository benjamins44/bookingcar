package bco.bookingcar.infrastructure.integration.primary.stubs;

import java.util.Optional;

import bco.bookingcar.annotation.Stub;
import bco.bookingcar.application.GetCarUseCase;
import bco.bookingcar.application.car.CarNotFoundException;
import bco.bookingcar.application.car.GetCarPresenter;
import bco.bookingcar.application.car.GetCarRequest;
import bco.bookingcar.application.car.GetCarResponse;
import bco.bookingcar.domain.car.Car;

@Stub
public class CarManagerStub implements GetCarUseCase {
    private Optional<Car> car = Optional.empty();

    public void setCar(Car car) {
        this.car = car == null ? Optional.empty() : Optional.of(car);
    }

    @Override
    public void execute(GetCarRequest request, GetCarPresenter presenter) throws CarNotFoundException {
        presenter.present(GetCarResponse.builder().car(car.orElseThrow(() -> new CarNotFoundException(request.getIdCar()))).build());
    }
}
