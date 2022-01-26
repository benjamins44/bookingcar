Feature: Planning manager

  Background:
    Given url baseUrl

  Scenario: can get a planning of cars

    Given path 'planning'
    And params { startDateTime: '2016-06-22T09:41:06.3839327+02:00', endDateTime: '2016-06-23T09:41:06.3839327+02:00' }
    When method get
    Then status 200
    And match response == '#[4]'