package bco.bookingcar.application.acceptance;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "json:target/cucumber/acceptance.json"},
        features = {"src/test/resources/features/booking.feature"})
public class AllAcceptanceTest {
}
