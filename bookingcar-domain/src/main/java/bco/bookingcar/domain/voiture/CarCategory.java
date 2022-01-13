package bco.bookingcar.domain.voiture;

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
public class CarCategory {
    private UUID id;
    private String label;

    public CarCategory(UUID id, String label) {
        notEmpty(label, "The label is mandatory");

        this.id = id;
        this.label = label;
    }
}
