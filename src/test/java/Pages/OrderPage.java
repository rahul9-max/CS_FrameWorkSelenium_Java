package Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {
    WebDriver driver;
    WebDriverWait wait;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // --- Locators ---

    @FindBy(xpath = "(//a[contains(text(),'Add to cart')])[1]")
    WebElement firstProductAddToCart;

    @FindBy(xpath = "//button[contains(text(),'Continue Shopping')]")
    WebElement continueShoppingBtn;

    @FindBy(xpath = "//a[normalize-space()='Cart']")
    WebElement cartButton;

    @FindBy(xpath = "//button[contains(text(),'Continue on Cart')]")
    WebElement cartPageHeader;
    
    @FindBy(tagName = "table")  // Cart table verification
    private WebElement cartTable;

    @FindBy(xpath = "//table//tr")  // Cart items verification (rows inside table)
    private List<WebElement> cartItems;
    
    @FindBy(xpath = "//a[contains(text(),'Proceed To Checkout')]")
    private WebElement proceedToCheckoutBtn;

    @FindBy(xpath = "//u[normalize-space()='Register / Login']")
    WebElement registerLoginBtn;

    @FindBy(name = "email")
    WebElement emailInput;

    @FindBy(name = "password")
    WebElement passwordInput;

    @FindBy(xpath = "//button[text()='Login']")
    WebElement loginBtn;

    @FindBy(xpath = "//a[contains(text(),'Logged in as')]")
    WebElement loggedInAsText;

    @FindBy(xpath = "//h2[normalize-space()='Address Details']")
    WebElement addressHeading;

    @FindBy(xpath = "//h2[normalize-space()='Review Your Order']")
    WebElement reviewHeading;

    @FindBy(xpath = "//textarea[@name='message']")
    WebElement commentBox;
    
    @FindBy(xpath = "//*[text()='Place Order']")
    WebElement placeOrderButton;

    @FindBy(name = "name_on_card")
    WebElement nameOnCard;

    @FindBy(name = "card_number")
    WebElement cardNumber;

    @FindBy(name = "cvc")
    WebElement cvc;

    @FindBy(name = "expiry_month")
    WebElement expiryMonth;

    @FindBy(name = "expiry_year")
    WebElement expiryYear;

    @FindBy(id = "submit")
    WebElement payConfirmButton;
    
    @FindBy(xpath = "//*[contains(text(),'order')]")
    WebElement successMessageElement;

//    @FindBy(xpath = "//p[contains(text(),'Your order has been placed successfully')]")
//    WebElement successMessage;

    @FindBy(xpath = "//a[contains(text(),'Delete Account')]")
    WebElement deleteAccountButton;

    @FindBy(xpath = "//*[contains(normalize-space(text()), 'Account Deleted!')]")
    private WebElement accountDeletedMessage;


    @FindBy(xpath = "//a[contains(text(),'Continue')]")
    WebElement continueButton;

    // --- Actions ---

    public void addFirstProductToCart() {
    	// Scroll to the product
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstProductAddToCart);
        
        // Wait and click
        wait.until(ExpectedConditions.elementToBeClickable(firstProductAddToCart)).click();
        
        // Click "Continue Shopping" if visible
        wait.until(ExpectedConditions.elementToBeClickable(continueShoppingBtn)).click();
    }

    public void clickContinueShopping() {
        continueShoppingBtn.click();
    }

    public void clickCartButton() {
        cartButton.click();
    }
    
    public boolean isCartPageDisplayed() {
        return cartTable.isDisplayed();  // Check if cart table exists
    }

//    public boolean isCartNotEmpty() {
//        return cartItems.size() > 0;  // Check if items exist in the cart
//    }
    
    public void clickProceedToCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutBtn)).click();
    }
    
    public void clickRegisterLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(registerLoginBtn)).click();
    }

    public void fillLoginDetails(String email, String password) {
        wait.until(ExpectedConditions.visibilityOf(emailInput)).sendKeys(email);
        passwordInput.sendKeys(password);
        loginBtn.click();
    }

    public void verifyLoginHeader(String expectedText) {
        wait.until(ExpectedConditions.textToBePresentInElement(loggedInAsText, expectedText));
    }

    public void verifyAddressAndReview() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 500)");

        wait.until(ExpectedConditions.visibilityOf(addressHeading));
        wait.until(ExpectedConditions.visibilityOf(reviewHeading));

        if (!addressHeading.getText().equals("Address Details") || 
            !reviewHeading.getText().equals("Review Your Order")) {
            throw new AssertionError("Address or Review section heading missing or incorrect");
        }
    }

 // This method clicks a button (usually an <a> tag styled as button) using its visible text
 // No need for separate @FindBy if you're using dynamic text-based button click
    public void clickButtonByText(String buttonText) {
        // Wait until the button with given text is clickable
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//a[normalize-space(text())='" + buttonText + "']")));
        
        // Once found, click the button
        button.click();
    }

    public void enterCommentAndPlaceOrder(String comment, String buttonText) {
        wait.until(ExpectedConditions.visibilityOf(commentBox)).sendKeys(comment);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'})", placeOrderButton);
        // Click the button with the exact visible text passed as 'buttonText'
        clickButtonByText(buttonText);
    }

    public void fillPaymentDetails(String name, String cardNum, String cvcCode, String month, String year) {
        wait.until(ExpectedConditions.visibilityOf(nameOnCard)).sendKeys(name);
        cardNumber.sendKeys(cardNum);
        cvc.sendKeys(cvcCode);
        expiryMonth.sendKeys(month);
        expiryYear.sendKeys(year);
    }
    
    public void clickPayConfirmOrder() {
    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", payConfirmButton);
        wait.until(ExpectedConditions.elementToBeClickable(payConfirmButton)).click();
    }

    public void verifyOrderSuccessMessage() {
        wait.until(ExpectedConditions.visibilityOf(successMessageElement));
        String actualMessage = successMessageElement.getText();

        if (actualMessage.contains("Your order has been placed successfully!") || 
            actualMessage.contains("Congratulations! Your order has been confirmed!")) {
            System.out.println("✅ Success message verified: " + actualMessage);
        } else {
            throw new AssertionError("❌ Unexpected success message: " + actualMessage);
        }
    }

    
    public void clickContinueButton() {
   	 wait.until(ExpectedConditions.elementToBeClickable(continueButton));
   	    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueButton);    
   	    }

    public void clickDeleteAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteAccountButton)).click();
    }

    public void verifyAccountDeletedMessage(String expectedMsg) {
        wait.until(ExpectedConditions.textToBePresentInElement(accountDeletedMessage, expectedMsg));
    }

    
}
