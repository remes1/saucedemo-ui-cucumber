package com.saucedo.pages.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import com.saucedo.pages.AbstractPage;

public class ProductItemComponent extends AbstractPage {

    @FindBy(className = "inventory_item_name")
    private WebElement name;

    @FindBy(xpath = ".//button")
    private WebElement addButton;

    public ProductItemComponent(WebDriver driver, WebElement root) {
        super(driver);
        PageFactory.initElements(new DefaultElementLocatorFactory(root), this);
    }

    public void addToCard() {
        clickElement(addButton);
    }

    public String getName() {
        return name.getText();
    }
}
