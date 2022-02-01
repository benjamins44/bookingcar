package bco.bookingcar.domain.customer;

import bco.bookingcar.annotation.DomainEntity;
import bco.bookingcar.exceptions.BusinessException;
import lombok.*;

import java.util.UUID;

import static bco.bookingcar.validation.Assert.field;

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

    @SneakyThrows({BusinessException.class})
    public Customer(UUID id, String firstname, String lastname) {
        field("firstname", firstname).notBlank();
        field("lastname", lastname).notBlank();

        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
