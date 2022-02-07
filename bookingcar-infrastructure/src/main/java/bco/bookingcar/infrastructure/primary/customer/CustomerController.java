package bco.bookingcar.infrastructure.primary.customer;

import bco.bookingcar.application.GetCustomerUseCase;
import bco.bookingcar.application.customer.GetCustomerRequest;
import bco.bookingcar.exceptions.BusinessException;
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
    private final GetCustomerUseCase getCustomerUseCase;
    private final GetCustomerResourcePresenter getCustomerResourcePresenter;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(tags = "Customers", summary = "Read a customer")
    public ResponseEntity<CustomerResource> readAbonne(@PathVariable String id) throws BusinessException {
        getCustomerUseCase.execute(GetCustomerRequest.builder().idCustomer(UUID.fromString(id)).build(), getCustomerResourcePresenter);
        return ResponseEntity.ok(getCustomerResourcePresenter.viewModel());
    }

}
