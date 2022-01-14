package bco.bookingcar.car;

import bco.bookingcar.annotation.ApplicationService;
import bco.bookingcar.domain.car.Car;
import bco.bookingcar.domain.ports.StoreCars;
import bco.bookingcar.primary.CarManager;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@ApplicationService
public class BcoCarManager implements CarManager {

    private StoreCars storeCars;

    @Override
    public Car findById(UUID carId) throws CarNotFoundException {
        return storeCars.getById(carId).orElseThrow(CarNotFoundException::new);
    }
}
