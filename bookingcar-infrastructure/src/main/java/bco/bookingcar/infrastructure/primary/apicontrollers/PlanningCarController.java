package bco.bookingcar.infrastructure.primary.apicontrollers;

import bco.bookingcar.application.PlanningCarManager;
import bco.bookingcar.infrastructure.primary.resources.PeriodResource;
import bco.bookingcar.infrastructure.primary.resources.PlanningCarResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/planning")
@Tag(name = "Planning", description = "Operations available on Planning")
public class PlanningCarController {
    private final PlanningCarManager planningCarManager;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(tags = "Planning", summary = "Get all planning of cars on a period a car")
    public ResponseEntity<List<PlanningCarResource>> getAllByPeriod(PeriodResource periodResource) {
        return ResponseEntity.ok(
                PlanningCarResource.fromDomain(
                        planningCarManager.get(periodResource.toDomain())
                )
        );
    }
}
