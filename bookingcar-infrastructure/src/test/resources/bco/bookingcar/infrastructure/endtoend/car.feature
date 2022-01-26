Feature: Car manager

  Background:
    Given url baseUrl

  Scenario: can get a car by id

    Given path 'cars/123e4567-e89b-12d3-a456-426614174000'
    When method get
    Then status 200
    And match response.id == '123e4567-e89b-12d3-a456-426614174000'
    And match response.brand == 'DACIA'
    And match response.model == 'Stepway'
    And match response.numberOfPlace == 5
    And match response.category == 'MINIVAN'

