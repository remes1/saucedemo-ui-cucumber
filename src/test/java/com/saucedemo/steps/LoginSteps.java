package com.saucedemo.steps;

import com.saucedemo.context.PageObjectContext;
import com.saucedo.config.Config;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {

    private Config config;
    private final PageObjectContext context;

    public LoginSteps(PageObjectContext context) {
        this.context = context;
    }

    @Given("I open the login page")
    public void i_open_the_login_page() {
        config = new Config(System.getenv("ENV"));
        context.loginPage.open(config.getBaseUrl());
    }

    @When("I login with valid credentials")
    public void i_login_with_valid_credentials() {
        context.loginPage.doLogin(config.getUsername(), config.getPassword());
    }

    @When("I login with invalid credentials")
    public void i_login_with_invalid_credentials() {
        context.loginPage.doLogin(config.getUsername(), "invalid_pass");
    }

    @When("I login with empty username and empty password")
    public void i_login_with_empty_username_and_empty_password() {
        context.loginPage.doLogin("", "");
    }

    @When("I login with blocked user")
    public void i_login_with_blocked_user() {
        context.loginPage.doLogin(config.getBlockedUsername(), config.getPassword());
    }

    @Then("I should see error message {string}")
    public void i_should_see_login_error(String expectedErrorMessage) {
        assertTrue(context.loginPage.getErrorMessageText().contains(expectedErrorMessage));
    }

    @Then("I should see login form")
    public void i_should_see_login_form() {
        context.loginPage.waitForLoginBoxToAppear();
    }
}
