@Actuators
Feature: The Health/Info/Metrics/Httptrace actuators are working
  I want to be able to reach the Health/Info/Metrics/Httptrace actuators endpoints.

  @Health
  Scenario: The Health actuator is up
    Given The server start
    #And The actuators are set up
    When I go on the Health path endpoint
    Then I can see that the Health endpoint is up and working
    And Showing proper Health informations

  @Info
  Scenario: The Info actuator is up
    Given The server start
    #And The actuators are set up
    When I go on the Info path endpoint
    Then I can see that the Info endpoint is up and working
    And Showing proper Info informations
    
  @Metrics
  Scenario: The Metrics actuator is up
    Given The server start
    #And The actuators are set up
    When I go on the Metrics path endpoint
    Then I can see that the Metrics endpoint is up and working
    And Showing proper Metrics informations
    
  @Httptrace
  Scenario: The Httptrace actuator is up
    Given The server start
    #And The actuators are set up
    When I go on the Httptrace path endpoint
    Then I can see that the Httptrace endpoint is up and working
    And Showing proper Httptrace informations
