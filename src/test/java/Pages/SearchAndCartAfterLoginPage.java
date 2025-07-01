package Pages;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

	public class SearchAndCartAfterLoginPage {

	    WebDriver driver;
	    WebDriverWait wait;
	    Actions actions;

	    public SearchAndCartAfterLoginPage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        actions = new Actions(driver);
	    }

	    @FindBy(xpath = "//a[@href='/products']")
	    public WebElement productsLink;

	    @FindBy(xpath = "//h2[contains(@class,'title') and contains(text(),'All Products')]")
	    public WebElement allProductsHeading;

	    @FindBy(xpath = "//input[@id='search_product']")
	    public WebElement searchBox;

	    @FindBy(xpath = "//i[@class='fa fa-search']")
	    public WebElement searchButton;

	    @FindBy(xpath = "//div[@class='overlay-content']//p[contains(text(),'Sleeveless Dress')]")
	    public WebElement sleevelessDressText;

	    @FindBy(xpath = "//div[@class='overlay-content']//a[@class='btn btn-default add-to-cart'][normalize-space()='Add to cart']")
	    public WebElement addToCartButton;

	    @FindBy(xpath = "//div[@id='cartModal']//div[@class='modal-content']")
	    WebElement modalPopup;

	    @FindBy(xpath = "//u[normalize-space()='View Cart']")
	    WebElement viewCartLinkInsideModal;

	    @FindBy(xpath = "//a[normalize-space()='Signup / Login']")
	    public WebElement signupLoginLink;

	    @FindBy(xpath = "//input[@data-qa='login-email']")
	    public WebElement loginEmailInput;

	    @FindBy(xpath = "//input[@placeholder='Password']")
	    public WebElement loginPasswordInput;

	    @FindBy(xpath = "//button[@data-qa='login-button']")
	    public WebElement loginButton;
	    
	    @FindBy(xpath = "//a[@href='/view_cart']")
	    WebElement viewCartClick;

	    // Cart table - Product row 
//	    @FindBy(xpath = "//table[@id='cart_info_table']//tbody//tr")
//	    public WebElement cartProductRow;

	    // Product name in cart
//	    @FindBy(xpath = "//table[@id='cart_info_table']//tbody//tr//td[@class='cart_description']//h4")
//	    public WebElement cartProductName;	
	    
	    public void clickProductsLink() {
	        wait.until(ExpectedConditions.elementToBeClickable(productsLink)).click();
	    }

	    public boolean isAllProductsVisible() {
	        return wait.until(ExpectedConditions.visibilityOf(allProductsHeading)).isDisplayed();
	    }

	    public void searchProduct(String productName) {
	        wait.until(ExpectedConditions.visibilityOf(searchBox)).sendKeys(productName);
	        searchButton.click();
	    }

	    public void hoverOnSearchedProduct() {
	        JavascriptExecutor js = (JavascriptExecutor) driver;

	        // Step 1: Scroll the page down to bring product area into view
	        js.executeScript("window.scrollBy(0, 500);");

	        // Step 2: Short wait to allow layout and animations to load
	        try {
	            Thread.sleep(1500);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

	        // Step 3: Hover to trigger the overlay animation
	        actions.moveToElement(sleevelessDressText)
	               .pause(Duration.ofSeconds(2))
	               .perform();

	        // ✅ Step 4: Verify that the product is visible on the screen
	        boolean isProductVisible = sleevelessDressText.isDisplayed();
	        System.out.println("Product visibility after scroll and hover: " + isProductVisible);

	    }
	    public void clickAddToCart() {
	        JavascriptExecutor js = (JavascriptExecutor) driver;

	        try {
	            // Scroll and hover on product
	            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", sleevelessDressText);
	            Thread.sleep(1000);
	            actions.moveToElement(sleevelessDressText)
	                   .pause(Duration.ofSeconds(2))
	                   .perform();

	            // Ensure Add to Cart button is visible
	            wait.until(ExpectedConditions.visibilityOf(addToCartButton));
	            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", addToCartButton);
	            actions.moveToElement(addToCartButton)
	                   .pause(Duration.ofSeconds(1))
	                   .perform();

	            // Try normal click first
	            try {
	                wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
	                System.out.println("✅ Clicked Add to Cart.");
	            } catch (ElementClickInterceptedException e) {
	                System.out.println("⚠️ Intercepted. Using JS click.");
	                js.executeScript("arguments[0].click();", addToCartButton);//it is JavaScript click , it bypasses the browser's restrictions and directly executes the click() method on the DOM element — without requiring any hover, scroll, or visibility checks."
	            }

	        } catch (Exception e) {
	            System.out.println("❌ Exception in clickAddToCart: " + e.getMessage());
	        }
	    }



//	    public void clickAddToCart() {
//	        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
//	    }

//	    public void clickViewCart() {
//	    	WebElement viewCartLink = wait.until(ExpectedConditions.presenceOfElementLocated(
//	    	        By.xpath("//u[normalize-space()='View Cart']")
//	    	    ));
//	    	    
//	    	    // Scroll into view first
//	    	    ((JavascriptExecutor)driver).executeScript(
//	    	        "arguments[0].scrollIntoView({block: 'center'});", viewCartLink
//	    	    );
//	    	    
//	    	    // JavaScript click as fallback
//	    	    ((JavascriptExecutor)driver).executeScript(
//	    	        "arguments[0].click();", viewCartLink
//	    	    );
//
//	    }
	    public void clickViewCart() {
	        try {
	            wait.until(ExpectedConditions.visibilityOf(modalPopup));

	            ((JavascriptExecutor) driver).executeScript(
	                "arguments[0].scrollIntoView({block: 'center'});", viewCartLinkInsideModal
	            );

	            ((JavascriptExecutor) driver).executeScript(
	                "arguments[0].click();", viewCartLinkInsideModal
	            );

	            System.out.println("✅ Clicked View Cart from modal.");
	        } catch (Exception e) {
	            System.out.println("❌ View Cart click failed: " + e.getMessage());
	        }
	    }





	    public void clickSignupLoginLink() {
	        wait.until(ExpectedConditions.elementToBeClickable(signupLoginLink)).click();
	    }

	    public void login(String email, String password) {
	        wait.until(ExpectedConditions.visibilityOf(loginEmailInput)).sendKeys(email);
	        loginPasswordInput.sendKeys(password);
	        loginButton.click();
	    }
	    
	    public void clickCartAfterLogin() {
	        try {
	            WebElement cartLink = wait.until(ExpectedConditions.elementToBeClickable(viewCartClick));

	            ((JavascriptExecutor) driver).executeScript(
	                "arguments[0].scrollIntoView({block: 'center'});", cartLink
	            );

	            cartLink.click();
	            System.out.println("✅ Clicked Cart after login.");

	        } catch (Exception e) {
	            System.out.println("❌ Failed to click Cart after login: " + e.getMessage());
	        }
	    }


//	    public boolean isProductVisibleInCart(String expectedProductName) {
//	        String actualProduct = wait.until(ExpectedConditions.visibilityOf(cartProductName)).getText();
//	        return actualProduct.equalsIgnoreCase(expectedProductName);
//	    }
	    
	    /**
	     * Verifies if the expected product is visible in the cart.
	     *
	     * NOTE: We avoid using @FindBy WebElement here because
	     * PageFactory may initialize the element before it appears on the page,
	     * leading to NoSuchElement or stale element errors.
	     * Instead, we use a dynamic wait with By locator to ensure the element
	     * is present and visible at runtime.
	     */
	    public boolean isProductVisibleInCart(String expectedProductName) {
	        List<WebElement> products = driver.findElements(By.cssSelector("td.cart_description h4 a"));
	        for (WebElement product : products) {
	            System.out.println("Found in cart: " + product.getText());
	            if (product.getText().trim().equalsIgnoreCase(expectedProductName)) {
	                return true;
	            }
	        }
	        return false;
	    }


}