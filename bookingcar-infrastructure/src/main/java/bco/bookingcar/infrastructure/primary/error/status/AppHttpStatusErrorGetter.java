package bco.bookingcar.infrastructure.primary.error.status;

import bco.bookingcar.application.car.CarNotFoundException;
import bco.bookingcar.application.customer.CustomerNotFoundException;
import bco.bookingcar.exceptions.BusinessException;
import bco.bookingcar.exceptions.TechnicalException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class AppHttpStatusErrorGetter implements HttpStatusErrorGetter {
    @Override
    public HttpStatus getStatut(BusinessException exception) {
        if (exception == null) return HttpStatus.BAD_REQUEST;
        return switch (exception) {
            case CarNotFoundException carNotFoundException -> HttpStatus.NOT_FOUND;
            case CustomerNotFoundException customerNotFoundException -> HttpStatus.NOT_FOUND;
            default -> HttpStatus.BAD_REQUEST;
        };
    }

    @Override
    public HttpStatus getStatut(TechnicalException exception) {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
