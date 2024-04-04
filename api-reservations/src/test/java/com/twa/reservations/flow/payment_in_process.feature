Feature: Check the process the payment
  Background:
    * def api_URL = `http://localhost:8080/api/`
    * def restheart_URL = `http://localhost:8082/flights_reservation/reservation/`
    * def localstack_URL = `http://localhost:4566/000000000000/payments_in_process.fifo`

  Scenario: Check the creation and process of payments events
    # Create reservation
    * def response_ok = read('./response/create_reservation_response.json')
    * def request_ok = read('./request/create_reservation_request.json')

    Given url api_URL + 'reservation'
    And request request_ok
    And header Accept = '*/*'
    And header Content-Type = 'application/json'
    When method POST
    Then status 201
    * def reservationId = response.id
    And match response == response_ok

    # Send message to the queue
    * def payment_event = read('./events/payment_in_process_query.txt')
    * replace payment_event.${reservation_id} = reservationId

    Given url localstack_URL
    And header Content-Type = 'application/x-www-form-urlencoded'
    And request payment_event
    When method POST
    Then status 200

    # Check the modification into the database
    * configure retry = { count: 10, interval: 3000 }
    * def change_database = read('./database/in_process_reservation_database.json')

    Given url restheart_URL + reservationId
    And retry until karate.match("response contains change_database").pass == true
    When method GET
    Then status 200
    And match response == change_database