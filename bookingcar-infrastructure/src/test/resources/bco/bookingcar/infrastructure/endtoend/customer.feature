Feature: Customer manager

  Background:
    Given url baseUrl

  Scenario: can get a customer by id

    Given path 'customers/123e4567-e89b-12d3-a456-426614174001'
    When method get
    Then status 200
    And match response.id == '123e4567-e89b-12d3-a456-426614174001'
    And match response.firstname == 'Benjamin'
    And match response.lastname == 'CORRE'

