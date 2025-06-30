package Pages;

import java.time.Duration;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.Driver;

public class HomePage {
	 WebDriver driver = Driver.getDriver();
	 
	 public HomePage() {
		  this.driver = Driver.getDriver();
		  PageFactory.initElements(driver, this);
	 }
	 
	 @FindBy(xpath = "//section[@id='slider']//div[@class='item active']")
	 WebElement homeCarousel;
	 
	 @FindBy(xpath="//a[normalize-space()='Signup / Login']")
	 WebElement signupLoginBtn ;
	 
//	 public boolean isHomePageVisible() {
//		    // Check if the unique home page carousel is displayed
//		    return homeCarousel.isDisplayed();
//		}
	 public boolean isHomePageVisible() {
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    try {
		        WebElement slider = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("slider-carousel")));
		        return slider.isDisplayed();
		    } catch (TimeoutException | NoSuchElementException e) {
		        return false;
		    }
		}

	 public void clickSignupLogin() {
		 signupLoginBtn.click();

	 }
}
