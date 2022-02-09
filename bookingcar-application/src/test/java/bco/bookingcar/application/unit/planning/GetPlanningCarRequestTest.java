package bco.bookingcar.application.unit.planning;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bco.bookingcar.application.planning.GetPlanningCarRequest;
import bco.bookingcar.exceptions.BusinessException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DisplayName("GetPlanningCarRequest invariant tests")
class GetPlanningCarRequestTest {
    @Test
    @DisplayName("period must not be null")
    void period_must_not_be_null() {
        assertThatThrownBy(() -> GetPlanningCarRequest.builder().period(null).build()).isInstanceOf(BusinessException.class);
    }
}