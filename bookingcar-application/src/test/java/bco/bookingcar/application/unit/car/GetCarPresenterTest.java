package bco.bookingcar.application.unit.car;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bco.bookingcar.application.GetCarUseCase;
import bco.bookingcar.application.car.GetCarUseCaseImpl;
import bco.bookingcar.application.car.CarNotFoundException;
import bco.bookingcar.application.car.GetCarPresenter;
import bco.bookingcar.application.car.GetCarRequest;
import bco.bookingcar.application.car.GetCarResponse;
import bco.bookingcar.domain.car.Car;
import bco.bookingcar.domain.ports.StoreCars;
import bco.bookingcar.domain.unit.InjectDomainObjects;
import bco.bookingcar.domain.unit.car.CarFactory;

import static org.assertj.core.api.Assertions.assertThat;

@InjectDomainObjects
@DisplayName("Get Car Use Case test")
public class GetCarPresenterTest implements GetCarPresenter<Car> {

    private StoreCars storeCars;
    private GetCarResponse getCarResponse;
    private GetCarUseCase getCarUseCase;

    @BeforeEach
    void setup(StoreCars storeCars) {
        this.storeCars = storeCars;
        this.getCarUseCase = new GetCarUseCaseImpl(storeCars);
    }

    @Override
    public void present(GetCarResponse response) {
        this.getCarResponse = response;
    }

    @Override
    public Car viewModel() {
        return this.getCarResponse.getCar();
    }

    @DisplayName("car is in the response")
    @Test
    void car_is_in_response() throws CarNotFoundException {
        var car = storeCars.add(CarFactory.build());

        getCarUseCase.execute(GetCarRequest.builder().idCar(car.getId()).build(), this);

        assertThat(getCarResponse.getCar()).isEqualTo(car);
    }
}
