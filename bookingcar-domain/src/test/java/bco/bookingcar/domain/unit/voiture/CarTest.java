package bco.bookingcar.domain.unit.voiture;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DisplayName("Car entity invariant tests")
public class CarTest {
    @Test
    @DisplayName("Check valid object")
    void check_valid_object() {
        CarFactory.build(null);
    }

    @Test
    @DisplayName("Brand must not be empty")
    void brand_is_not_empty() {
        assertThatThrownBy(() -> {
            CarFactory.build(null).withBrand("");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Brand must not be null")
    void brand_is_not_null() {
        assertThatThrownBy(() -> {
            CarFactory.build(null).withBrand(null);
        }).isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("Model must not be empty")
    void model_is_not_empty() {
        assertThatThrownBy(() -> {
            CarFactory.build(null).withModel("");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Model must not be null")
    void model_must_not_be_null() {
        assertThatThrownBy(() -> {
            CarFactory.build(null).withModel(null);
        }).isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("Number of place must be positive")
    void nb_of_place_must_be_positive() {
        assertThatThrownBy(() -> {
            CarFactory.build(null).withNumberOfPlace(0);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Number of place must not be null")
    void nb_of_place_must_not_be_null() {
        assertThatThrownBy(() -> {
            CarFactory.build(null).withNumberOfPlace(null);
        }).isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("Category must not be null")
    void category_must_not_be_null() {
        assertThatThrownBy(() -> {
            CarFactory.build(null).withCategory(null);
        }).isInstanceOf(NullPointerException.class);
    }
}
