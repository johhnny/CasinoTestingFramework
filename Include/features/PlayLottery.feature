@Play_Lottery
Feature: User Can Play Lottery on casino

Background:
	Given I am logged into casino


  @Smoke
  Scenario Outline: Play a lottery game with 1 ticket
    When  I select Lottery menu entry
    And I select to participate in <number_of_draws> draws
    And I click on BUY TICKETS button
    Then The bet amount is deducted from the balance
    
    Examples: 
      | number_of_draws |
      | 1 							|


 @Regression
  Scenario Outline: Play a lottery game with 1 ticket and <number_of_draws> draws
    When I select Lottery menu entry
    And I select to participate in <number_of_draws> draws
    And I click on BUY TICKETS button
    Then The bet amount is deducted from the balance

    Examples: 
      | number_of_draws |
      | 1 						  |
      | 2 							|
      | 3 							|
      | 4 							|
      | 5 							|
			| 6								|
			| 7 							|
      | 8 							|
      | 9 							|
			| 10							|