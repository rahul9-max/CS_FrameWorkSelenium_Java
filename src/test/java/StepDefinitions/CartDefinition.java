package StepDefinitions;

import org.testng.Assert;

import Pages.CartPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.Driver;

public class CartDefinition {

	CartPage cartPage = new CartPage(Driver.getDriver());
	
	@When("Clicks on {string} button for cart")
	public void click_on_button_for_cart(String button) {
	    cartPage.clickProductsButton();  
	}

	@And("Hover over first product and click {string}")
	public void hover_over_first_product_and_click(String button) {
		// Use buttonText if building dynamic XPath (optional, not needed in current fixed use)
	    System.out.println("Button clicked: " + button);
		cartPage.addFirstProductToCart();
	}
	@And("Click {string} button after adding to cart")
	public void click_button_after_adding_to_cart(String button) {
		System.out.println("Button clicked: " + button);
		cartPage.clickContinueShopping();
	}
	@Then("Hover over second product and click {string}")
	public void hover_over_second_product_and_click(String button) {
		System.out.println("Button clicked: " + button);
		cartPage.addSecondProductToCart();
	}
	@And("Click {string} button on cart page")
	public void click_button_on_cart_page(String button) {
		System.out.println("Button clicked: " + button);
	    cartPage.clickViewCart();
	}
	@Then("Verify both products are added to Cart")
	public void verify_both_products_are_added_to_cart() {
		cartPage.scrollToCartTable();  // 🔁 This ensures visibility
	    int count = cartPage.getProductCount();
	    System.out.println("Cart contains " + count + " products");

	    cartPage.printProductNames();  // Optional

	    Assert.assertEquals(count, 2, "Expected 2 products, but found " + count);
	}
	@Then("Verify their prices, quantity and total price")
	public void verify_their_prices_quantity_and_total_price() {
	
	}

}
