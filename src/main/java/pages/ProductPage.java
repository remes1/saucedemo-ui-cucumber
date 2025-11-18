package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.components.MenuComponent;
import pages.components.ProductItemComponent;
import pages.components.ShoppingCartComponent;

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