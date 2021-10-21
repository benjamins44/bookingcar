package bco.reservationvoitures.domain.acceptance.stepdefs;

import bco.reservationvoitures.domain.spi.BaseCategoriesVoiture;
import bco.reservationvoitures.domain.spi.BaseVoitures;
import bco.reservationvoitures.domain.voiture.CategorieVoiture;
import bco.reservationvoitures.domain.voiture.Voiture;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class VoitureStepdefs implements En {
    public VoitureStepdefs(BaseVoitures baseVoitures, BaseCategoriesVoiture baseCategoriesVoiture) {
        Given("^des voitures existent:$", (DataTable dataTable) -> {
            List<Map<String, String>> dataMaps = dataTable.asMaps(String.class, String.class);
            dataMaps.forEach(dataMap -> {

                Optional<CategorieVoiture> categorieVoiture = baseCategoriesVoiture.recupererParId(UUID.fromString(dataMap.get("categorie")));
                assertThat(categorieVoiture.isPresent()).isTrue();

                Voiture voiture = Voiture.builder()
                        .id(UUID.fromString(dataMap.get("id")))
                        .marque(dataMap.get("marque"))
                        .modele(dataMap.get("modele"))
                        .nombrePlace(Integer.valueOf(dataMap.get("nombre_place")))
                        .categorie(categorieVoiture.get())
                        .build();
                baseVoitures.ajouter(voiture);
            });

        });
    }
}
