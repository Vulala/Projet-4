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

@/personEndpoint
Feature: /personEndpoint CRUD
  I want to be able to perform CRUD operations over HTTP on the /person endpoint
  
  @PersonPost
  Scenario: The </person> endpoint is used to perform Post actions with HTTP, from the json file
    Given I reach the </person> endpoint
   #And Decide to create a new <person>
    When I create a new <person>
    Then I can see that this <person> have been well created

  @PersonPut
  Scenario: The </person> endpoint is used to perform Put actions with HTTP, from the json file
    Given I reach the </person> endpoint
   #And Decide to update an existing <person>
    When I update a <person>
    Then I can see that this <person> have been well updated
    
  @PersonDelete
  Scenario: The </person> endpoint is used to perform Delete actions with HTTP, from the json file
    Given I reach the </person> endpoint
   #And Decide to delete a <person>
    When I delete a <person>
    Then I can see that this <person> have been well deleted
    
 # @CRUD
 # Scenario Outline: All CRUD cases scenario
 #   Given I want to <CRUD> a <person>
 #   When I use <CRUD> action to do it
 #   Then I can see that the <status> is saved

  #  Examples: 
  #    | CRUD  | person | status  |
  #    | POST |     AAAA | success |
  #    | PUT |     AAAA to BBBB | success |
  #    | DELETE |     AAAA | fail |
  #    | DELETE |     BBBB | success |
      
      
      