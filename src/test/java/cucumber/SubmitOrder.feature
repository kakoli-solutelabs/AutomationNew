
@tag
Feature: Purchase the order from eccomerce Website
  I want to use this template for my feature file

	Background:
	Given I landed on Ecommerce Page
	
  @Regression
  Scenario Outline: Positive test to submit an order
    Given Logged in with Username <Username> and Password <Password>
    When I add Productname <pname> from cart
    And Checkout <pname> and Submit the order
    Then "Thankyou for the order." message is displayed in ConfirmationPage

    Examples: 
      | Username  				| Password | pname |
      | sso1@yopmail.com 	| Test@123 | ZARA COAT 3 |
