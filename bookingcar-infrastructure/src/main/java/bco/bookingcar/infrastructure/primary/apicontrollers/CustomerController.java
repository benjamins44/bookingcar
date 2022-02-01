package bco.bookingcar.infrastructure.primary.apicontrollers;

import bco.bookingcar.application.CustomerManager;
import bco.bookingcar.exceptions.BusinessException;
import bco.bookingcar.infrastructure.primary.resources.CustomerResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/customers")
@Tag(name = "Customers", description = "Operations available on Customers")
@Slf4j
public class CustomerController {
    private final CustomerManager customerManager;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(tags = "Customers", summary = "Read a customer")
    public ResponseEntity<CustomerResource> readAbonne(@PathVariable String id) throws BusinessException {
        return ResponseEntity.ok(
                CustomerResource.fromDomain(
                        customerManager.findById(UUID.fromString(id))
                )
        );
    }
}
