package StepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import Pages.SignupNegativePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.Driver;

public class SignupNegativeSteps {

	
    SignupNegativePage signupPage;

    @When("User enters name {string} and already registered email {string}")
    public void user_enters_name_and_registered_email(String name, String email) {
    	 WebDriver driver = Driver.getDriver();
        signupPage = new SignupNegativePage(driver);  // Pass driver from Hooks
        signupPage.enterNameAndEmail(name, email);
    }

//    @Then("Error message {string} should be visible")
//    public void error_message_should_be_visible(String expectedMessage) {
//        WebDriver driver = Driver.getDriver();
//        signupPage = new SignupNegativePage(driver); // <-- yeh line zaroori hai
//        String actualMessage = signupPage.getErrorMessage();
//        Assert.assertEquals(actualMessage, expectedMessage);
//    }
}
