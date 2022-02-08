package bco.bookingcar.infrastructure.primary.car;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bco.bookingcar.application.GetCarUseCase;
import bco.bookingcar.application.car.GetCarPresenter;
import bco.bookingcar.application.car.GetCarRequest;
import bco.bookingcar.exceptions.BusinessException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/cars")
@Tag(name = "Cars", description = "Operations available on Cars")
public class CarController {
    private final GetCarUseCase getCarUseCase;
    private final GetCarPresenter<CarResource> getCarPresenter;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(tags = "Cars", summary = "Read a car")
    public ResponseEntity<CarResource> readAbonne(@PathVariable String id) throws BusinessException {
        getCarUseCase.execute(GetCarRequest.builder().idCar(UUID.fromString(id)).build(), getCarPresenter);
        return ResponseEntity.ok(getCarPresenter.viewModel());
    }
}
