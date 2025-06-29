package StepDefinitions;

import org.testng.Assert;

import Pages.RegisterWhileCheckoutPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.Driver;

public class RegisterWhileCheckoutDef {

	RegisterWhileCheckoutPage registerWhileCheckoutPage = new RegisterWhileCheckoutPage(Driver.getDriver());

//	@When("User clicks on {string} button on RegisterWhileCheckout page")
//	@And("Clicks on {string} button on RegisterWhileCheckout page")
//	public void click_button_on_register_checkout_page(String buttonName) {
//	    registerPage.clickButton(buttonName);
//	}
    @And("User clicks on {string} button on RegisterWhileCheckout page")
    public void user_clicks_cart_button_register_while_checkout(String buttonName) {
    	registerWhileCheckoutPage.clickCartButton();
    }

//    @Then("Cart page should be displayed")
//    public void cart_page_should_be_displayed() {
//    	
//    }

    @When("User clicks on {string} button in RegisterWhileCheckout page")
    public void user_clicks_proceed_to_checkout_button_register_while_checkout(String buttonName) {
    	registerWhileCheckoutPage.clickProceedToCheckoutButton();
    }
    @Then("'New User Signup!' heading should be visible")
    public void newUserSignupHeadingShouldBeVisible() {
        Assert.assertTrue(registerWhileCheckoutPage.isSignupHeadingVisible(), "'New User Signup!' heading is not visible.");
    }

    @Then("Account Information form should be displayed")
    public void accountInformationFormShouldBeDisplayed() {
        Assert.assertTrue(registerWhileCheckoutPage.isAccountInformationFormVisible(), "Account Information form is not displayed.");
    }


    @And("Clicks on {string} button for registering")
    public void clicks_register_login_button_for_registering(String buttonName) {
    	 registerWhileCheckoutPage.clickRegisterLoginButton();

    }

//    @Then("Logged in as username should be visible")
//    public void logged_in_as_username_should_be_visible() {
//    	
//    }

//    @When("User clicks on 'Cart' button on RegisterWhileCheckout page")
//    public void user_clicks_cart_button_register_while_checkout_again() {
//    	
//    }

//    @And("Clicks on 'Proceed To Checkout' button on RegisterWhileCheckout page")
//    public void clicks_proceed_to_checkout_again() { 
//    	
//    }
}

