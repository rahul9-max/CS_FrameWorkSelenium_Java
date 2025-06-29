package StepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import Pages.HomePage;
import Pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.Driver;

public class LoginSteps {
    HomePage home;
    LoginPage login;

    @Given("User launches the browser for login test")
    public void user_launches_the_browser_for_login_test() {
        Driver.initialize(); // Browser launch aur maximize
        WebDriver driver = Driver.getDriver(); // ✅ Fetch initialized driver
        home = new HomePage();
        login = new LoginPage(driver);
    }
    @When("User navigates to {string} for login")
    public void user_navigates_to_for_login(String url) {
    	Driver.getDriver().get(url);
    }

    @Then("Home page should be visible for login")
    public void home_page_should_be_visible_for_login() {
        Assert.assertTrue(home.isHomePageVisible());
    }

    @When("User clicks on \"Login\" button for login")
    public void user_clicks_on_login_button_for_login() {
        home.clickSignupLogin();
        login.clickLoginButton();
    }

    @Then("Verify \"Login to your account\" is visible")
    public void verify_login_to_your_account_is_visible() {
        Assert.assertTrue(login.isLoginHeadingVisible());
    }

    @When("User enters correct email and password")
    public void user_enters_correct_email_and_password() {
        login.enterCredentials("rahul.test123@testmail.com", "rahul@123");
    }

    @When("Clicks login button")
    public void clicks_login_button() {
        login.submitLogin();
    }

    @Then("Verify that \"Logged in as username\" is visible")
    public void verify_logged_in_as_username_is_visible() {
        Assert.assertTrue(login.isLoggedInVisible());
    }
}
