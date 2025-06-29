package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage {
	WebDriver driver;
    WebDriverWait wait;

		@FindBy(xpath = "//a[@href='/products']")
	    private WebElement productsButton;

	    @FindBy(xpath = "//h2[contains(text(),'All Products')]")
	    private WebElement allProductsHeader;

	    @FindBy(id = "search_product")
	    private WebElement searchInput;

	    @FindBy(xpath = "//i[@class='fa fa-search']")
	    private WebElement searchButton;

	    @FindBy(xpath = "//h2[@class='title text-center']")
	    private WebElement searchedProductsHeader;
	    
//	    @FindBy(xpath = "//div[@class='overlay-content']//p[contains(text(),'Winter Top')]")
//	    private WebElement searchedProduct;
	    
	    public SearchPage(WebDriver driver) {
	    	 this.driver = driver;
	         this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // ✅ Properly initialized
	         PageFactory.initElements(driver, this);  // ✅ Important for @FindBy
	    }
	    
	    public void clickProductsButton() {
	        wait.until(ExpectedConditions.elementToBeClickable(productsButton)).click();
	    }

	    public boolean isAllProductsPageVisible() {
	        return wait.until(ExpectedConditions.visibilityOf(allProductsHeader)).isDisplayed();
	    }

	    public void searchForProduct(String productName) {
	        wait.until(ExpectedConditions.visibilityOf(searchInput)).clear();
	        searchInput.sendKeys(productName);
	        searchButton.click();
	    }

	    public boolean isSearchedProductsSectionVisible(String expectedText) {
	        return wait.until(ExpectedConditions.visibilityOf(searchedProductsHeader))
	                   .getText().trim().equalsIgnoreCase(expectedText);
	    }

	    public boolean isProductVisibleAfterScroll(String productName) {
	        // We use this instead of @FindBy to support dynamic values and allow flexible matching at runtime.
	        By dynamicProduct = By.xpath("//p[contains(text(),'" + productName + "')]");
	        
	        try {
	            WebElement product = wait.until(ExpectedConditions.presenceOfElementLocated(dynamicProduct));
	            
	            // Scroll the product into view
	            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", product);
	            Thread.sleep(500); // Optional: wait for smooth scroll (can be removed in stable apps)

	            // Hover over the product (some overlays only appear after hover)
	            Actions actions = new Actions(driver);
	            actions.moveToElement(product).perform();
	            
	            // Final check
	            return product.isDisplayed();
	        } catch (Exception e) {
	            System.out.println("Error while verifying product visibility: " + e.getMessage());
	            return false;
	        }
	    }


}
