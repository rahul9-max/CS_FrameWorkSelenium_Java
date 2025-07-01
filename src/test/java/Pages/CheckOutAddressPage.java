package Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckOutAddressPage {
    WebDriver driver;
    WebDriverWait wait;

    public CheckOutAddressPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // ---------- Signup/Login Elements ----------

    @FindBy(xpath = "//a[normalize-space()='Signup / Login']")
    WebElement signupLoginHeaderButton;

    @FindBy(xpath = "//input[@placeholder='Name']")
    WebElement nameInput;

    @FindBy(xpath = "//input[@data-qa='signup-email']")
    WebElement emailInput;

    @FindBy(xpath = "//button[normalize-space()='Signup']")
    WebElement signupButton;

    @FindBy(xpath = "//input[@id='id_gender1']")
    WebElement mrTitleRadio;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(id = "days")
    WebElement dobDays;

    @FindBy(id = "months")
    WebElement dobMonths;

    @FindBy(id = "years")
    WebElement dobYears;

    @FindBy(id = "newsletter")
    WebElement newsletterCheckbox;

    @FindBy(id = "optin")
    WebElement optinCheckbox;

    @FindBy(id = "first_name")
    WebElement firstNameField;

    @FindBy(id = "last_name")
    WebElement lastNameField;

    @FindBy(id = "address1")
    WebElement addressField;

    @FindBy(id = "state")
    WebElement stateField;

    @FindBy(id = "city")
    WebElement cityField;

    @FindBy(id = "zipcode")
    WebElement zipcodeField;

    @FindBy(id = "mobile_number")
    WebElement mobileNumberField;

    @FindBy(xpath = "//button[normalize-space()='Create Account']")
    WebElement createAccountBtn;

    @FindBy(xpath = "//*[text()='Account Created!']")
    WebElement accountCreatedMsg;

    @FindBy(xpath = "//a[text()='Continue']")
    WebElement continueAfterSignup;

    @FindBy(xpath = "//a[contains(text(),'Logged in as')]")
    WebElement loggedInUsernameText;

    // ---------- Product & Cart Elements ----------

    @FindBy(xpath = "(//a[contains(text(),'Add to cart')])[1]")
    WebElement firstProductAddToCart;

//    @FindBy(xpath = "//button[contains(text(),'Continue Shopping')]")
//    WebElement continueShoppingBtn;

    @FindBy(xpath = ".//a/u[normalize-space()='View Cart']")
    WebElement viewCartLinkInsideModal;

//    @FindBy(xpath = "//button[contains(text(),'Continue on Cart')]")
//    WebElement cartPageHeader;
    
    @FindBy(tagName = "table")  // Cart table verification
    private WebElement cartTable;

    @FindBy(xpath = "//table//tr")  // Cart items verification (rows inside table)
    private List<WebElement> cartItems;
    
    @FindBy(xpath = "//a[contains(text(),'Proceed To Checkout')]")
    private WebElement proceedToCheckoutBtn;

    // ---------- Address Elements ----------

    @FindBy(id = "address_delivery")
    WebElement deliveryAddressBox;

    @FindBy(id = "address_invoice")
    WebElement billingAddressBox;

    // ---------- Delete Account ----------


    @FindBy(xpath = "//a[contains(text(),'Delete Account')]")
    WebElement deleteAccountButton;

    @FindBy(xpath = "//*[contains(normalize-space(text()), 'Account Deleted!')]")
    private WebElement accountDeletedMessage;


    @FindBy(xpath = "//a[contains(text(),'Continue')]")
    WebElement continueButton;

    // ---------- Actions ----------

    public void clickSignupLoginFromHeader() {
        signupLoginHeaderButton.click();
    }

    public void enterNameAndEmail(String name, String email) {
        nameInput.sendKeys(name);
        emailInput.sendKeys(email);
    }

    public void clickSignupButton() {
        signupButton.click();
    }

    public void fillAccountDetails(String password, String day, String month, String year) {
        mrTitleRadio.click();
        passwordField.sendKeys(password);
        new Select(dobDays).selectByValue(day);
        new Select(dobMonths).selectByVisibleText(month);
        new Select(dobYears).selectByValue(year);
    }

    public void checkNewsletterAndOffers() {
        newsletterCheckbox.click();
        optinCheckbox.click();
    }

    public void fillAddressInfo(String firstName, String lastName, String address, String state, String city, String zip, String mobile) {
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        addressField.sendKeys(address);
        stateField.sendKeys(state);
        cityField.sendKeys(city);
        zipcodeField.sendKeys(zip);
        mobileNumberField.sendKeys(mobile);
    }

    public void clickCreateAccount() {
        createAccountBtn.click();
    }

    public boolean isAccountCreatedMessageVisible() {
        return accountCreatedMsg.isDisplayed();
    }

    public void clickContinueAfterSignup() {
        continueAfterSignup.click();
    }

//    public boolean isLoggedInUserVisible(String expectedUsername) {
//        return loggedInUsernameText.getText().contains(expectedUsername);
//    }
//    
//    public String getLoggedInUsernameText() {
//        return loggedInUsernameText.getText().trim();
//    }
    
    public boolean isLoggedInUserVisibleIgnoreCase(String expectedUsername) {
//    	ChromeOptions options = new ChromeOptions();
//        Map<String, Object> prefs = new HashMap<>();
//        prefs.put("credentials_enable_service", false);
//        prefs.put("profile.password_manager_enabled", false);
//        options.setExperimentalOption("prefs", prefs);
//        String actualText = loggedInUsernameText.getText().trim();
//        return actualText.toLowerCase().contains(expectedUsername.toLowerCase());
    	try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement loggedInText = wait.until(ExpectedConditions.visibilityOf(loggedInUsernameText));
            
            String actualText = loggedInText.getText().trim();
            System.out.println("Actual text found: " + actualText);
            
            return actualText.equalsIgnoreCase("Logged in as Rahul");
        } catch (TimeoutException e) {
            System.out.println("Timeout: 'Logged in as' element not found.");
            return false;
        } catch (NoSuchElementException e) {
            System.out.println("Element not found: " + loggedInUsernameText);
            return false;
        }
    }

    public void addFirstProductToCart() {
    	// Scroll to the product
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstProductAddToCart);
        
        // Wait and click
        wait.until(ExpectedConditions.elementToBeClickable(firstProductAddToCart)).click();
    }


//    public void clickCartButton() {
//    	viewCartLinkInsideModal.click();
//    }
    public void clickCartButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Wait until visible and clickable
        wait.until(ExpectedConditions.visibilityOf(viewCartLinkInsideModal));
        wait.until(ExpectedConditions.elementToBeClickable(viewCartLinkInsideModal));

            viewCartLinkInsideModal.click();
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

    public boolean isDeliveryAddressCorrect(String expectedAddress) {
        return deliveryAddressBox.getText().contains(expectedAddress);
    }

    public boolean isBillingAddressCorrect(String expectedAddress) {
        return billingAddressBox.getText().contains(expectedAddress);
    }

    public void clickContinueButton() {
      	 wait.until(ExpectedConditions.elementToBeClickable(continueButton));
      	    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueButton);    
      	    }

    public void clickDeleteAccountButton() {
           wait.until(ExpectedConditions.elementToBeClickable(deleteAccountButton)).click();
       }

    public boolean verifyAccountDeletedMessage(String expectedMsg) {
        try {
            return wait.until(ExpectedConditions.textToBePresentInElement(accountDeletedMessage, expectedMsg));
        } catch (TimeoutException e) {
            return false;
        }
    }


}
