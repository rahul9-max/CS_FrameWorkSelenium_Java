package StepDefinitions;

import org.testng.Assert;

import Pages.CartAddPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.Driver;

public class CartAddDefinition {

	CartAddPage cartPage = new CartAddPage(Driver.getDriver());
	
	@When("User clicks on {string} for any product on the home page")
	public void user_clicks_on_for_any_product_on_the_home_page(String string) {
        cartPage.clickViewProduct();
	}
	@Then("User should see the product details page")
	public void user_should_see_the_product_details_page() {
	    boolean isVisible = cartPage.isProductDetailVisible();
	    Assert.assertTrue(isVisible, "Product detail page not visible");
	}
	@When("User increase the quantity to {int}")
	public void user_increase_the_quantity_to(Integer quantity) {
		cartPage.setQuantity(quantity);
	}
	@And("User clicks on the {string} button on cartAdd page")
	public void user_clicks_on_the_button_on_cart_add_page(String button) {
        cartPage.clickAddToCart();

	}
	@When("User clicks on the {string} button finally")
	public void user_clicks_on_the_button_finally(String string) {
        cartPage.clickViewCart();
	}
	@Then("User should see the product displayed in the cart page with quantity {int}")
    public void user_should_see_product_in_cart_with_quantity(Integer qty) {
        cartPage.verifyQuantityInCart(qty);
    }

}
