package bco.bookingcar.infrastructure.primary.planning;

import java.util.List;

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
@Schema(name = "GetPlanningCarResult", description = "The result of get planning car use case")
public class GetPlanningCarResultResource extends RepresentationModel<GetPlanningCarResultResource> {
    @Schema(description = "List of planning car")
    private List<PlanningCarResource> result;

    @JsonIgnore
    private Period period;

    public GetPlanningCarResultResource(List<PlanningCarResource> result, Period period) {
        this.result = result;
        this.period = period;

        var periodResource = PeriodResource.fromDomain(period);
        add(linkOfPeriod(periodResource).withSelfRel());

        var periodNextDayResource = PeriodResource.fromDomain(period.nextDay());
        add(linkOfPeriod(periodNextDayResource ).withRel("nextDay"));

        var periodPreviousDayResource = PeriodResource.fromDomain(period.previousDay());
        add(linkOfPeriod(periodPreviousDayResource).withRel("previousDay"));
    }

    private Link linkOfPeriod(PeriodResource periodResource) {
        return Link.of(
                linkTo(methodOn(PlanningCarController.class).getAllByPeriod(
                periodResource.getStartDateTime(),
                periodResource.getEndDateTime()
        )).toString().replace("%3A", ":")); // it seems to have a bug with spring hateoas version
    }
}
