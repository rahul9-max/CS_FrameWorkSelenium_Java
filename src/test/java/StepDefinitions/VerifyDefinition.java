package StepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import Pages.HomePage;
import Pages.LoginPage;
import Pages.VerifyPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.Driver;

public class VerifyDefinition {
	HomePage home;
	VerifyPage verifyPage= new VerifyPage(Driver.getDriver());;
	
//@Given("the browser is launched")
//public void the_browser_is_launched() {
//	 Driver.initialize(); // Browser launch aur maximize
//     WebDriver driver = Driver.getDriver(); // ✅ Fetch initialized driver
//     home = new HomePage();
//     verifyPage = new VerifyPage(driver);
//     
//}
//@And("user navigates to {string}")
//public void user_navigates_to(String url) {
//	Driver.getDriver().get(url);
//}
//@Then("the home page should be visible")
//public void the_home_page_should_be_visible() {
//	 Assert.assertTrue(home.isHomePageVisible());
//}
@When("user clicks on {string} button on verify page")
public void user_clicks_on_button(String button) {
	 if (button.equalsIgnoreCase("Test Cases")) {
         verifyPage.clickTestCases();
     } else {
         throw new IllegalArgumentException("Button not supported: " + button);
     }
}

@Then("user should be navigated to the Test Cases page")
public void user_should_be_navigated_to_the_test_cases_page() {
	Assert.assertTrue(verifyPage.isTestCasesPageVisible(), "Test Cases page not visible");
//    Assert.assertTrue("Test Cases page not visible", verifyPage.isTestCasesPageVisible()); //this is testNG style
}
}
