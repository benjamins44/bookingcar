package bco.bookingcar.application.acceptance;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "json:target/cucumber/acceptance.json"},
        features = {"src/test/resources/features/booking.feature"})
public class AllAcceptanceTest {
}
