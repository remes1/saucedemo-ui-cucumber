package com.saucedo.pages.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.saucedo.pages.AbstractPage;

public class MenuComponent extends AbstractPage {

    @FindBy(className = "bm-burger-button")
    private WebElement menuIcon;

    @FindBy (css = "[data-test=logout-sidebar-link]")
    private WebElement logoutLink;

    public MenuComponent(WebDriver driver) {
        super(driver);
    }

    public void logout() {
        clickElement(menuIcon);
        clickElement(logoutLink);
    }
}
