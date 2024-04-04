Feature: Create a new reservation
  Background:
    * def api_URL = `http://localhost:8080/api/`
    * def restheart_URL = `http://localhost:8082/flights_reservation/reservation/`
    * def response_ok = read('./response/create_reservation_response.json')
    * def request_ok = read('./request/create_reservation_request.json')

  Scenario: Persist the information
    Given url api_URL + 'reservation'
    And request request_ok
    And header Accept = '*/*'
    And header Content-Type = 'application/json'
    When method POST
    Then status 201
    * def reservationId = response.id
    And match response == response_ok

    # Check the insert into the database
    * def create_database = read('./database/create_reservation_database.json')

    Given url restheart_URL + reservationId
    When method GET
    Then status 200
    And match response == create_database