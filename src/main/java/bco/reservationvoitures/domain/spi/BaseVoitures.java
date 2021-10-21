package bco.reservationvoitures.domain.spi;

import bco.reservationvoitures.domain.voiture.Voiture;

public interface BaseVoitures {
    Voiture ajouter(Voiture voiture);
}
