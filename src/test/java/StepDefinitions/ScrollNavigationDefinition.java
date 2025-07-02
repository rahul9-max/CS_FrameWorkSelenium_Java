package StepDefinitions;

import Pages.ScrollNavigationPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.Driver;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ScrollNavigationDefinition {

    ScrollNavigationPage scrollPage = new ScrollNavigationPage(Driver.getDriver());

    @When("user scrolls down to the bottom of the page")
    public void user_scrolls_down_to_the_bottom_of_the_page() {
        scrollPage.scrollToBottom();
        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
    }


    @Then("user should see the {string} section in ScrollNavigation")
    public void user_should_see_the_section_in_scroll_navigation(String sectionName) {
        Assert.assertTrue(scrollPage.isSubscriptionVisible(), "❌ Subscription section not visible!");
    }

    @When("user clicks on the arrow at the bottom right of the screen")
    public void user_clicks_on_the_arrow_at_the_bottom_right_of_the_screen() {
        scrollPage.clickScrollUpArrow();
    }

    @Then("the page should scroll up")
    public void the_page_should_scroll_up() {
        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    @And("user should see the text {string}")
    public void user_should_see_the_text(String expectedText) {
        Assert.assertTrue(scrollPage.isTopHeaderVisible(), "❌ Top header not visible after scrolling up!");
        String actualText = scrollPage.getTopHeaderText();  // Cleaner abstraction
        Assert.assertTrue(actualText.contains(expectedText),
                "❌ Expected text not found!\nExpected: " + expectedText + "\nActual: " + actualText);
    }

}
