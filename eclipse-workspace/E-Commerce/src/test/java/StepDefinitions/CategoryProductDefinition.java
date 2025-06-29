package StepDefinitions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import Pages.CategoryPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import utilities.Driver;

public class CategoryProductDefinition {

    CategoryPage categoryPage = new CategoryPage(Driver.getDriver());

    @Then("Categories should be visible on the left sidebar")
    public void categories_should_be_visible_on_the_left_sidebar() {
        categoryPage.scrollToCategorySection();
        assertTrue(categoryPage.isCategorySectionVisible());
    }

    @When("User clicks on {string} category")
    public void user_clicks_on_category(String category) {
        if (category.equalsIgnoreCase("WOMEN")) {
            categoryPage.clickWomenCategory();
        } else if (category.equalsIgnoreCase("MEN")) {
            categoryPage.clickMenCategory(); 
        }
    }

    @And("User clicks on {string} sub-category under {string} category")
    public void user_clicks_on_sub_category_under_category(String subCategory, String mainCategory) {
        if (mainCategory.equalsIgnoreCase("WOMEN") && subCategory.equalsIgnoreCase("TOPS")) {
            try {
                categoryPage.clickWomenTopsSubCategory(); // Try direct click
            } catch (Exception e) {
                categoryPage.expandWomenCategory();      // If fails, then expand and try again
                categoryPage.clickWomenTopsSubCategory();
            }
        }
    }


    @Then("Category page should be displayed with heading {string}")
    public void category_page_should_be_displayed_with_heading(String expectedHeading) {
        String actualHeading = categoryPage.getCategoryHeadingText1();
        assertEquals(expectedHeading.toUpperCase(), actualHeading.toUpperCase());
    }

    @When("User clicks on any sub-category under {string} category")
    public void user_clicks_on_any_sub_category_under_category(String category) {
        if (category.equalsIgnoreCase("MEN")) {
            try {
                categoryPage.clickMenTshirtsSubCategory();  // Try direct click if visible
            } catch (Exception e) {
                categoryPage.expandMenCategory();          // If not visible, expand then click
                categoryPage.clickMenTshirtsSubCategory();
            }
        }
    }

    @Then("User should be navigated to the corresponding {string} category page")
    public void user_should_be_navigated_to_the_corresponding_category_page(String expectedCategory) {
        String actualHeading = categoryPage.getCategoryHeadingText();
        assertTrue(actualHeading.toUpperCase().contains(expectedCategory.toUpperCase()));
    }

}
