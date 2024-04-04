Feature: Check the process of confirm the payments
  Background:
    * def api_URL = `http://localhost:8080/api/`
    * def restheart_URL = `http://localhost:8082/flights_reservation/reservation/`
    * def localstack_URL = `http://localhost:4566/000000000000/payments_confirmed.fifo`
    * def localstack_assertions_URL = `http://localhost:4566/000000000000/reservation_confirmed-assertions.fifo`

  Scenario: Check the confirmation of the payments
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
    * def payment_event = read('./events/payment_confirmed_query.txt')
    * replace payment_event.${reservation_id} = reservationId

    Given url localstack_URL
    And header Content-Type = 'application/x-www-form-urlencoded'
    And request payment_event
    When method POST
    Then status 200

    # Check the modification into the database
    * configure retry = { count: 10, interval: 3000 }
    * def change_database = read('./database/confirmed_reservation_database.json')

    Given url restheart_URL + reservationId
    And retry until karate.match("response contains change_database").pass == true
    When method GET
    Then status 200
    And match response == change_database

    # Check if the message exists
    * def assertion_event = read('./events/reservation_confirmed_query.txt')
    * replace assertion_event.${reservation_id} = reservationId

    * print localstack_assertions_URL + `?` + assertion_event

    Given url localstack_assertions_URL + `?` + assertion_event
    And retry until karate.match("response/ReceiveMessageResponse/ReceiveMessageResult/Message/Body == '#present'").pass == true
    When method Get
    Then status 200
    And match response/ReceiveMessageResponse/ReceiveMessageResult/Message/Body == '#present'
