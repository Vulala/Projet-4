#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

@Actuators
Feature: We can access the Health/Info/Metrics/Trace actuators
  I want to be able to reach the Health/Info/Metrics/Trace actuators endpoints

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
    
