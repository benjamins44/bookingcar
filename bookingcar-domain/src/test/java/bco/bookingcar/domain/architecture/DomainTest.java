package bco.bookingcar.domain.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.DisplayName;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "fr.su.bookingcar.domain")
@DisplayName("Check domain architecture")
public class DomainTest {
    @ArchTest
    static final ArchRule domain_classes_must_not_use_infrastructures_class = noClasses()
            .that().resideInAnyPackage("..domain..")
            .should().dependOnClassesThat().resideInAPackage("..infrastructure..");
}
