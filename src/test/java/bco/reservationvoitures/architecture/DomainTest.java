package bco.reservationvoitures.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.DisplayName;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "fr.su.reservationvoitures.domain")
@DisplayName("Vérification de l'architecture du domaine")
public class DomainTest {
    @ArchTest
    static final ArchRule les_classes_du_domaine_ne_doivent_dependre_d_aucune_classe_de_l_infra = noClasses()
            .that().resideInAnyPackage("..domain..")
            .should().dependOnClassesThat().resideInAPackage("..infra..");
}
