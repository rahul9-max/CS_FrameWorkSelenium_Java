package Pages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

public class CartAddPage {
    WebDriver driver;
    WebDriverWait wait;

    public CartAddPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Scroll and View Product Button
    @FindBy(xpath = "//div[contains(@class,'product-image-wrapper')][1]//a[contains(text(),'View Product')]")
    WebElement viewProductButton;

 // Element to confirm product detail page
    @FindBy(xpath = "//div[@class='product-information']")
    WebElement productInformationSection;
    
    // Quantity input
    @FindBy(id = "quantity")
    WebElement quantityInput;

    // Add to Cart button
    @FindBy(xpath = "//button[@type='button']//i[@class='fa fa-shopping-cart']")
    WebElement addToCartButton;
    
    @FindBy(xpath = "//u[normalize-space()='View Cart']")
    WebElement viewCartBtn;

    @FindBy(xpath = "//td[@class='cart_quantity']//button")
    WebElement cartQuantityText;


    public void clickViewProduct() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", viewProductButton);
        wait.until(ExpectedConditions.elementToBeClickable(viewProductButton)).click();
    }
    
    public boolean isProductDetailVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(productInformationSection));
            return productInformationSection.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void setQuantity(int qty) {
        wait.until(ExpectedConditions.visibilityOf(quantityInput));
        quantityInput.clear();
        quantityInput.sendKeys(String.valueOf(qty));
    }

    public void clickAddToCart() {
        try {
            // Extra wait (2 sec) to allow quantity field to settle
            Thread.sleep(2000); // Temporary pause before checking clickable state
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
    }
    
    public void clickViewCart() {
        wait.until(ExpectedConditions.elementToBeClickable(viewCartBtn)).click();
    }
    
    public void verifyQuantityInCart(int expectedQty) {
        String actualQty = cartQuantityText.getText().trim();
        if (!actualQty.equals(String.valueOf(expectedQty))) {
            throw new AssertionError("Expected quantity: " + expectedQty + " but found: " + actualQty);
        }
    }
}
