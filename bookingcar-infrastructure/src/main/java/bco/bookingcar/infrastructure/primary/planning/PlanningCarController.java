package bco.bookingcar.infrastructure.primary.planning;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bco.bookingcar.application.GetPlanningCarUseCase;
import bco.bookingcar.application.planning.GetPlanningCarPresenter;
import bco.bookingcar.application.planning.GetPlanningCarRequest;
import bco.bookingcar.infrastructure.primary.shared.PeriodResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/planning")
@Tag(name = "Planning", description = "Operations available on Planning")
public class PlanningCarController {
    private final GetPlanningCarUseCase getPlanningCarUseCase;
    private final GetPlanningCarPresenter<GetPlanningCarResultResource> getPlanningCarPresenter;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(tags = "Planning", summary = "Get all planning of cars on a period a car")
    public ResponseEntity<GetPlanningCarResultResource> getAllByPeriod(
            @Schema(description = "Start date and time of the period") @RequestParam String startDateTime,
            @Schema(description = "End date and time of the period") @RequestParam String endDateTime
    ) {
        var periodResource = PeriodResource.builder().startDateTime(startDateTime).endDateTime(endDateTime).build();
        getPlanningCarUseCase.execute(
                GetPlanningCarRequest.builder().period(periodResource.toDomain()).build(),
                getPlanningCarPresenter
        );
        return ResponseEntity.ok(getPlanningCarPresenter.viewModel());
    }
}
