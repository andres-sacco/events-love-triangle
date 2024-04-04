Feature: Health of Spring Boot
  Background:
    * url 'http://localhost:8080/api'
    * def response_ok = read('./response/health_response.json')

  Scenario: Obtain information about if application is healthy or not
    Given path 'health'
    When method GET
    Then status 200
    And match response == response_ok