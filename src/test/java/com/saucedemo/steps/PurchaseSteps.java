package com.saucedemo.steps;

import com.saucedemo.context.PageObjectContext;
import com.saucedo.driver.DriverManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PurchaseSteps {

    private final PageObjectContext context;

    public PurchaseSteps(PageObjectContext context) {
        this.context = context;
    }

    @Then("I should be redirected to the inventory page")
    public void i_should_be_redirected_to_the_inventory_page() {
        assertTrue(DriverManager.getDriver().getCurrentUrl().contains("inventory"));
    }

    @When("I add {string} to the cart")
    public void i_add_to_cart(String productName) {
        context.productPage.addProductToCart(productName);
    }

    @When("I see {int} product was added to the cart")
    public void i_see_product_number_was_added_to_cart(int expectedProductNumber) {
        assertEquals(expectedProductNumber, context.productPage.cart.getCartNumber());
    }

    @When("I click on a shopping cart icon")
    public void i_click_cart_icon() {
        context.productPage.cart.clickCart();
    }

    @Then("I should be redirected to the cart page")
    public void i_should_be_redirected_to_the_cart_page() {
        assertTrue(context.cartPage.isAt(), "Not on cart page");
    }

    @Then("I see {int} products were added to the cart")
    public void i_see_products_were_added_to_the_cart(int expectedCount) {
        int actualCount = context.cartPage.getCartItemsCount();
        assertEquals(expectedCount, actualCount, "Cart item count mismatch");
    }

    @Then("I see product {string} in the cart")
    public void i_see_product_in_the_cart(String productName) {
        boolean exists = context.cartPage.isProductInCart(productName);
        assertTrue(exists, "Product should be present in cart: " + productName);
    }

    @When("I click on Checkout page")
    public void i_click_checkout_button() {
        context.cartPage.clickCheckoutButton();
    }

    @Then("I should be redirected to the checkout information page")
    public void i_should_be_redirected_to_the_checkout_information_page() {
        assertTrue(context.checkoutInformationPage.isAt(), "Not on checkout information page");
    }

    @When("I enter checkout information page")
    public void i_enter_checkout_information() {
        context.checkoutInformationPage.enterInformation("John", "Doe", "809566");
    }

    @When("I click on Continue button")
    public void i_click_continue_button() {
        context.checkoutInformationPage.clickCheckoutButton();
    }

    @Then("I should be redirected to the checkout overview page")
    public void i_should_be_redirected_to_the_checkout_overview_page() {
        assertTrue(context.checkoutOverviewPage.isAt(), "Not on checkout overview page");
    }

    @When("I click on Finish button")
    public void i_click_finish_button() {
        context.checkoutOverviewPage.clickFinishButton();
    }

    @Then("I should be redirected to the checkout complete page")
    public void i_should_be_redirected_to_the_checkout_complete_page() {
        assertTrue(context.checkoutCompletePage.isAt(), "Not on checkout complete page");
    }

    @Then("I see message {string}")
    public void i_see_message(String expectedMessage) {
        assertEquals(expectedMessage, context.checkoutCompletePage.getCompleteHeaderText());
    }

    @When("I click on Back Home button")
    public void i_click_back_home_button() {
        context.checkoutCompletePage.clickBackToProductsButton();
    }

    @When("I logout from Products page")
    public void i_logout_from_products_page() {
        context.productPage.menu.logout();
    }
}
