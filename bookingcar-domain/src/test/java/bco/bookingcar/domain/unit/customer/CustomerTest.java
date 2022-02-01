package bco.bookingcar.domain.unit.customer;

import bco.bookingcar.exceptions.MissingMandatoryValueException;
import bco.bookingcar.exceptions.StringEmptyValueException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DisplayName("Customer entity invariant tests")
class CustomerTest {
    @Test
    @DisplayName("Check valid object")
    void check_valid_object() {
        assertThatCode(CustomerFactory::build).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Firstname must not be empty")
    void firstname_is_not_empty() {
        assertThatThrownBy(() -> CustomerFactory.build().withFirstname("")).isInstanceOf(StringEmptyValueException.class);
    }

    @Test
    @DisplayName("Firstname must not be null")
    void firstname_is_not_null() {
        assertThatThrownBy(() -> CustomerFactory.build().withFirstname(null)).isInstanceOf(MissingMandatoryValueException.class);
    }

    @Test
    @DisplayName("Lastname must not be empty")
    void lastname_is_not_empty() {
        assertThatThrownBy(() -> CustomerFactory.build().withLastname("")).isInstanceOf(StringEmptyValueException.class);
    }

    @Test
    @DisplayName("Lastname must not be null")
    void lastname_must_not_be_null() {
        assertThatThrownBy(() -> CustomerFactory.build().withLastname(null)).isInstanceOf(MissingMandatoryValueException.class);
    }
}
