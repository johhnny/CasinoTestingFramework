@Login
Feature: Login feature
	As a user i want to login to the casino so i can perform different operations:consult my balance, add money to account, betting 
  
Background: 
		Given I navigate to casino url
    And I authenticate   
  
  @Smoke 
  @Regression
  Scenario Outline: Test login with valid credentials  
    When I click on RETURNING USER button
    And I enter an <email> of a registered user
    Then I am redirected to homepage

    Examples: 
      | email  		|
      | x30@x.com |
      
