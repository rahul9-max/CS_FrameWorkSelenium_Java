package StepDefinitions;


import Pages.LoginPage;
import Pages.OrderPage;
import Pages.ProductPage;
import Pages.CartPage;
import io.cucumber.java.en.*;
import utilities.Driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class EndToEndDefinition {

//    WebDriver driver = Driver.getDriver();
//    ProductPage productPage = new ProductPage(Driver.getDriver());
//    CartPage cartPage = new CartPage(Driver.getDriver());
//    LoginPage loginPage = new LoginPage(Driver.getDriver());
    OrderPage orderPage = new OrderPage(Driver.getDriver());

    @When("User add products to cart")
    public void user_adds_products_to_cart() {
    	orderPage.addFirstProductToCart();
    }

    @And("User clicks on 'Cart' button in endToEndOrder Page")
    public void user_clicks_cart_button() {
        orderPage.clickCartButton();
    }

    @Then("Cart page should be displayed")
    public void cart_page_should_be_displayed() {
        Assert.assertTrue(orderPage.isCartPageDisplayed(), "Cart page is not displayed");
    }

    @When("User clicks 'Proceed To Checkout' button on endToEndOrder page")
    public void user_clicks_proceed_to_checkout_button_on_end_to_end_order_page() {
        orderPage.clickProceedToCheckout();
    }

    @And("User clicks on {string} button in popup box")
    public void user_clicks_register_login_button_in_popup_box(String buttonText) {
        // Optional: Check text if needed
        if (buttonText.equals("Register / Login")) {
            orderPage.clickRegisterLoginButton();
        }
        // else you can handle other buttons here later
    }


    @And("User fills all details in Login")
    public void user_fills_all_details_in_login() {
    	orderPage.fillLoginDetails("rahul.test123@testmail.com", "rahul@123");
    }

    @Then("Verify {string} at top")
    public void verify_at_top(String expectedText) {
    	orderPage.verifyLoginHeader(expectedText);
    }

    @Then("Verify Address Details and Review Your Order")
    public void verify_address_details_and_review_your_order() {
    	orderPage.verifyAddressAndReview();
    }

    @When("User enters description in comment text area and clicks {string}")
    public void user_enters_description_in_comment_text_area_and_clicks(String buttonText) {
    	orderPage.enterCommentAndPlaceOrder("Please deliver between 9am-5pm", buttonText);
    }

    @When("User enters payment details: Name on Card, Card Number, CVC, Expiration date")
    public void user_enters_payment_details_name_on_card_card_number_cvc_expiration_date() {
        orderPage.fillPaymentDetails("Test User", "4111111111111111", "123", "12", "2026");
    }

    @And("User clicks {string} button in the end")
    public void user_clicks_button_in_the_end(String buttonText) {
    	 orderPage.clickPayConfirmOrder();
    }

    @Then("Verify success message {string}")
    public void verify_success_message(String message) {
        orderPage.verifyOrderSuccessMessage();
        orderPage.clickContinueButton();
    }

    @When("User clicks 'Delete Account' button for deletion")
    public void user_clicks_delete_account_button_for_deletion() {
        orderPage.clickDeleteAccountButton();
    }

    @Then("Verify 'ACCOUNT DELETED!' and click 'Continue' button")
    public void verify_account_deleted_and_click_continue() {
        orderPage.verifyAccountDeletedMessage("ACCOUNT DELETED!");
        orderPage.clickContinueButton();
    }

} 
