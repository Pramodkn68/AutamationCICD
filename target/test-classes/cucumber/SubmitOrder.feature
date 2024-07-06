
@tag
Feature: Purchase a order from Ecommerce website
  I want to use this template for my feature file

Background: 
Given I landed on the Ecommerce page

  @Regression
  Scenario Outline: Positive test for submitting order
  
    Given Logged in with username <name> and password <password>
    When I add product <productName> to cart
    And checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | name                 | password       | productName |
      | pramodkn68@gmail.com | Pramod@0303    | ZARA COAT 3 |