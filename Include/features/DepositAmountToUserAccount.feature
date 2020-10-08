@Deposit_To_Account
Feature: Deposit amount to User Account
  I test the types of deposit methods: Card, Direct Bank, E-wallet

Background:
	Given I am logged into casino

  @Smoke 
  Scenario Outline: Deposit using the <deposit_method> method
    When I click on the balance button
    And I click on <deposit_method> method
    And I select the desired deposit amount <value>
    And I click on Deposit Approved button   
    Then The confirmation <message> is displayed
    And The <notification message> is showed
    And The amount is added to the account balance

    Examples: 
      | deposit_method | value  	|  message 											| notification message																		|
      | Card 					 | 10 			| Your deposit was successful!	| Your deposit of €10 has been credited to your account.	|
      | Direct Bank		 | 10 			| Your deposit was successful!	| Your deposit of €10 has been credited to your account.	|
      | E-wallet			 | 10 			| Your deposit was successful!	| Your deposit of €10 has been credited to your account.	|

 
  @Regression
  Scenario Outline: Deposit the amount of <value> using the <deposit_method>
    When I click on the balance button
    And I click on <deposit_method> method
    And I select the desired deposit amount <value>
    And I click on Deposit Approved button   
    Then The confirmation <message> is displayed
    And The <notification message> is showed
    And The amount is added to the account balance

    Examples: 
      | deposit_method | value  	|  message 											| notification message																		|
      | Card 					 | 10 			| Your deposit was successful!	| Your deposit of €10 has been credited to your account.	|
      | Card					 | 50 			| Your deposit was successful!	| Your deposit of €50 has been credited to your account.	|
      | Card 					 | 100			| Your deposit was successful!	| Your deposit of €100 has been credited to your account.	|
      | Card 					 | 500			| Your deposit was successful!	| Your deposit of €500 has been credited to your account.	| 
      | Direct Bank		 | 10 			| Your deposit was successful!	| Your deposit of €10 has been credited to your account.	|
      | Direct Bank		 | 50 			| Your deposit was successful!	| Your deposit of €50 has been credited to your account.	|
      | Direct Bank 	 | 100			| Your deposit was successful!	| Your deposit of €100 has been credited to your account.	|
      | Direct Bank 	 | 500			| Your deposit was successful!	| Your deposit of €500 has been credited to your account.	| 
      | E-wallet 			 | 10 			| Your deposit was successful!	| Your deposit of €10 has been credited to your account.	|
      | E-wallet			 | 50 			| Your deposit was successful!	| Your deposit of €50 has been credited to your account.	|
      | E-wallet 			 | 100			| Your deposit was successful!	| Your deposit of €100 has been credited to your account.	|
      | E-wallet 			 | 500			| Your deposit was successful!	| Your deposit of €500 has been credited to your account.	| 
      
      			 