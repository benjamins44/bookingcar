package bco.bookingcar.infrastructure.primary.error.status;

import bco.bookingcar.exceptions.BusinessException;
import bco.bookingcar.exceptions.TechnicalException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class AppHttpStatusErrorGetter implements HttpStatusErrorGetter {
    @Override
    public HttpStatus getStatut(BusinessException exception) {
        if (exception == null) return HttpStatus.BAD_REQUEST;
        //TODO use pattern matching
        switch (exception.getClass().getSimpleName()) {
            case "CarNotFoundException":
            case "CustomerNotFoundException":
                return HttpStatus.NOT_FOUND;
            default:
                return HttpStatus.BAD_REQUEST;
        }
    }

    @Override
    public HttpStatus getStatut(TechnicalException exception) {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
