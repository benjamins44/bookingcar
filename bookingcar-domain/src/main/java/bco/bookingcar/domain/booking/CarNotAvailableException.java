package bco.bookingcar.domain.booking;

import bco.bookingcar.domain.shared.BookingMessage;
import bco.bookingcar.exceptions.BusinessException;

import java.util.UUID;

public class CarNotAvailableException extends BusinessException {
    public CarNotAvailableException(final UUID idCar) {
        super(BusinessException.builder()
                .message(BookingMessage.CAR_NOT_AVAILABLE)
                .argument("idCar", idCar)
        );
    }
}
