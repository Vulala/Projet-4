@TheServerStart
Feature: The web server start and show the index page
  I want to go be sure that the server actually start

  @Index
  Scenario: The web server start and so, show the index
    Given The web server start
    When I reach the web server with my broswer
    Then I see the index page
