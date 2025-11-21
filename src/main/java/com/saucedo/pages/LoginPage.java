package com.saucedo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage {

    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement buttonSignIn;

    @FindBy(css = "h3[data-test='error']")
    private WebElement errorMessage;

    @FindBy(css=".login-box")
    private WebElement loginBox;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open(String url) {
        driver.get(url);
    }

    public void doLogin(String username, String password) {
        enterInputField(usernameInput, username);
        enterInputField(passwordInput, password);
        clickElement(buttonSignIn);
    }

    public String getErrorMessageText() {
        return errorMessage.getText();
    }

    public void waitForLoginBoxToAppear() {
        waitForElementToAppear(loginBox);
    }
}
