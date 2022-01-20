package bco.bookingcar.infrastructure.primary.resources;

import bco.bookingcar.application.booking.SearchAvailableCarsCriterias;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(name = "Search available car ", description = "A car available on the period")
public class SearchAvailableCarsCriteriasResource {

    @Schema(description = "Start date and time of the period")
    private String startDateTime;

    @Schema(description = "End date and time of the period")
    private String endDateTime;

    public static SearchAvailableCarsCriteriasResource fromDomain(SearchAvailableCarsCriterias searchAvailableCarsCriterias) {
        var period = PeriodResource.fromDomain(searchAvailableCarsCriterias.getPeriod());
        return new SearchAvailableCarsCriteriasResource(period.getStartDateTime(), period.getEndDateTime());
    }

    public SearchAvailableCarsCriterias toDomain() {
        var period = PeriodResource.builder()
                .startDateTime(startDateTime)
                .endDateTime(endDateTime)
                .build();
        return new SearchAvailableCarsCriterias(period.toDomain());
    }
}
