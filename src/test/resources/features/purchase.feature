@regression
Feature: End-to-End Purchase Flow

  As a customer
  I want to log in, add a product to the cart and complete checkout
  So that I can verify the full purchase flow works correctly

  Background:
    Given I open the login page
    When I login with valid credentials
    Then I should be redirected to the inventory page

  Scenario: Successful product purchase
    When I add "Sauce Labs Backpack" to the cart
    Then I see 1 product was added to the cart
    When I click on a shopping cart icon
    Then I should be redirected to the cart page
    And I see 1 products were added to the cart
    And I see product "Sauce Labs Backpack" in the cart
    When I click on Checkout page
    Then I should be redirected to the checkout information page
    And I enter checkout information page
    And I click on Continue button
    Then I should be redirected to the checkout overview page
    And I click on Finish button
    Then I should be redirected to the checkout complete page
    Then I see message "Thank you for your order!"
    When I click on Back Home button
    Then I should be redirected to the inventory page
    When I logout from Products page
    Then I should see login form
