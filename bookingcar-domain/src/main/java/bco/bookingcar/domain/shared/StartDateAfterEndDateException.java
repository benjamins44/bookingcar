package bco.bookingcar.domain.shared;

import bco.bookingcar.exceptions.BusinessException;

import java.time.ZonedDateTime;

public class StartDateAfterEndDateException extends BusinessException {
    public StartDateAfterEndDateException(ZonedDateTime startDate, ZonedDateTime endDate) {
        super(BusinessException.builder()
                .message(BookingMessage.START_DATE_AFTER_END_DATE)
                .argument("startDate", startDate)
                .argument("endDate", endDate)
        );
    }
}
