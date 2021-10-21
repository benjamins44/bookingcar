package bco.reservationvoitures.domain.unit.voiture;

import bco.reservationvoitures.domain.voiture.Voiture;

import java.util.UUID;

public interface VoitureFactory {
    static Voiture build(UUID id) {
        return Voiture.builder()
                .id(id)
                .marque("DACIA")
                .modele("Stepway")
                .nombrePlace(5)
                .categorie(CategorieVoitureFactory.build(null))
                .build();
    }
}
