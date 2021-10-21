package bco.reservationvoitures.domain.acceptance;

import bco.reservationvoitures.domain.spi.stubs.InMemoryBaseCategoriesVoiture;
import bco.reservationvoitures.domain.spi.stubs.InMemoryBaseVoitures;
import cucumber.runtime.java.picocontainer.PicoFactory;

public class CustomPicoFactory extends PicoFactory {

    public CustomPicoFactory() {
        addClass(InMemoryBaseCategoriesVoiture.class);
        addClass(InMemoryBaseVoitures.class);
    }
}
