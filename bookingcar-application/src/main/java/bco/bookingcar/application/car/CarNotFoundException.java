package bco.bookingcar.application.car;

import bco.bookingcar.domain.shared.BookingMessage;
import bco.bookingcar.exceptions.BusinessException;

import java.util.UUID;

public class CarNotFoundException extends BusinessException {
    public CarNotFoundException(final UUID idCar) {
        super(BusinessException.builder()
                .message(BookingMessage.CAR_NOT_FOUND)
                .argument("idCar", idCar)
        );
    }
}
