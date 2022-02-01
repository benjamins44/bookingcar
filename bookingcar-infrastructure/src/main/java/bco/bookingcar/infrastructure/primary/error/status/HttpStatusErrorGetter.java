package bco.bookingcar.infrastructure.primary.error.status;

import bco.bookingcar.exceptions.BusinessException;
import bco.bookingcar.exceptions.TechnicalException;
import org.springframework.http.HttpStatus;

public interface HttpStatusErrorGetter {
    HttpStatus getStatut(BusinessException exception);

    HttpStatus getStatut(TechnicalException exception);
}
