
@tag
Feature: Error Validation
  I want to use this template for my feature file

  
  @ErrorValidation
  Scenario Outline: Title of your scenario outline
    Given I landed on Ecommerce Page
    When Logged in with Username <Username> and Password <Password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | Username  				| Password |
      | sso12@yopmail.com	| Test@123 |
