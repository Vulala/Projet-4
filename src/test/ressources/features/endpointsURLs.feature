@EndpointsURLs
Feature: Endpoints URLs
  I want to be able to do the specified task on these endpoints
 
#/firestation?stationNumber=<station_number>
  @/firestation?station_number
  Scenario: The </firestation?station_number> is used to GET the list of the people covered by the <station_number> set as parameter
    Given I reach the </firestation> endpoint
    When I set a <station_number> as parameter
    Then I see all the persons deserved by this station, showing me the <firstName>,<lastName>,<address> and <phone> of the concerned people
    And It show me a count of the numbers of adults and children (-18yo) deserved by the <station_number>

#/childAlert?address=<address>
  @/childAlert?address
  Scenario: The </childAlert?address> is used to GET the list of the children (-18yo) living at the <address> set as parameter
    Given I reach the </childAlert> endpoint
    When I set an <address> as parameter
    Then I see all the children present at this address, showing me the <firstName>, <lastName>, <age> and the <adults> of this address
    And If there is no children, then the url is empty

#/phoneAlert?firestation=<firestation_number>
  @/childAlert?address
  Scenario: The </phoneAlert?firestation> is used to GET all the phone numbers of the people covered by the station set as parameter
    Given I reach the </phoneAlert> endpoint
    When I set a <firestation> as parameter
    Then I get all the phone numbers of the people deserved by this <firestation>
 
#/fire?address=<address>
  @/fire?address
  Scenario: The </fire?address> is used to GET the list of the people living at the <address> set as parameter plus the <station_number> who deserve this <address>
    Given I reach the </fire> endpoint
    When I set an <address> as parameter
    Then I see all the persons living at this <address> with their <lastName>, <phone>, <age>
    And The <medicalRecord> for each persons
    
#/flood/stations?stations=<a list of station_numbers>
  @/flood/stations?stations
  Scenario: The </flood/stations> is used to GET a list of all the persons deserved by the <station> set as parameter
    Given I reach the </flood/stations> endpoint
    When I set a <station> as parameter
    Then I see all the persons deserved by this <station> gathered by address, with their <lastName>, <phone> and <age>
    And I also see the <medicalRecord> of every persons, next to their name
    
#/personInfo?firstName=<firstName>&lastName=<lastName>
  @/personInfo?firstName&lastName
  Scenario: The </personInfo> is used to GET the person set as parameters
    Given I reach the </personInfo> endpoint
    When I set a <{firstName}{lastName}> as parameters
    Then I see all the informations about this person: <lastName>, <address>, <age>, <email>, <medicalRecord>
    And If there is different persons with the same <lastName>, then nobody is missing   
    
#/communityEmail?city=<city>
  @/communityEmail?city
  Scenario: The </communityEmail> is used to GET all the email of the people living in the <city> set as parameter
    Given I reach the </communityEmail> endpoint
    When I set a <city> as parameter
    Then I see all the email of the persons living in that <city>
