package bco.reservationvoitures.domain.voiture;

import bco.reservationvoitures.socle.annotation.DomainEntity;
import lombok.*;

import java.util.UUID;

import static org.apache.commons.lang3.Validate.notEmpty;

@With
@Builder
@Getter
@ToString
@EqualsAndHashCode(of = {"id"})
@DomainEntity
public class CategorieVoiture {
    private UUID id;
    private String libelle;

    public CategorieVoiture(UUID id, String libelle) {
        // on met ici les invariants,
        // ce qui est toujours vrai peu importe le contexte d'utilisation de cette entité
        notEmpty(libelle, "Le libellé est obligatoire");

        this.id = id;
        this.libelle = libelle;
    }
}
