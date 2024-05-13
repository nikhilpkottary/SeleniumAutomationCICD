@tag
Feature: Purchase the order from Ecommerce website

  Background:
    Given I landed on Ecommerce page

  @Regression
  Scenario Outline: Positive test of submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to cart
    And checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed in the ConfirmationPage

    Examples:
    | name                       | password  | productName |
    | nikhil.p.kottary@gmail.com | $Sel12345 | ZARA COAT 3 |