Feature: Booking a car

  Background:
    Given cars exist:
      | id                                   | brand   | model   | nbOfPlace | category    |
      | 00000000-0000-0000-0000-000000000001 | DACIA   | Stepway | 5         | Sedan       |
      | 00000000-0000-0000-0000-000000000002 | PEUGEOT | 5008    | 5         | Minivan     |
      | 00000000-0000-0000-0000-000000000003 | CITROEN | DS4     | 5         | CityDweller |
    Given customers exist:
      | id                                   | firstname | lastname |
      | 20000000-0000-0000-0000-000000000001 | Benjamin  | Corre    |
      | 20000000-0000-0000-0000-000000000002 | Patrick   | Dupont   |

  Scenario Outline: I can search a available car
    Given The car "<id_car_1>" is booked between "<start_date_1>" and "<end_date_1>"
    And The car "<id_car_2>" is booked between "<start_date_2>" and "<end_date_2>"
    When I'm looking for available cars between "<start_date>" and "<end_date>"
    Then The car "<id_car_3>" is available
    Examples:
      | start_date       | end_date         | id_car_1                             | start_date_1     | end_date_1       | id_car_2                             | start_date_2     | end_date_2       | id_car_3                             |
      | 2021-12-01 08:00 | 2021-12-01 12:00 | 00000000-0000-0000-0000-000000000001 | 2021-12-01 08:00 | 2021-12-01 12:00 | 00000000-0000-0000-0000-000000000002 | 2021-11-30 08:00 | 2021-12-02 08:00 | 00000000-0000-0000-0000-000000000003 |
      | 2021-12-01 08:00 | 2021-12-01 12:00 | 00000000-0000-0000-0000-000000000001 | 2021-11-30 08:00 | 2021-12-01 09:00 | 00000000-0000-0000-0000-000000000002 | 2021-11-30 08:00 | 2021-12-01 07:59 | 00000000-0000-0000-0000-000000000002 |


  Scenario Outline: I can book a available car
    Given I am authenticated as "<id_customer>"
    When I book the car "<id_car>" between "<start_date>" and "<end_date>"
    Then This car is booked for me
    Examples:
      | id_customer                          | id_car                               | start_date       | end_date         |
      | 20000000-0000-0000-0000-000000000001 | 00000000-0000-0000-0000-000000000001 | 2021-12-01 08:00 | 2021-12-01 12:00 |
      | 20000000-0000-0000-0000-000000000002 | 00000000-0000-0000-0000-000000000002 | 2021-12-01 08:00 | 2021-12-07 08:00 |



