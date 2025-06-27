package Pages;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterWhileCheckoutPage {
	 WebDriver driver;
	    WebDriverWait wait;

	    public RegisterWhileCheckoutPage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    }
	
	@FindBy(xpath = "//a[normalize-space()='Cart']")
	WebElement cartButton;
	
	@FindBy(xpath = "//a[contains(text(),'Proceed To Checkout')]")
	WebElement proceedToCheckoutButton;
	
	@FindBy(xpath = "//u[normalize-space()='Register / Login']")
	WebElement registerLoginButton;
	
	@FindBy(xpath = "//h2[contains(text(),'New User Signup!')]")
    WebElement newUserSignupHeading;

    @FindBy(xpath = "//b[normalize-space()='Enter Account Information']")
    WebElement accountInformationForm;
    
    @FindBy(xpath = "//button[contains(text(), 'No, Thanks')]")
    WebElement modalButton;
    
	
//	public void clickButton(String buttonName) {
//        switch (buttonName.toLowerCase()) {
//            case "register / login":
//                registerLoginButton.click();
//                break;
//            case "cart":
//                cartButton.click();
//                break;
//            case "proceed to checkout":
//                proceedToCheckoutButton.click();
//                break;
//            default:
//                throw new IllegalArgumentException("Invalid button name: " + buttonName);
//        }
//    }
    
    public void clickRegisterLoginButton() {
        registerLoginButton.click();
    }

    public void clickCartButton() {
        cartButton.click();
    }

    public void clickProceedToCheckoutButton() {
//        try {
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//
//            // Wait for modal and click 'No, Thanks' if present
//            wait.until(ExpectedConditions.elementToBeClickable(modalButton)).click();
//
//            // Wait for modal to disappear
//            wait.until(ExpectedConditions.invisibilityOf(modalButton));
//
//        } catch (Exception e) {
//            System.out.println("Modal not found or JS click failed.");
//        }

        // Click on Proceed to Checkout using JS (if needed)
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", proceedToCheckoutButton);
        } catch (Exception e) {
            System.out.println("Proceed to Checkout button click failed.");
        }
    }


	
	public boolean isSignupHeadingVisible() {
        return newUserSignupHeading.isDisplayed();
    }

    public boolean isAccountInformationFormVisible() {
        return accountInformationForm.isDisplayed();
    }
//	public void clickCartButton() {
//	    cartButton.click();
//	}
//	
//	public void clickProceedToCheckoutButton() {
//	    proceedToCheckoutButton.click();
//	}
//	
//	public void clickRegisterLoginButton() {
//	    registerLoginButton.click();
//	}
}
