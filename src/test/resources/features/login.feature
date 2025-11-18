@regression
Feature: Login to SauceDemo

  As a customer, I want to log in to SauceDemo
  So that I can access my inventory and see errors on invalid login attempts

  Background:
    Given I open the login page

  Scenario: Successful login
    When I login with valid credentials
    Then I should be redirected to the inventory page


  Scenario: Validation login
    When I login with invalid credentials
    Then I should see error message "Username and password do not match any user in this service"


    Scenario: Login with blocked user
      When I login with blocked user
      Then I should see error message "Sorry, this user has been locked out."

  Scenario: Login with empty login and password
    When I login with empty username and empty password
    Then I should see error message "Username is required"