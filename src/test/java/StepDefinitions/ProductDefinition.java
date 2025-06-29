package StepDefinitions;

import org.testng.Assert;
import Pages.ProductPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.Driver;

public class ProductDefinition {

	 ProductPage productPage = new ProductPage(Driver.getDriver());
	
	@When("user clicks on {string} button on productPage")
	public void user_clicks_on_button(String button) {
		 if (button.equalsIgnoreCase("Products")) {
	            productPage.clickProductsButton();
	        }
	}
	@Then("user should be navigated to the ALL PRODUCTS page")
	public void user_should_be_navigated_to_the_all_products_page() {
        Assert.assertTrue(productPage.isAllProductsPageVisible(), "All Products page not visible");
	}
	@And("the products list should be visible")
	public void the_products_list_should_be_visible() {
        Assert.assertTrue(productPage.isProductListVisible(), "Product list not visible");
	}
	@When("user clicks on {string} of the first product")
	public void user_clicks_on_of_the_first_product(String action) {
		 if (action.equalsIgnoreCase("View Product")) {
	            productPage.clickViewFirstProduct();
	        }
	}
	@Then("user should be navigated to the product detail page")
	public void user_should_be_navigated_to_the_product_detail_page() {
		 Assert.assertTrue(
			        productPage.isProductDetailSectionVisible(),
			        "Product detail page not loaded or product details section not visible"
			    );
	}
	@And("product name, category, price, availability, condition, and brand should be visible")
	public void product_name_category_price_availability_condition_and_brand_should_be_visible() {
	    Assert.assertTrue(
	        productPage.areProductDetailsVisible(),
	        "One or more product details are not visible on the detail page"
	    );
	}

}
