package StepDefinitions;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import Pages.ContactPage;
import Pages.HomePage;
import Pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.Driver;

public class ContactDefinition {
//	WebDriver driver ; 
	ContactPage contact;
	HomePage home;

	@Then("Home page should be visible successfully")
	public void home_page_should_be_visible_successfully() {
		contact = new ContactPage(Driver.getDriver());  // 👈 Add this line
		Assert.assertTrue(contact.isHomePageVisible1(), "Home page carousel is not visible");
	}

	//    @When("User clicks on {string} button")
	//    public void user_clicks_on_button(String buttonName) {
	//        contact = new ContactPage(Driver.getDriver());
	//        contact.clickButton(buttonName);
	//        System.out.println("Clicked on button: " + buttonName);
	//    }
	@When("User clicks on {string} option on contact page")
	public void user_clicks_on_button_on_contact_page(String buttonName) {
	    contact = new ContactPage(Driver.getDriver());
	    if (buttonName.equalsIgnoreCase("Contact us")) {
	        contact.clickContactUs();
	    } else {
	        throw new IllegalArgumentException("Button not handled: " + buttonName);
	    }
	}


	@Then("{string} should be visible")
	public void should_be_visible(String expectedText) {
		contact = new ContactPage(Driver.getDriver());
		Assert.assertTrue(contact.isHeadingVisible(expectedText), 
				"Expected heading '" + expectedText + "' was not visible.");
	}
	@When("User enters name, email, subject and message")
	public void user_enters_name_email_subject_and_message() {
		contact.fillContactForm("Rahul", "rahul@example.com", "Testing contact form", "This is a test message.");
	}
	@And("User uploads a file")
	public void user_uploads_a_file() {
		contact.uploadFile("C:/Users/thebe/OneDrive/Desktop/TestFile.txt");//C:\Users\thebe\OneDrive\Desktop 
	}
	@When("User clicks on {string} button on contact page")
	public void user_clicks_on_submitbutton(String buttonLabel) {
	    contact = new ContactPage(Driver.getDriver());
	    if (buttonLabel.equalsIgnoreCase("Submit")) {
	        contact.clickSubmit();
	    } else {
	        throw new IllegalArgumentException("Button not handled: " + buttonLabel);
	    }
	}

	@When("User clicks {string} on the alert popup")
	public void user_clicks_on_the_alert_popup(String action) {
		contact.handleAlert(action);

	}

	@Then("Success message {string} should be visible")
	public void heading_should_be_visible(String successText) {
		Assert.assertTrue(contact.isSuccessMessageVisible(successText));
	}




	//    @Then("User should be navigated to home page successfully")
	//    public void user_should_be_navigated_to_home() {
	//        Assert.assertTrue(home.isHomePageVisible());
	//    }

}
