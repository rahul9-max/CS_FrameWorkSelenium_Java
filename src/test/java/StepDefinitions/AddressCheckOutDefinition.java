package StepDefinitions;

import Pages.CheckOutAddressPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.Driver;

public class AddressCheckOutDefinition {
 
    CheckOutAddressPage checkOutPage = new CheckOutAddressPage(Driver.getDriver());

    @When("the user clicks on {string} button")
    public void the_user_clicks_on_button(String button) {
        if (button.equalsIgnoreCase("Signup / Login")) {
            checkOutPage.clickSignupLoginFromHeader();
        }
    }

    @And("fills in all required details to create a new account")
    public void fills_in_all_required_details_to_create_a_new_account() {
        checkOutPage.enterNameAndEmail("Rahul", "rahul.test123@testmail.com");
        checkOutPage.clickSignupButton();
        checkOutPage.fillAccountDetails("rahul@123", "1", "January", "1995");
        checkOutPage.checkNewsletterAndOffers();
        checkOutPage.fillAddressInfo("Rahul", "Verma", "Civil Lines", "UP", "Kanpur", "208001", "9123456789");
        checkOutPage.clickCreateAccount();
    }

    @Then("{string} message should be visible after successfull signup")
    public void message_should_be_visible_after_successfull_signup(String msg) {
        Assert.assertTrue(checkOutPage.isAccountCreatedMessageVisible(), "Account Created message not visible");
    }

    @And("the user clicks the {string} button after signup")
    public void the_user_clicks_the_button_after_signup(String button) {
        if (button.equalsIgnoreCase("Continue")) {
            checkOutPage.clickContinueAfterSignup();
        }
    }

    @Then("{string} should be visible at the top after signup")
    public void should_be_visible_at_the_top_after_signup(String expectedText) {
        Assert.assertTrue(checkOutPage.isLoggedInUserVisibleIgnoreCase(expectedText),
            "Expected username not found in logged-in message."
        );
    }

    @When("the user adds products to the cart")
    public void the_user_adds_products_to_the_cart() {
        checkOutPage.addFirstProductToCart();
//        checkOutPage.clickContinueShopping();
    }

    @And("clicks the {string} button to check the cart")
    public void clicks_the_button_to_check_the_cart(String button) {
        if (button.equalsIgnoreCase("View Cart")) {
            checkOutPage.clickCartButton();
        }
    }

    @Then("the cart page should be displayed")
    public void the_cart_page_should_be_displayed() {
        Assert.assertTrue(checkOutPage.isCartPageDisplayed(), "Cart page not displayed");
    }

    @When("the user clicks on {string} for reviewing address")
    public void the_user_clicks_on_for_reviewing_address(String button) {
        if (button.equalsIgnoreCase("Proceed To Checkout")) {
            checkOutPage.clickProceedToCheckout();
        }
    }

    @Then("the delivery address should match the registration address")
    public void the_delivery_address_should_match_the_registration_address() {
        Assert.assertTrue(checkOutPage.isDeliveryAddressCorrect("Civil Lines"), "Delivery address mismatch");
    }

    @And("the billing address should match the registration address")
    public void the_billing_address_should_match_the_registration_address() {
        Assert.assertTrue(checkOutPage.isBillingAddressCorrect("Civil Lines"), "Billing address mismatch");
    }

    @When("the user clicks the {string} button after reviewing address")
    public void the_user_clicks_the_button_after_reviewing_address(String button) {
        if (button.equalsIgnoreCase("Delete Account")) {
            checkOutPage.clickDeleteAccountButton();
        }
    }

    @Then("{string} message should be displayed on page")
    public void message_should_be_displayed_on_page(String expectedMsg) {
        Assert.assertTrue(checkOutPage.verifyAccountDeletedMessage(expectedMsg), "Account Deleted message not visible");
    }


    @And("the user clicks the {string} button finally")
    public void the_user_clicks_the_button_finally(String button) {
        if (button.equalsIgnoreCase("Continue")) {
            checkOutPage.clickContinueButton();
        }
    }
}
