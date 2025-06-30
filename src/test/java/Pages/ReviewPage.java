package Pages;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReviewPage {

    WebDriver driver;

    public ReviewPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // 🔹 PRODUCTS button
    @FindBy(xpath = "//a[@href='/products']")
    public WebElement productsButton;

    // 🔹 'ALL PRODUCTS' page title (using contains to be flexible)
    @FindBy(xpath = "//h2[contains(text(),'All Products')]")
    public WebElement allProductsTitle;

    // 🔹 'View Product' button inside the product box
    @FindBy(xpath = "//div[@class='choose']/ul/li/a[normalize-space()='View Product']")
    public WebElement viewProductButton;
    
    @FindBy(xpath = "//a[normalize-space()='Write Your Review']")
    public WebElement writeYourReviewAnchor;

    // 🔹 Name input field for review
    @FindBy(xpath = "//input[@id='name']")
    public WebElement nameInput;

    // 🔹 Email input field for review
    @FindBy(xpath = "//input[@id='email']")
    public WebElement emailInput;

    // 🔹 Review textarea
    @FindBy(xpath = "//textarea[@id='review']")
    public WebElement reviewTextarea;

    // 🔹 Submit button for posting review
    @FindBy(xpath = "//button[@id='button-review']")
    public WebElement submitReviewButton;

    // 🔹 Success message after submitting review
    @FindBy(xpath = "//span[contains(text(),'Thank you for your review.')]")
    public WebElement successMessage;
    
 // 🔸 Click on 'Products' button
    public void clickProducts() {
        productsButton.click();
    }

    // 🔸 Verify 'All Products' title is visible
    public boolean isAllProductsTitleVisible() {
        return allProductsTitle.isDisplayed();
    }

    public void clickViewProduct() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", viewProductButton);
        viewProductButton.click();
    }
    
    public boolean isWriteYourReviewVisible() {
     	JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 500);");
        return writeYourReviewAnchor.isDisplayed();
    }


    // 🔸 Enter review details
    public void enterReviewDetails(String name, String email, String review) {
        nameInput.sendKeys(name);
        emailInput.sendKeys(email);
        reviewTextarea.sendKeys(review);
    }

    // 🔸 Click on 'Submit' button for review
    public void clickSubmitReview() {
     	JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 100);");
        submitReviewButton.click();
    }

    public boolean isSuccessMessageVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOf(successMessage));
            return successMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }



}
