package bco.bookingcar.domain.unit.car;

import bco.bookingcar.exceptions.MissingMandatoryValueException;
import bco.bookingcar.exceptions.NumberNotPositiveValueException;
import bco.bookingcar.exceptions.StringEmptyValueException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DisplayName("Car entity invariant tests")
class CarTest {
    @Test
    @DisplayName("Check valid object")
    void check_valid_object() {
        assertThatCode(CarFactory::build).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Brand must not be empty")
    void brand_is_not_empty() {
        assertThatThrownBy(() -> CarFactory.build().withBrand("")).isInstanceOf(StringEmptyValueException.class);
    }

    @Test
    @DisplayName("Brand must not be null")
    void brand_is_not_null() {
        assertThatThrownBy(() -> CarFactory.build().withBrand(null)).isInstanceOf(MissingMandatoryValueException.class);
    }

    @Test
    @DisplayName("Model must not be empty")
    void model_is_not_empty() {
        assertThatThrownBy(() -> CarFactory.build().withModel("")).isInstanceOf(StringEmptyValueException.class);
    }

    @Test
    @DisplayName("Model must not be null")
    void model_must_not_be_null() {
        assertThatThrownBy(() -> CarFactory.build().withModel(null)).isInstanceOf(MissingMandatoryValueException.class);
    }

    @Test
    @DisplayName("Number of place must be positive")
    void nb_of_place_must_be_positive() {
        assertThatThrownBy(() -> CarFactory.build().withNumberOfPlace(0)).isInstanceOf(NumberNotPositiveValueException.class);
    }

    @Test
    @DisplayName("Number of place must not be null")
    void nb_of_place_must_not_be_null() {
        assertThatThrownBy(() -> CarFactory.build().withNumberOfPlace(null)).isInstanceOf(MissingMandatoryValueException.class);
    }

    @Test
    @DisplayName("Category must not be null")
    void category_must_not_be_null() {
        assertThatThrownBy(() -> CarFactory.build().withCategory(null)).isInstanceOf(MissingMandatoryValueException.class);
    }
}
