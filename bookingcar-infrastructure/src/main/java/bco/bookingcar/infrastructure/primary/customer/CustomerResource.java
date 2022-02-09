package bco.bookingcar.infrastructure.primary.customer;

import bco.bookingcar.domain.customer.Customer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.With;
import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

@With
@Builder
@Getter
@ToString
@EqualsAndHashCode
@Schema(name = "Customers", description = "A customer can book a car")
public class CustomerResource {

    @Schema(description = "Customer id", accessMode = Schema.AccessMode.READ_ONLY)
    private UUID id;

    @Schema(description = "Fistname")
    private String firstname;

    @Schema(description = "Lastname")
    private String lastname;

    public static CustomerResource fromDomain(Customer customer) {
        return new CustomerResource(customer.getId(), customer.getFirstname(), customer.getLastname());
    }

    public Customer toDomain() {
        return new Customer(this.getId(), this.getFirstname(), this.getLastname());
    }
}
