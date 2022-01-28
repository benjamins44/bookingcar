Feature: Booking manager

  Background:
    Given url baseUrl

  Scenario: can book a car

    Given path 'booking/car/123e4567-e89b-12d3-a456-426614174000/customer/123e4567-e89b-12d3-a456-426614174001/startDateTime/2015-09-22T09:41:06.3839327+02:00/endDateTime/2015-09-23T09:41:06.3839327+02:00'
    When method post
    Then status 201
    And match response.id == '#present'
    And match response.idCar == '123e4567-e89b-12d3-a456-426614174000'
    And match response.idCustomer == '123e4567-e89b-12d3-a456-426614174001'
    And match response.period.startDateTime == '2015-09-22T09:41:06.3839327Z'
    And match response.period.endDateTime == '2015-09-23T09:41:06.3839327Z'

  Scenario: can search available cars

    Given path 'booking'
    And params { startDateTime: '2016-06-22T09:41:06.3839327+02:00', endDateTime: '2016-06-23T09:41:06.3839327+02:00' }
    When method get
    Then status 200
    And match response == '#[2]'


