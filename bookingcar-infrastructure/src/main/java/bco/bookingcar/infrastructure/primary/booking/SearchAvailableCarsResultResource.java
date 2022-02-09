package bco.bookingcar.infrastructure.primary.booking;

import java.util.List;
import java.util.UUID;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import bco.bookingcar.domain.shared.Period;
import bco.bookingcar.infrastructure.primary.shared.PeriodResource;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Builder
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Schema(name = "SearchAvailableCarsResult", description = "The result of search available cars use case")
public class SearchAvailableCarsResultResource extends RepresentationModel<SearchAvailableCarsResultResource> {
    @Schema(description = "List of available car")
    private List<AvailableCarResource> result;

    @JsonIgnore
    private Period period;

    @JsonIgnore
    private UUID customerId;

    public SearchAvailableCarsResultResource(List<AvailableCarResource> result, Period period, UUID customerId) {
        this.result = result;
        this.period = period;
        this.customerId = customerId;

        var periodResource = PeriodResource.fromDomain(period);
        add(linkOfPeriod(periodResource, customerId).withSelfRel());

        var periodNextDayResource = PeriodResource.fromDomain(period.nextDay());
        add(linkOfPeriod(periodNextDayResource, customerId).withRel("nextDay"));

        var periodPreviousDayResource = PeriodResource.fromDomain(period.previousDay());
        add(linkOfPeriod(periodPreviousDayResource, customerId).withRel("previousDay"));
    }

    private Link linkOfPeriod(PeriodResource periodResource, UUID customerId) {
        return Link.of(
                linkTo(methodOn(BookingController.class).getAllByPeriod(
                periodResource.getStartDateTime(),
                periodResource.getEndDateTime(),
                customerId.toString()
        )).toString().replace("%3A", ":")); // it seems to have a bug with spring hateoas version
    }
}
