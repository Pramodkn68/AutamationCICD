
@tag
Feature: Error Validation
  I want to use this template for my feature file
  
Background: 
Given I landed on the Ecommerce page

  @ErrorValidation
  Scenario Outline: Negative scenario for ErrorValidation
    Given Logged in with username <name> and password <password>
    
    Then "Incorrect email or password." message is displayed

    Examples: 
     | name                 | password     | 
     | pramodkn68@gmail.com | Pamod@0303   |