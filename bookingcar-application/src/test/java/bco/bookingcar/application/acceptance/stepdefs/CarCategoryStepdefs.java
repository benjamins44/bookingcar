package bco.bookingcar.application.acceptance.stepdefs;

import bco.bookingcar.domain.spi.StoreCarCategory;
import bco.bookingcar.domain.voiture.CarCategory;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CarCategoryStepdefs implements En {
    public CarCategoryStepdefs(StoreCarCategory storeCarCategory) {
        Given("^categories exist:$", (DataTable dataTable) -> {
            List<Map<String, String>> dataMaps = dataTable.asMaps(String.class, String.class);
            dataMaps.forEach(dataMap -> {
                CarCategory categories = CarCategory.builder()
                        .id(UUID.fromString(dataMap.get("id")))
                        .label(dataMap.get("label"))
                        .build();
                storeCarCategory.add(categories);
            });
        });
    }
}
