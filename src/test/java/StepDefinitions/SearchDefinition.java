package StepDefinitions;

import org.testng.Assert;

import Pages.SearchPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.Driver;

public class SearchDefinition {

	SearchPage searchPage = new SearchPage(Driver.getDriver());
	
	@When("User clicks on 'Products' button on search page")
	public void user_clicks_on_products_button() {
		searchPage.clickProductsButton();
	}
	@Then("User should be navigated to ALL PRODUCTS page successfully")
	public void user_should_be_navigated_to_all_products_page_successfully() {
        Assert.assertTrue(searchPage.isAllProductsPageVisible(), "ALL PRODUCTS page not visible!");
	}
	@When("User enters product name in search input and clicks search button")
	public void user_enters_product_name_in_search_input_and_clicks_search_button() {
		searchPage.searchForProduct("Winter Top");
	}
	@Then("{string} section should be visible")
	public void section_should_be_visible(String sectionName) {
        Assert.assertTrue(searchPage.isSearchedProductsSectionVisible(sectionName), sectionName + " section is not visible!");
	}
	@And("All products related to the search should be visible")
	public void all_products_related_to_the_search_should_be_visible() {
        Assert.assertTrue(searchPage.isProductVisibleAfterScroll("Winter Top"), "Winter Top not visible in search results!");
	}

}
