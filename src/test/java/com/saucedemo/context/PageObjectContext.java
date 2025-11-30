package com.saucedemo.context;

import com.saucedo.pages.*;
import org.openqa.selenium.WebDriver;

public class PageObjectContext {

    public LoginPage loginPage;
    public ProductPage productPage;
    public CartPage cartPage;
    public CheckoutInformationPage checkoutInformationPage;
    public CheckoutOverviewPage checkoutOverviewPage;
    public CheckoutCompletePage checkoutCompletePage;

    public void init(WebDriver driver) {
        this.loginPage = new LoginPage(driver);
        this.productPage = new ProductPage(driver);
        this.cartPage = new CartPage(driver);
        this.checkoutInformationPage = new CheckoutInformationPage(driver);
        this.checkoutOverviewPage = new CheckoutOverviewPage(driver);
        this.checkoutCompletePage = new CheckoutCompletePage(driver);
    }
}
