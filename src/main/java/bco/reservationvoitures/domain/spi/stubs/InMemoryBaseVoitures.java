package bco.reservationvoitures.domain.spi.stubs;

import bco.reservationvoitures.domain.spi.BaseVoitures;
import bco.reservationvoitures.domain.voiture.Voiture;
import bco.reservationvoitures.socle.annotation.Stub;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Stubs repository de clients
 */
@Stub // annotation purement informative
public class InMemoryBaseVoitures implements BaseVoitures {

    private final Map<UUID, Voiture> voitureMap = new HashMap<>();

    @Override
    public Voiture ajouter(Voiture voiture) {
        voitureMap.put(voiture.getId(), voiture);
        return voiture;
    }
}
