@tag
Feature: Error validation

  Background:
    Given I landed on Ecommerce page

  @ErrorValidation
  Scenario Outline: Error validation of login page
    Given I landed on Ecommerce page
    When Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples:
      | name                       | password  |
      | nikhil.p.kottary@gmail.com | $Sel1234  |