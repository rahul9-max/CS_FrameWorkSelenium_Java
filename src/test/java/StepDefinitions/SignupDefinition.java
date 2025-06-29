package StepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import Pages.HomePage;
import Pages.RegisterPage;
import Pages.SignUpPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.Driver;

public class SignupDefinition {
	// Declare page object references at class level so they can be used in all step methods
	HomePage home;       // Represents the homepage of the application
	SignUpPage signup;   // Represents the signup/login page
	RegisterPage register; // Represents the RegisterPage
	
	@Given("User launches the browser")
	public void user_launches_the_browser() {
	    // This method starts a new Chrome browser using the custom Driver utility class
	    Driver.initialize(); // Launches and maximizes the browser window

	    // After the browser is launched, initialize all Page Object classes
	    // This ensures that WebDriver is not null and PageFactory can bind elements correctly
	    home = new HomePage();       // Initializes elements on the homepage
	    signup = new SignUpPage();   // Initializes elements on the signup/login page
	    register=new RegisterPage();
	}


@When("User navigates to {string}")
public void user_navigates_to(String url) {
	 Driver.getDriver().get(url);
}

@Then("Home page should be visible")
public void home_page_should_be_visible() {
 Assert.assertTrue(home.isHomePageVisible());
}

@When("User clicks on {string} button")
public void user_clicks_on_button(String signup) {
	if(signup.equalsIgnoreCase("Signup / Login")) {
	    home.clickSignupLogin();	
	}
}
@Then("'New User Signup!' should be visible for signup")
public void new_user_signup_should_be_visible() {
    Assert.assertTrue(signup.isSignUpPageDisplayed(), "'New User Signup!' text not visible");
}

@When("User enters name and email")
public void user_enters_name_and_email() {
    signup.enterNameEmail("Rahul","rahul.test123@testmail.com");
}

@And("Clicks on Signup button")
public void clicks_on_signup_button() {
signup.clickSignup();
}

@Then("Enter Account Information page should be visible")
public void enter_account_information_page_should_be_visible() {
    Assert.assertTrue(register.isEnterAccountInfoVisible(), "'Enter Account Information' section not visible");
}
@When("User fills account details")
public void user_fills_account_details() throws IOException {
    register.registerForm();
}

@And("Selects newsletters and offers checkboxes")
public void selects_newsletters_and_offers_checkboxes() {
   register.optionBox();
}

@And("Fills address details")
public void fills_address_details() {
  register.fillAddressDetails();
}

@And("Clicks on {string} button")
public void clicks_on_button(String buttonText) {
    if (buttonText.equalsIgnoreCase("Create Account")) {
        register.submit(); // ✅ Call to POM method
    } else {
        System.out.println("Button not handled: " + buttonText);
    }
}


@Then("Account Created message should be visible")
public void accountCreatedShouldBeVisible() {
   register.isAccountCreatedVisible();
}

@When("User clicks Continue button")
public void user_clicks_continue_button() {
	register.clickContinueButton();
}

@Then("Logged in as username should be visible")
public void logged_in_as_username_should_be_visible() {
    Assert.assertTrue(register.isLoggedInVisible());
}

@When("User logs out from the application")
public void user_logs_out_from_the_application() {
    register.logout();  // 🔁 Using method from HomePage POM
}

//@Then("User should be on the homepage")
//public void user_should_be_on_the_homepage() {
//    Assert.assertTrue(home.isHomePageVisible(), "User is not on the homepage after logout");
//}

//@When("User clicks 'Delete Account' button")
//public void userClicksDeleteAccountButton() {
//    register.DeleteAccount();
//}
//
//@Then("Account Deleted should be visible")
//public void accountDeletedShouldBeVisible() {
//    Assert.assertTrue(register.isDeleteAccVisible());  // This should check the visibility of the delete confirmation message
//}

}