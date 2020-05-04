@/person|/firestation|/medicalRecord
Feature: Endpoint CRUD
  I want to be able to perform CRUD operations over HTTP on these three endpoints.
 
#Person 
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

#Firestation   
  @FirestationPost
  Scenario: The </firestation> endpoint is used to perform Post actions with HTTP, from the json file
    Given I reach the </firestation> endpoint
   #And Decide to create a new <firestation>
    When I create a new <firestation>
    Then I can see that this <firestation> have been well created

  @FirestationPut
  Scenario: The </firestation> endpoint is used to perform Put actions with HTTP, from the json file
    Given I reach the </firestation> endpoint
   #And Decide to update an existing <firestation> number
    When I update a <firestation>
    Then I can see that this <firestation> have been well updated
    
  @FirestationDelete
  Scenario: The </firestation> endpoint is used to perform Delete actions with HTTP, from the json file
    Given I reach the </firestation> endpoint
   #And Decide to delete a <firestation>
    When I delete a <firestation>
    Then I can see that this <firestation> have been well deleted     
      
#MedicalRecord
  @MedicalRecordPost
  Scenario: The </medicalRecord> endpoint is used to perform Post actions with HTTP, from the json file
    Given I reach the </medicalRecord> endpoint
   #And Decide to create a new <medicalRecord>
    When I create a new <medicalRecord>
    Then I can see that this <medicalRecord> have been well created

  @MedicalRecordPut
  Scenario: The </medicalRecord> endpoint is used to perform Put actions with HTTP, from the json file
    Given I reach the </medicalRecord> endpoint
   #And Decide to update an existing <medicalRecord>
    When I update a <medicalRecord>
    Then I can see that this <medicalRecord> have been well updated
    
  @MedicalRecordDelete
  Scenario: The </medicalRecord> endpoint is used to perform Delete actions with HTTP, from the json file
    Given I reach the </medicalRecord> endpoint
   #And Decide to delete a <medicalRecord>
    When I delete a <medicalRecord>
    Then I can see that this <medicalRecord> have been well deleted      
      
 # @CRUD
 # Scenario Outline: All CRUD cases scenario
 #   Given I want to <CRUD> on an <endpoint>
 #   When I use <CRUD> http request to do it
 #   Then I can see that the <status> is saved

  #  Examples: 
  #    | CRUD   | person | status  |
  #    | POST   |  AAAA  | success |
  #    | PUT    |  AAAA to BBBB | success |
  #    | DELETE |  AAAA  | fail |
  #    | DELETE |  BBBB  | success |   
      
      
      
      
      
      