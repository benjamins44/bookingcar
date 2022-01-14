package bco.bookingcar.domain.unit.shared;

import bco.bookingcar.domain.shared.Period;

import java.time.ZonedDateTime;

public interface PeriodFactory {
    static Period build() {
        return Period.builder()
                .startDateTime(ZonedDateTime.now())
                .endDateTime(ZonedDateTime.now().plusDays(1L))
                .build();
    }
}
