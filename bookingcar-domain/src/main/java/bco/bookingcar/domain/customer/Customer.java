package bco.bookingcar.domain.customer;

import bco.bookingcar.annotation.DomainEntity;
import lombok.*;

import java.util.UUID;

import static org.apache.commons.lang3.Validate.notEmpty;

@With
@Builder
@Getter
@ToString
@EqualsAndHashCode(of = {"id"})
@DomainEntity
public class Customer {
    private UUID id;
    private String firstname;
    private String lastname;

    public Customer(UUID id, String firstname, String lastname) {
        notEmpty(firstname, "The firstname is mandatory");
        notEmpty(lastname, "The lastname is mandatory");

        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
