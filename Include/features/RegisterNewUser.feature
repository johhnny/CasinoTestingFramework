@Registration
Feature: Register new user
  I want to register a new user to the casino

Background: 
	Given I navigate to casino url
  And I authenticate

  @Smoke 
  @Regression
  Scenario: Register a new user
    When I click on NEW USER button 
    And I click on I GET IT, CONTINUE button
    And I enter valid data
      | Fields								| Value										  	|
      | email  								| generate_an_unique_email		|
      | country phone code 		| +34													|
      | phone 								| 774455											|
      | name  								| name23 prename23						|
      | password 							| x_1234											|
            
    Then I get the message "Your registration is complete!" that the new user is registered
    
  