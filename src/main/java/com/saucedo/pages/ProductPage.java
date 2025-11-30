package com.saucedo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.saucedo.pages.components.MenuComponent;
import com.saucedo.pages.components.ProductItemComponent;
import com.saucedo.pages.components.ShoppingCartComponent;

import java.util.List;

public class ProductPage extends AbstractPage {

    @FindBy(className = "inventory_item")
    private List<WebElement> productItems;

    public ShoppingCartComponent cart = new ShoppingCartComponent(driver);
    public MenuComponent menu = new MenuComponent(driver);

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void addProductToCart(String productName) {

        for (WebElement product : productItems) {
            ProductItemComponent item = new ProductItemComponent(driver, product);
            if (item.getName().equalsIgnoreCase(productName)) {
                item.addToCard();
                break;
            }
        }
    }
}