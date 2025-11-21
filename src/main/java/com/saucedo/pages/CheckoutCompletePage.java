package com.saucedo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutCompletePage extends AbstractPage {

    @FindBy(css = "[data-test=complete-header]")
    private WebElement completeHeader;

    @FindBy(css = "[data-test=back-to-products]")
    private WebElement backToProductsButton;

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public boolean isAt() {
        return driver.getCurrentUrl().contains("checkout-complete");
    }

    public String getCompleteHeaderText() {
        return completeHeader.getText();
    }

    public void clickBackToProductsButton() {
        clickElement(backToProductsButton);
    }
}
