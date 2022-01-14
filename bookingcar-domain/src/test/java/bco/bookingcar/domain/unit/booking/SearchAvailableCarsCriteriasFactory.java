package bco.bookingcar.domain.unit.booking;

import bco.bookingcar.domain.booking.SearchAvailableCarsCriterias;
import bco.bookingcar.domain.unit.shared.PeriodFactory;

public interface SearchAvailableCarsCriteriasFactory {
    static SearchAvailableCarsCriterias build() {
        return SearchAvailableCarsCriterias.builder()
                .period(PeriodFactory.build())
                .build();
    }
}
