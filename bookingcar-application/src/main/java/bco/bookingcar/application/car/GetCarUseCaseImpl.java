package bco.bookingcar.application.car;

import java.util.UUID;

import bco.bookingcar.annotation.ApplicationService;
import bco.bookingcar.application.GetCarUseCase;
import bco.bookingcar.domain.car.Car;
import bco.bookingcar.domain.ports.StoreCars;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@ApplicationService
public class GetCarUseCaseImpl implements GetCarUseCase, GetCar {

    private StoreCars storeCars;

    @Override
    public Car findById(UUID carId) throws CarNotFoundException {
        return storeCars.getById(carId).orElseThrow(() -> new CarNotFoundException(carId));
    }

    @Override
    public void execute(GetCarRequest request, GetCarPresenter presenter) throws CarNotFoundException {
        var carId = request.getIdCar();
        var car = this.findById(carId);
        presenter.present(GetCarResponse.builder().car(car).build());
    }
}
