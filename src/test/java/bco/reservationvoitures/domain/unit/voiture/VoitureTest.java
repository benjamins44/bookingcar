package bco.reservationvoitures.domain.unit.voiture;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DisplayName("Test des invariants de l'entité Voiture")
public class VoitureTest {
    @Test
    @DisplayName("Contrôle que l'objet est valide")
    void controle_objet_valide() {
        VoitureFactory.build(null);
    }

    @Test
    @DisplayName("Marque doit être non vide")
    void marque_doit_etre_non_vide() {
        assertThatThrownBy(() -> {
            VoitureFactory.build(null).withMarque("");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Marque doit être non null")
    void marque_doit_etre_non_null() {
        assertThatThrownBy(() -> {
            VoitureFactory.build(null).withMarque(null);
        }).isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("Modèle doit être non vide")
    void modele_doit_etre_non_vide() {
        assertThatThrownBy(() -> {
            VoitureFactory.build(null).withModele("");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Modèle doit être non null")
    void modele_doit_etre_non_null() {
        assertThatThrownBy(() -> {
            VoitureFactory.build(null).withModele(null);
        }).isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("Nombre de place doit être positif")
    void nbPlace_doit_etre_positif() {
        assertThatThrownBy(() -> {
            VoitureFactory.build(null).withNombrePlace(0);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Nombre de place doit être non null")
    void nbPlace_doit_etre_non_null() {
        assertThatThrownBy(() -> {
            VoitureFactory.build(null).withNombrePlace(null);
        }).isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("Catégorie doit être non null")
    void categorie_doit_etre_non_null() {
        assertThatThrownBy(() -> {
            VoitureFactory.build(null).withCategorie(null);
        }).isInstanceOf(NullPointerException.class);
    }
}
