package Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
	 WebDriver driver;
	 WebDriverWait wait;
	 Actions actions;
	
	 @FindBy(xpath = "//a[@href='/products']")
	 WebElement productsButton;
	 
	// First Product Element
	 @FindBy(xpath = "(//div[@class='productinfo text-center'])[1]")
	 WebElement firstProductBox;

	 @FindBy(xpath = "(//a[normalize-space()='Add to cart'])[1]")
	 WebElement firstAddToCart;

	 // Second Product Element
	 @FindBy(xpath = "(//div[@class='productinfo text-center'])[2]")
	 WebElement secondProductBox;

	 @FindBy(xpath = "(//div[@class='overlay-content'])[2]//a[contains(text(),'Add to cart')]")
	 WebElement secondAddToCart;

	 // Continue Shopping Button
	 @FindBy(xpath = "//button[@class='btn btn-success close-modal btn-block']")
	 WebElement continueShoppingButton;

	 // View Cart Button
	 @FindBy(xpath = "//u[normalize-space()='View Cart']")
	 WebElement viewCartButton;


	 // All cart rows (<tr> elements for each product)
	 @FindBy(xpath = "//table[@class='table table-condensed']//tbody//tr")
	 List<WebElement> cartRows;

	 // Optional: Product names inside cart (usually inside <h4><a>)
	 @FindBy(xpath = "//table[@class='table table-condensed']//h4/a")
	 List<WebElement> productNames;
	 
	 public CartPage(WebDriver driver) {
		    this.driver = driver;
	        PageFactory.initElements(driver, this);
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // ✅ Properly initialized
	        actions = new Actions(driver);
	    }
	 public void clickProductsButton() {
		    wait.until(ExpectedConditions.elementToBeClickable(productsButton)).click();
		}
	 
	 public void hoverAndClickAddToCart(WebElement product, WebElement addToCart) {
		    // ✅ Scroll to bring product into center view
		    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", product);

		    // ✅ Give time for layout & animations
		    try {
		     Thread.sleep(1000); // Animation complete hone ka wait
		 } catch (InterruptedException e) {
		     Thread.currentThread().interrupt();
		 }

		    // ✅ Hover on product
		    actions.moveToElement(product).perform();

		    // ✅ Ensure "Add to Cart" appears inside overlay
		    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='overlay-content']")));
		    wait.until(ExpectedConditions.visibilityOf(addToCart));
		    wait.until(ExpectedConditions.elementToBeClickable(addToCart));

		    // ✅ Click safely
		    try {
		        addToCart.click();
		    } catch (Exception e) {
		        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCart);
		    }
		}



	    public void addFirstProductToCart() {
	        hoverAndClickAddToCart(firstProductBox, firstAddToCart);
	    }

	    public void addSecondProductToCart() {
	        // ✅ Wait for modal to disappear after "Continue Shopping"
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='modal-content']")));

	        // ✅ Extra pause to ensure proper DOM settlement after modal close
	        try {
	            Thread.sleep(1500);  // Animation settle hone ka wait
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	        }

	        // ✅ Hover over second product properly
	        actions.moveToElement(secondProductBox).perform();

	        // ✅ Ensure overlay is visible before proceeding
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='overlay-content'])[2]")));

	        // ✅ Make sure the "Add to Cart" button is visible and clickable
	        wait.until(ExpectedConditions.visibilityOf(secondAddToCart));
	        wait.until(ExpectedConditions.elementToBeClickable(secondAddToCart));

	        // ✅ Perform click safely, using JavaScriptExecutor if needed
	        try {
	            secondAddToCart.click();
	        } catch (Exception e) {
	            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", secondAddToCart);
	        }
	    }


	    public void clickContinueShopping() {
	        wait.until(ExpectedConditions.elementToBeClickable(continueShoppingButton)).click();
	    }

	    public void clickViewCart() {
	        wait.until(ExpectedConditions.elementToBeClickable(viewCartButton)).click();
	    }
    
    
	    // ✅ Returns true if at least one product exists
	    public boolean isCartNotEmpty() {
	        return cartRows.size() > 0;
	    }

	    // ✅ Returns total number of items in cart
	    public int getProductCount() {
	        return cartRows.size();
	    }

	    // ✅ Optional: Print all product names
	    public void printProductNames() {
	        for (WebElement product : productNames) {
	            System.out.println("Product: " + product.getText());
	        }
	    }
	    
	    public void scrollToCartTable() {
	        WebElement table = driver.findElement(By.xpath("//table[@class='table table-condensed']"));
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", table);
	    }
    
}
