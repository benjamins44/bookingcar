package bco.bookingcar.domain.unit.voiture;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DisplayName("CarCategory entity invariant tests")
public class CategoryCarTest {
    @Test
    @DisplayName("Check valid object")
    void check_valid_object() {
        CarCategoryFactory.build(null);
    }

    @Test
    @DisplayName("Label must not be empty")
    void label_must_not_be_empty() {
        assertThatThrownBy(() -> {
            CarCategoryFactory.build(null).withLabel("");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Label must not be null")
    void label_must_not_be_null() {
        assertThatThrownBy(() -> {
            CarCategoryFactory.build(null).withLabel(null);
        }).isInstanceOf(NullPointerException.class);
    }
}
