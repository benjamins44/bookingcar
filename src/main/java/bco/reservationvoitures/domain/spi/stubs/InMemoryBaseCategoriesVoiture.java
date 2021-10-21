package bco.reservationvoitures.domain.spi.stubs;

import bco.reservationvoitures.domain.spi.BaseCategoriesVoiture;
import bco.reservationvoitures.domain.voiture.CategorieVoiture;
import bco.reservationvoitures.socle.annotation.Stub;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * Stubs repository de clients
 */
@Stub // annotation purement informative
public class InMemoryBaseCategoriesVoiture implements BaseCategoriesVoiture {

    private final Map<UUID, CategorieVoiture> categorieVoitureMap = new HashMap<>();

    @Override
    public CategorieVoiture ajouter(CategorieVoiture categorieVoiture) {
        categorieVoitureMap.put(categorieVoiture.getId(), categorieVoiture);
        return categorieVoiture;
    }

    @Override
    public Optional<CategorieVoiture> recupererParId(UUID id) {
        if (categorieVoitureMap.get(id) != null) {
            return Optional.of(categorieVoitureMap.get(id));
        }
        return Optional.empty();
    }
}
