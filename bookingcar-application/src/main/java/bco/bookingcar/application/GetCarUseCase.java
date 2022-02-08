package bco.bookingcar.application;

import bco.bookingcar.application.car.CarNotFoundException;
import bco.bookingcar.application.car.GetCarPresenter;
import bco.bookingcar.application.car.GetCarRequest;

public interface GetCarUseCase {
    void execute(GetCarRequest request, GetCarPresenter presenter) throws CarNotFoundException;
}
