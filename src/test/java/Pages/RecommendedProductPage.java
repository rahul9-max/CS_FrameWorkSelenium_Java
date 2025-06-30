package Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

public class RecommendedProductPage {

    WebDriver driver;

    public RecommendedProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // 🔹 Section: Recommended Items
    @FindBy(xpath = "//h2[normalize-space()='recommended items']")
    public WebElement recommendedSectionHeading;

//    // 🔹 'Add to cart' button for "Summer White Top" (adjusted for clarity)
//    @FindBy(xpath = "//div[@class='recommended_items']//a[contains(@data-product-name, 'Summer White Top')]")
//    public WebElement addToCartButton;
    
    @FindBy(xpath = "(//div[@class='recommended_items']//div[contains(@class,'item active')]//a[contains(text(),'Add to cart')])[last()]")
    public WebElement lastVisibleAddToCartButton;

    // 🔹 View Cart button in modal after adding item
    @FindBy(xpath = "//u[normalize-space()='View Cart']")
    public WebElement viewCartButton;

    // 🔹 Table rows in cart for confirming product is added
    @FindBy(xpath = "//table[@id='cart_info_table']//tbody//tr")
    public List<WebElement> cartProductRows;

    // 🔹 Scroll to bottom of page
    public void scrollToBottom() {
    	 JavascriptExecutor js = (JavascriptExecutor) driver;
    	    js.executeScript("arguments[0].scrollIntoView({block: 'center'});", recommendedSectionHeading);
//    	    It tells the browser to scroll the page automatically just enough so that the recommendedSectionHeading WebElement comes into center view
    }

    // 🔹 Check if 'Recommended Items' section is visible
    public boolean isRecommendedSectionVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOf(recommendedSectionHeading));
            return recommendedSectionHeading.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    // 🔹 Click "Add to Cart" for a recommended product
    public void clickAddToCart() {
//    	JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("window.scrollBy(0, 300);");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", lastVisibleAddToCartButton);
        lastVisibleAddToCartButton.click();
    }

    public void clickViewCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Optionally wait for the modal to appear first (you can locate modal div)
            wait.until(ExpectedConditions.visibilityOf(viewCartButton));

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", viewCartButton);
            viewCartButton.click();

        } catch (TimeoutException e) {
            System.out.println("View Cart button was not visible in time.");
            throw e;
        }
    }


    // 🔹 Verify product was added to cart by checking table rows
    public boolean isRecommendedProductInCart() {
        return cartProductRows.size() > 0;
    }
}
