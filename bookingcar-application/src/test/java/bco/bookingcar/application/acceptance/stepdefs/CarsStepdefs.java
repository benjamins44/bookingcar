package bco.bookingcar.application.acceptance.stepdefs;

import bco.bookingcar.domain.car.Car;
import bco.bookingcar.domain.car.CarCategory;
import bco.bookingcar.domain.ports.StoreCars;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CarsStepdefs implements En {
    public CarsStepdefs(StoreCars storeCars) {
        Given("^cars exist:$", (DataTable dataTable) -> {
            List<Map<String, String>> dataMaps = dataTable.asMaps(String.class, String.class);
            dataMaps.forEach(dataMap -> {

                CarCategory carCategory = CarCategory.getByValue(dataMap.get("category"));

                Car car = Car.builder()
                        .id(UUID.fromString(dataMap.get("id")))
                        .brand(dataMap.get("brand"))
                        .model(dataMap.get("model"))
                        .numberOfPlace(Integer.valueOf(dataMap.get("nbOfPlace")))
                        .category(carCategory)
                        .build();
                storeCars.add(car);
            });

        });
    }
}
