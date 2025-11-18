package pages.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.AbstractPage;

public class ShoppingCartComponent extends AbstractPage {

    @FindBy(css = "[data-test=shopping-cart-link]")
    private WebElement cart;

    @FindBy(css = "[data-test=shopping-cart-badge]")
    private WebElement badge;

    public ShoppingCartComponent(WebDriver driver) {
        super(driver);
    }

    public void clickCart() {
        clickElement(cart);
    }

    public int getCartNumber() {
        if (badge == null || badge.getText().isEmpty()) {
            return 0;
        }
        return Integer.parseInt(badge.getText());
    }
}
