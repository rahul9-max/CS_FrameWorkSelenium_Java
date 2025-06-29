package StepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import Pages.DeletePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.Driver;

public class TearDownSteps {
	WebDriver driver = Driver.getDriver();
    DeletePage deletePage = new DeletePage();

    @Given("User is on the account page")
    public void user_is_on_the_account_page() {
        driver.get("http://automationexercise.com");
    }

    @And("Verify if the user is logged in")
    public void verify_if_the_user_is_logged_in() {
        Assert.assertTrue(deletePage.isUserLoggedIn(), "User is not logged in!");
    }


    @When("User clicks delete account button")
    public void user_clicks_delete_account_button() {
        deletePage.deleteAccount();
    }

    @Then("Verify account deleted message")
    public void verify_account_deleted_message() {
        Assert.assertTrue(deletePage.isAccountDeleted(), "Account deletion failed!");
    }
}
