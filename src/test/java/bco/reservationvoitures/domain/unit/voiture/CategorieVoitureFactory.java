package bco.reservationvoitures.domain.unit.voiture;

import bco.reservationvoitures.domain.voiture.CategorieVoiture;

import java.util.UUID;

public interface CategorieVoitureFactory {
    static CategorieVoiture build(UUID id) {
        return CategorieVoiture.builder()
                .id(id)
                .libelle("Citadine")
                .build();
    }
}
