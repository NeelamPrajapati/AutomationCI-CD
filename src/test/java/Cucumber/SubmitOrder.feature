
@tag
Feature: Purchase order from Ecommerse website
  I want to use this template for my feature file

Background:
Given I landed on Ecommerse webpage
 
  @tag2
  Scenario Outline: Positive testing of submitting order
    Given Logged in with username <username> and passowrd <password>
    When I add product <product> to cart
    And Checkout product <product> and submit order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page

    Examples: 
      | username  | password | product  |
      | neelam@gmail.com |     Neelam@123 | ZARA COAT 3 |
     
