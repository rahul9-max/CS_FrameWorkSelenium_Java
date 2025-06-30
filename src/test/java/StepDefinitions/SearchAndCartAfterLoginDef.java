package StepDefinitions;

import Pages.SearchAndCartAfterLoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utilities.Driver;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

public class SearchAndCartAfterLoginDef {

    SearchAndCartAfterLoginPage cartPage = new SearchAndCartAfterLoginPage(Driver.getDriver());
 

    @When("User clicks on {string} button on SearchAndCartAfterLogin page")
    public void user_clicks_on_button_on_search_and_cart_after_login_page(String buttonName) {
        if (buttonName.equalsIgnoreCase("Products")) {
            cartPage.clickProductsLink();
        }
    }

    @Then("User should be navigated to {string} page on SearchAndCartAfterLogin page")
    public void user_should_be_navigated_to_page_on_search_and_cart_after_login_page(String pageName) {
        Assert.assertTrue(cartPage.isAllProductsVisible(), "All Products heading not visible!");
    }

    @When("User enters product_name in search input and clicks search button")
    public void user_enters_product_name_in_search_input_and_clicks_search_button() {
        cartPage.searchProduct("Sleeveless Dress");
    }

//    @Then("All products related to search should be visible")
//    public void all_products_related_to_search_should_be_visible() {
//        Assert.assertTrue(cartPage.sleevelessDressText.isDisplayed(), "Searched product not visible!");
//    }
    @Then("All products related to search should be visible")
    public void all_products_related_to_search_should_be_visible() {
        cartPage.hoverOnSearchedProduct();
    }

    @When("User adds all searched products to cart")
    public void user_adds_all_searched_products_to_cart() {
//        cartPage.hoverOnSearchedProduct();
        cartPage.clickAddToCart();
    }

    @And("User clicks on {string} button at SearchAndCartAfterLogin page")
    public void user_clicks_on_button_on_page(String buttonName) {
        if (buttonName.equalsIgnoreCase("View Cart")) {
            cartPage.clickViewCart();
        }
    }

    @Then("Added products should be visible in cart")
    public void added_products_should_be_visible_in_cart() throws TimeoutException {
        Assert.assertTrue(cartPage.isProductVisibleInCart("Sleeveless Dress"),
            "Added product is not visible in cart!");
    }

    @When("User clicks on {string} button and submits login credentials")
    public void user_clicks_on_button_and_submits_login_credentials(String buttonName) {
        if (buttonName.equalsIgnoreCase("Signup / Login")) {
            cartPage.clickSignupLoginLink();
            cartPage.login("rahul.test123@testmail.com", "rahul@123");
        }
    }

    @And("User navigates back to {string} page on SearchAndCartAfterLogin page")
    public void user_navigates_back_to_page_on_search_and_cart_after_login_page(String pageName) {
        if (pageName.equalsIgnoreCase("Cart")) {
            cartPage.clickCartAfterLogin();  // user manually clicks 'View Cart' link/button
        }
    }


    @Then("Previously added products should still be visible in cart")
    public void previously_added_products_should_still_be_visible_in_cart() throws TimeoutException {
        Assert.assertTrue(cartPage.isProductVisibleInCart("Sleeveless Dress"),
            "Product not retained in cart after login!");
    }
}