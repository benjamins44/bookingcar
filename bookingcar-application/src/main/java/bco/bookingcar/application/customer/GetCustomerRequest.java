package bco.bookingcar.application.customer;

import bco.bookingcar.annotation.DTO;
import bco.bookingcar.exceptions.BusinessException;
import lombok.*;

import java.util.UUID;

import static bco.bookingcar.validation.Assert.field;

@Builder
@Getter
@ToString
@EqualsAndHashCode
@DTO
public class GetCustomerRequest {
    private UUID idCustomer;

    @SneakyThrows({BusinessException.class})
    public GetCustomerRequest(UUID idCustomer) {
        field("idCustomer", idCustomer).notNull();

        this.idCustomer = idCustomer;
    }
}
