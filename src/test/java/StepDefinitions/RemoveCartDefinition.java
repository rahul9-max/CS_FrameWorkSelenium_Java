package StepDefinitions;

import org.testng.Assert;

import Pages.RemoveCartPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.Driver;

public class RemoveCartDefinition {

    RemoveCartPage removeCartPage = new RemoveCartPage(Driver.getDriver());
	
    @When("User clicks on {string} button on RemoveCart page")
    public void user_clicks_on_button_on_remove_cart_page(String buttonName) {
        if (buttonName.equalsIgnoreCase("Cart")) {
            removeCartPage.clickCartButton();
        }
    }
//    @Then("Cart page should be displayed")
//    public void cart_page_should_be_displayed() {
//        Assert.assertTrue(removeCartPage.isCartPageDisplayed(), "Cart page is not visible");
//    }
    @When("User clicks {string} button corresponding to the product")
    public void userClicksRemoveProduct(String buttonLabel) {
        if (buttonLabel.equalsIgnoreCase("X")) {
        	removeCartPage.removeProduct();
        } else {
            throw new IllegalArgumentException("Unsupported button: " + buttonLabel);
        }
    }

    @Then("Product should be removed from the cart")
    public void product_should_be_removed_from_the_cart() {
        Assert.assertTrue(removeCartPage.isCartEmpty(), "Product was not removed from cart");
    }

}
