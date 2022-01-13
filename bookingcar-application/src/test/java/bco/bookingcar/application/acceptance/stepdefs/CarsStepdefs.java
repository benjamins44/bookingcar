package bco.bookingcar.application.acceptance.stepdefs;

import bco.bookingcar.domain.spi.StoreCarCategory;
import bco.bookingcar.domain.spi.StoreCars;
import bco.bookingcar.domain.voiture.Car;
import bco.bookingcar.domain.voiture.CarCategory;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class CarsStepdefs implements En {
    public CarsStepdefs(StoreCars storeCars, StoreCarCategory storeCarCategory) {
        Given("^cars exist:$", (DataTable dataTable) -> {
            List<Map<String, String>> dataMaps = dataTable.asMaps(String.class, String.class);
            dataMaps.forEach(dataMap -> {

                Optional<CarCategory> carCategory = storeCarCategory.getById(UUID.fromString(dataMap.get("category")));
                assertThat(carCategory.isPresent()).isTrue();

                Car car = Car.builder()
                        .id(UUID.fromString(dataMap.get("id")))
                        .brand(dataMap.get("brand"))
                        .model(dataMap.get("model"))
                        .numberOfPlace(Integer.valueOf(dataMap.get("nbOfPlace")))
                        .category(carCategory.get())
                        .build();
                storeCars.add(car);
            });

        });
    }
}
