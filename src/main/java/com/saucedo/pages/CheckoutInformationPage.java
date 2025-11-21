package com.saucedo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutInformationPage extends AbstractPage {

    @FindBy(css = "[data-test=firstName]")
    private WebElement inputFirstName;

    @FindBy(css = "[data-test=lastName]")
    private WebElement inputLastName;

    @FindBy(css = "[data-test=postalCode]")
    private WebElement inputPostalCode;

    @FindBy(css = "[data-test=continue]")
    private WebElement buttonContinue;

    public CheckoutInformationPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAt() {
        return driver.getCurrentUrl().contains("checkout-step-one");
    }

    public void enterInformation(String firstName, String lastName, String postalCode) {
        enterInputField(inputFirstName, firstName);
        enterInputField(inputLastName, lastName);
        enterInputField(inputPostalCode, postalCode);
    }

    public void clickCheckoutButton() {
        clickElement(buttonContinue);
    }
}
