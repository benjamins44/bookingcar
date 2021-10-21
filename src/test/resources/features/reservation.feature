Feature: Réservation de voiture

  L'application permet de réserver une voiture sur une période donnée.

  Background:
    Given des catégories existent:
      | id                                   | libelle   |
      | 10000000-0000-0000-0000-000000000001 | citadine  |
      | 10000000-0000-0000-0000-000000000002 | berline   |
      | 10000000-0000-0000-0000-000000000003 | monospace |
    Given des voitures existent:
      | id                                   | marque  | modele  | nombre_place | categorie                            |
      | 00000000-0000-0000-0000-000000000001 | DACIA   | Stepway | 5            | 10000000-0000-0000-0000-000000000001 |
      | 00000000-0000-0000-0000-000000000002 | PEUGEOT | 5008    | 5            | 10000000-0000-0000-0000-000000000003 |
      | 00000000-0000-0000-0000-000000000003 | CITROEN | DS4     | 5            | 10000000-0000-0000-0000-000000000002 |


  Scenario Outline: achat crédit
    Given je suis authentifié en tant que "<prenom_client>"
    And que le solde de mon compte est de "<solde_avant>" crédits
    When j'achète "<nb_credit>" crédits
    Then le solde de mon compte est de "<solde_après>" crédits
    Examples:
      | prenom_client | solde_avant | nb_credit | solde_après |
      | Benjamin      | 0           | 5         | 5           |
      | Jean-Michel   | 10          | 2         | 12          |
      | Patrick       | 4           | 50        | 54          |




