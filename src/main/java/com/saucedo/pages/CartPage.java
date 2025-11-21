package com.saucedo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends AbstractPage {

    @FindBy(css = "[data-test=checkout]")
    private WebElement buttonCheckout;

    @FindBy(css = "[data-test=continue-shopping]")
    private WebElement buttonContinueShopping;

    @FindBy(css = ".cart_item")
    private List<WebElement> cartItems;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAt() {
        return driver.getCurrentUrl().contains("cart");
    }

    public void clickCheckoutButton() {
        clickElement(buttonCheckout);
    }

    public void clickContinueShopping() {
        clickElement(buttonContinueShopping);
    }

    private List<String> getProductNames() {
        return cartItems.stream()
                .map(item -> item.findElement(By.cssSelector(".inventory_item_name")).getText())
                .toList();
    }

    public boolean isProductInCart(String productName) {
        return getProductNames()
                .stream()
                .anyMatch(name -> name.equalsIgnoreCase(productName));
    }

    public int getCartItemsCount() {
        return cartItems.size();
    }
}
