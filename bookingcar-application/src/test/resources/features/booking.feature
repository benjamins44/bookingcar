Feature: Booking a car

  Background:
    Given categories exist:
      | id                                   | label     |
      | 10000000-0000-0000-0000-000000000001 | citadine  |
      | 10000000-0000-0000-0000-000000000002 | berline   |
      | 10000000-0000-0000-0000-000000000003 | monospace |
    Given cars exist:
      | id                                   | brand   | model   | nbOfPlace | category                             |
      | 00000000-0000-0000-0000-000000000001 | DACIA   | Stepway | 5         | 10000000-0000-0000-0000-000000000001 |
      | 00000000-0000-0000-0000-000000000002 | PEUGEOT | 5008    | 5         | 10000000-0000-0000-0000-000000000003 |
      | 00000000-0000-0000-0000-000000000003 | CITROEN | DS4     | 5         | 10000000-0000-0000-0000-000000000002 |


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




