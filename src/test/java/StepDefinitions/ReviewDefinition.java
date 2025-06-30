package StepDefinitions;

import org.testng.Assert;

import Pages.ReviewPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.Driver;

public class ReviewDefinition {

    ReviewPage reviewPage = new ReviewPage(Driver.getDriver());

    @When("User clicks on {string} button for review")
    public void user_clicks_on_button_for_review(String buttonText) {
        reviewPage.clickProducts();
    }

    @Then("User should be navigated to {string} page successfully on review page")
    public void user_should_be_navigated_to_page_successfully(String expectedTitleText) {
        Assert.assertTrue(reviewPage.isAllProductsTitleVisible(), "All Products title is not visible");
    }

    @When("User clicks on {string} button for seeing review")
    public void user_clicks_on_button_for_seeing_review(String buttonText) {
        reviewPage.clickViewProduct();
    }

    @Then("{string} section should be visible for review")
    public void verifyReviewSectionVisible(String sectionName) {
        Assert.assertTrue(reviewPage.isWriteYourReviewVisible(), "'Write Your Review' section is not visible");
    }

    @When("User enters name, email and review")
    public void user_enters_name_email_and_review() {
        reviewPage.enterReviewDetails("Rahul Verma", "rahul@example.com", "Amazing product. Totally worth it!");
    }

    @And("User clicks on {string} button for posting review")
    public void user_clicks_on_button_for_posting_review(String buttonLabel) {
        reviewPage.clickSubmitReview();
    }

    @Then("Success message {string} should be visible on review page")
    public void verifyReviewSuccessMessageVisible(String expectedMessage) {
        Assert.assertTrue(reviewPage.isSuccessMessageVisible(), "Success message not visible");
    }
}
