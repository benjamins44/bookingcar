package bco.reservationvoitures.domain.voiture;

import bco.reservationvoitures.socle.annotation.AggregateRoot;
import lombok.*;

import java.util.UUID;

import static bco.reservationvoitures.socle.validation.Validate.isPositiveAndNotNull;
import static org.apache.commons.lang3.Validate.notEmpty;
import static org.apache.commons.lang3.Validate.notNull;

@With
@Builder
@Getter
@ToString
@EqualsAndHashCode(of = {"id"})
@AggregateRoot
public class Voiture {
    private UUID id;
    private String marque;
    private String modele;
    private Integer nombrePlace;
    private CategorieVoiture categorie;

    public Voiture(UUID id, String marque, String modele, Integer nombrePlace, CategorieVoiture categorie) {
        // on met ici les invariants,
        // ce qui est toujours vrai peu importe le contexte d'utilisation de cette entité
        notEmpty(marque, "La marque est obligatoire");
        notEmpty(modele, "Le modèle est obligatoire");
        isPositiveAndNotNull(nombrePlace, "Le nombre de place est positif et obligatoire");
        notNull(categorie, "La catégorie est obligatoire");

        this.id = id;
        this.marque = marque;
        this.modele = modele;
        this.nombrePlace = nombrePlace;
        this.categorie = categorie;
    }
}
