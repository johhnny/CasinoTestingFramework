@Play_Game
Feature: User Can Play Games on casino

Background:
	Given I am logged into casino


  @Smoke
  Scenario Outline: Play a casino game
    When I select Casino menu entry
    And I set the Bet value to <value>
    And I click on one of the cats
    Then My balance updates accordingly in case I win or I loose 

    Examples: 
      | value  	| 
      | 5 			| 

     
    @Regression
    Scenario Outline: Play a casino game with the bet amount of <value>
    When I select Casino menu entry
    And I set the Bet value to <value>
    And I click on one of the cats
    Then My balance updates accordingly in case I win or I loose  
    
    Examples: 
      | value  	| 
      | 5 			| 
      | 10		  |
      | 25 			|
      | 50 			| 
      | 250			|
      
      
      