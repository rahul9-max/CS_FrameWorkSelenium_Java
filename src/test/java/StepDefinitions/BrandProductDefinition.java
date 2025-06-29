package StepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.testng.Assert.*;

import Pages.BrandPage;
import utilities.Driver;

public class BrandProductDefinition {

    BrandPage brandPage = new BrandPage(Driver.getDriver());

    @When("User clicks on {string} button on BrandPage")
    public void user_clicks_on_button_on_brand_page(String buttonName) {
        // Generic if you expand buttons later
        if (buttonName.equalsIgnoreCase("Products")) {
            brandPage.clickProductsButton();
        }
    }

    @Then("Brands are visible on the left side bar")
    public void brands_are_visible_on_the_left_side_bar() {
        assertTrue(brandPage.isBrandsVisible(), "Brand section is not visible after scrolling!");
    }

    @When("User clicks on any brand name")
    public void user_clicks_on_any_brand_name() {
        brandPage.clickOnBabyhugBrand();
    }

    @Then("User is navigated to brand page and brand products are displayed")
    public void user_is_navigated_to_brand_page_and_brand_products_are_displayed() {
        assertTrue(brandPage.isBabyhugHeadingVisible(), "Babyhug brand heading not found!");
    }

    @When("User clicks on another brand name from the left side bar")
    public void user_clicks_on_another_brand_name_from_the_left_side_bar() {
        brandPage.clickOnHMBrand();
    }

    @Then("User is navigated to that brand page and relevant products are displayed")
    public void user_is_navigated_to_that_brand_page_and_relevant_products_are_displayed() {
        assertTrue(brandPage.isHMHeadingVisible(), "H&M brand heading not found!");
    }
}
