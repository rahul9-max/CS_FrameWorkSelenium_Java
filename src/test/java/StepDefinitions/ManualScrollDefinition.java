package StepDefinitions;

import Pages.ManualScrollPage;
import io.cucumber.java.en.When;
import utilities.Driver;

public class ManualScrollDefinition {

	
	ManualScrollPage scrollPage = new ManualScrollPage(Driver.getDriver());

	    @When("user scrolls back to the top of the page manually")
	    public void user_scrolls_back_to_the_top_of_the_page_manually() {
	        scrollPage.scrollToTopManually();
	    }
}
