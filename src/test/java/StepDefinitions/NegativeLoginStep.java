package StepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import Pages.HomePage;
import Pages.LoginPage;
import Pages.NegativeLogin;
import io.cucumber.java.en.And;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.Driver;

public class NegativeLoginStep {
	 HomePage home;
	 LoginPage login;
	 NegativeLogin loginPage;
	 

	 @When("User enters invalid email and password")
	    public void user_enters_invalid_email_and_password() {
	        if (loginPage == null) {
	            WebDriver driver = Driver.getDriver();  // ✅ driver fetch karo
	            loginPage = new NegativeLogin(driver);  // ✅ initialize properly
	        }
	        loginPage.enterCredentials("invaliduser@email.com", "wrongpassword");
	    }
//	    @And("Clicks login button")
//	    public void clicks_login_button() {
//	        loginPage.clickLogin();
//	    }

	    @Then("Error message {string} should be visible")
	    public void error_message_should_be_visible(String message) {
	        Assert.assertEquals(loginPage.getErrorMessage(), message);
	    }
}
