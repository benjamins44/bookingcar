package bco.bookingcar.application.unit.car;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bco.bookingcar.application.car.GetCarRequest;
import bco.bookingcar.exceptions.BusinessException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DisplayName("GetCarRequest invariant tests")
class GetCarRequestTest {
    @Test
    @DisplayName("carId must not be null")
    void id_car_must_not_be_null() {
        assertThatThrownBy(() -> GetCarRequest.builder().idCar(null).build()).isInstanceOf(BusinessException.class);
    }
}