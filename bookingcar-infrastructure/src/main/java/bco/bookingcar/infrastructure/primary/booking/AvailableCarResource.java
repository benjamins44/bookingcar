package bco.bookingcar.infrastructure.primary.booking;

import bco.bookingcar.application.booking.AvailableCar;
import bco.bookingcar.exceptions.BusinessException;
import bco.bookingcar.infrastructure.primary.car.CarResource;
import bco.bookingcar.infrastructure.primary.planning.PlanningCarController;
import bco.bookingcar.infrastructure.primary.shared.PeriodResource;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;
import java.util.UUID;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Builder
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Schema(name = "Available Car", description = "A car available on the period")
public class AvailableCarResource extends RepresentationModel<AvailableCarResource> {
    @Schema(description = "Available car")
    private CarResource car;

    @Schema(description = "Available period")
    private PeriodResource period;

    @JsonIgnore
    private UUID customerId;

    public AvailableCarResource(CarResource car, PeriodResource period, UUID customerId) {
        this.car = car;
        this.period = period;

        try {
            add(Link.of(
                    linkTo(methodOn(BookingController.class).book(
                            car.getId().toString(),
                            customerId.toString(),
                            period.getStartDateTime(),
                            period.getEndDateTime())
                    )
                .toString().replace("%3A", ":")) // it seems to have a bug with spring hateoas version
                .withRel("book")
            );
        } catch (BusinessException exception) {
            //It's not a problem, the service book isn't executed
        }
    }

    public static AvailableCarResource fromDomain(AvailableCar availableCar, UUID customerId) {
        return new AvailableCarResource(CarResource.fromDomain(availableCar.getCar()), PeriodResource.fromDomain(availableCar.getPeriod()), customerId);
    }

    public static List<AvailableCarResource> fromDomain(List<AvailableCar> availableCars, UUID customerId) {
        return availableCars.stream().map(availableCarResource -> AvailableCarResource.fromDomain(availableCarResource, customerId)).toList();
    }

    public AvailableCar toDomain() throws BusinessException {
        return new AvailableCar(this.getCar().toDomain(), this.getPeriod().toDomain());
    }
}
