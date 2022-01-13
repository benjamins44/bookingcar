package bco.bookingcar.application.acceptance;

import bco.bookingcar.domain.spi.stubs.InMemoryStoreCarCategory;
import bco.bookingcar.domain.spi.stubs.InMemoryStoreCars;
import cucumber.runtime.java.picocontainer.PicoFactory;

public class CustomPicoFactory extends PicoFactory {

    public CustomPicoFactory() {
        addClass(InMemoryStoreCarCategory.class);
        addClass(InMemoryStoreCars.class);
    }
}
