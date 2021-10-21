package bco.reservationvoitures.domain.spi;

import bco.reservationvoitures.domain.voiture.CategorieVoiture;
import bco.reservationvoitures.socle.annotation.DomainRepository;

import java.util.Optional;
import java.util.UUID;

@DomainRepository
public interface BaseCategoriesVoiture {
    CategorieVoiture ajouter(CategorieVoiture categorie);

    Optional<CategorieVoiture> recupererParId(UUID categorie);
}
