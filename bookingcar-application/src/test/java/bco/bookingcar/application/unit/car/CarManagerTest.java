package bco.bookingcar.application.unit.car;

import bco.bookingcar.application.car.BcoCarManager;
import bco.bookingcar.application.car.CarNotFoundException;
import bco.bookingcar.application.CarManager;
import bco.bookingcar.domain.ports.StoreCars;
import bco.bookingcar.domain.unit.InjectDomainObjects;
import bco.bookingcar.domain.unit.car.CarFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@InjectDomainObjects
@DisplayName("Car manager test")
public class CarManagerTest {

    private StoreCars storeCars;
    private CarManager carManager;

    @BeforeEach
    void setup(StoreCars storeCars) {
        this.storeCars = storeCars;
        carManager = new BcoCarManager(storeCars);
    }

    @Nested
    @DisplayName("Find by id")
    class FindByIdTest {
        @Test
        void find_by_id_must_return_car() throws CarNotFoundException {
            var car = storeCars.add(CarFactory.build());

            var carFound = carManager.findById(car.getId());

            assertThat(carFound).isEqualTo(car);
        }

        @Test
        void find_by_id_shoud_return_exception_if_car_not_exist() {
            assertThatThrownBy(() -> {
                var car = CarFactory.build().withId(UUID.randomUUID());
                var carFound = carManager.findById(car.getId());
            }).isInstanceOf(CarNotFoundException.class);
        }
    }
}
