package bco.reservationvoitures.domain.unit.voiture;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DisplayName("Test des invariants de l'entité CatégorieVoiture")
public class CategorieVoitureTest {
    @Test
    @DisplayName("Contrôle que l'objet est valide")
    void controle_objet_valide() {
        CategorieVoitureFactory.build(null);
    }

    @Test
    @DisplayName("Libellé doit être non vide")
    void libelle_doit_etre_non_vide() {
        assertThatThrownBy(() -> {
            CategorieVoitureFactory.build(null).withLibelle("");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Libellé doit être non null")
    void libelle_doit_etre_non_null() {
        assertThatThrownBy(() -> {
            CategorieVoitureFactory.build(null).withLibelle(null);
        }).isInstanceOf(NullPointerException.class);
    }
}
