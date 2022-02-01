package bco.bookingcar.infrastructure.integration.primary.error;

import bco.bookingcar.application.car.CarNotFoundException;
import bco.bookingcar.application.customer.CustomerNotFoundException;
import bco.bookingcar.exceptions.BusinessException;
import bco.bookingcar.exceptions.StandardMessage;
import bco.bookingcar.exceptions.TechnicalException;
import bco.bookingcar.infrastructure.primary.error.status.AppHttpStatusErrorGetter;
import bco.bookingcar.infrastructure.primary.error.status.HttpStatusErrorGetter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.UUID;

@DisplayName("HttpStatusErrorGetter component tests")
public class AppHttpStatusErrorGetterTest {
    private HttpStatusErrorGetter appHttpStatusErrorGetter = new AppHttpStatusErrorGetter();

    @Test
    void default_http_status_is_bad_request() {
        Assertions.assertThat(appHttpStatusErrorGetter.getStatut(BusinessException.builder().message(StandardMessage.MANDATORY_BLANK).build())).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void not_found_car_exception_return_not_found_status() {
        Assertions.assertThat(appHttpStatusErrorGetter.getStatut(new CarNotFoundException(UUID.randomUUID()))).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void not_found_customer_exception_return_not_found_status() {
        Assertions.assertThat(appHttpStatusErrorGetter.getStatut(new CustomerNotFoundException(UUID.randomUUID()))).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void technical_exception_return_internal_server_error_status() {
        Assertions.assertThat(appHttpStatusErrorGetter.getStatut(TechnicalException.builder().build())).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
