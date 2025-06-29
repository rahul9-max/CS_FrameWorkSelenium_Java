package StepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import Pages.SubscriptionPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.Driver;

public class SubscriptionDefinition {

	SubscriptionPage subscriptionPage = new SubscriptionPage(Driver.getDriver());
	
	@When("Scroll down to footer")
    public void scroll_down_to_footer() {
        subscriptionPage.scrollToFooter();
    }

    @Then("Verify text {string} is visible")
    public void verify_text_is_visible(String expectedText) {
        Assert.assertTrue(subscriptionPage.isSubscriptionTextVisible(expectedText), "SUBSCRIPTION text not visible!");
    }

    @When("Enter email address in input and click arrow button")
    public void enter_email_address_in_input_and_click_arrow_button() {
        subscriptionPage.enterEmailAndClickSubscribe("test" + System.currentTimeMillis() + "@mail.com");
    }

    @Then("Verify success message {string} is visible")
    public void verify_success_message_is_visible(String expectedMessage) {
        Assert.assertTrue(subscriptionPage.isSuccessMessageVisible(expectedMessage), "Success message not visible!");
    }
    
}

