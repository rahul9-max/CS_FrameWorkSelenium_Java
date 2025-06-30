package StepDefinitions;

import Pages.RecommendedProductPage;
import utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class RecommendedProductDef {

    RecommendedProductPage recommendedPage = new RecommendedProductPage(Driver.getDriver());

    @When("User scrolls down to the bottom of the page")
    public void user_scrolls_down_to_the_bottom_of_the_page() {
        recommendedPage.scrollToBottom();
    }

    @Then("Recommended items section should be visible")
    public void recommended_items_section_should_be_visible() {
        Assert.assertTrue(recommendedPage.isRecommendedSectionVisible(), "Recommended section is not visible!");
    }

    @When("User clicks on {string} for a recommended product")
    public void user_clicks_on_for_a_recommended_product(String productName) {
        // Since the button is fixed for "Summer White Top" in POM, no dynamic product selection
        recommendedPage.clickAddToCart();
    }

    @And("User clicks on {string} button from recommended product")
    public void user_clicks_on_button_from_recommended_product(String buttonText) {
        recommendedPage.clickViewCart();
    }

    @Then("Recommended product should be visible in the cart page")
    public void recommended_product_should_be_visible_in_the_cart_page() {
        Assert.assertTrue(recommendedPage.isRecommendedProductInCart(), "Recommended product not visible in cart!");
    }
}
