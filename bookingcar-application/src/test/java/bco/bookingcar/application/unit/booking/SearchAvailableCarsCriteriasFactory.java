package bco.bookingcar.application.unit.booking;

import java.util.UUID;

import bco.bookingcar.application.booking.SearchAvailableCarsRequest;
import bco.bookingcar.domain.unit.shared.PeriodFactory;

public interface SearchAvailableCarsCriteriasFactory {
    static SearchAvailableCarsRequest build() {
        return SearchAvailableCarsRequest.builder().customerId(UUID.randomUUID()).period(PeriodFactory.build()).build();
    }
}
