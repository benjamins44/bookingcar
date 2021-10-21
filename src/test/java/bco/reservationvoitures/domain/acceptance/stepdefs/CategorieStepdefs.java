package bco.reservationvoitures.domain.acceptance.stepdefs;

import bco.reservationvoitures.domain.spi.BaseCategoriesVoiture;
import bco.reservationvoitures.domain.voiture.CategorieVoiture;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CategorieStepdefs implements En {
    public CategorieStepdefs(BaseCategoriesVoiture baseCategoriesVoiture) {
        Given("^des catégories existent:$", (DataTable dataTable) -> {
            List<Map<String, String>> dataMaps = dataTable.asMaps(String.class, String.class);
            dataMaps.forEach(dataMap -> {
                CategorieVoiture categorie = CategorieVoiture.builder()
                        .id(UUID.fromString(dataMap.get("id")))
                        .libelle(dataMap.get("libelle"))
                        .build();
                baseCategoriesVoiture.ajouter(categorie);
            });
        });
    }
}
