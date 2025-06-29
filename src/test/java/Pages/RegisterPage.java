package Pages;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.Driver;

public class RegisterPage {

	WebDriver driver = Driver.getDriver();
	
	@FindBy(xpath="//b[normalize-space()='Enter Account Information']")
	WebElement AccountInfoText;
	
	@FindBy(id="id_gender1")  // Mr. radio button
	WebElement titleRadio;

	@FindBy(id="password")
	WebElement passwordField;

	@FindBy(id="days")
	WebElement daysDropdown;

	@FindBy(id="months")
	WebElement monthsDropdown;

	@FindBy(id="years")
	WebElement yearsDropdown;
	
	@FindBy(id="newsletter")
	WebElement newsletterClick;
	
	@FindBy(xpath="//input[@id='optin']")
	WebElement optinClick;
	
	// Address section fields
	@FindBy(id = "first_name")
	WebElement firstName;

	@FindBy(id = "last_name")
	WebElement lastName;

	@FindBy(id = "company")
	WebElement company;

	@FindBy(id = "address1")
	WebElement address1;

	@FindBy(id = "address2")
	WebElement address2;

	@FindBy(id = "country")
	WebElement countryDropdown;
	
	@FindBy(id = "state")
	WebElement state;

	@FindBy(id = "city")
	WebElement city;

	@FindBy(id = "zipcode")
	WebElement zipcode;

	@FindBy(id = "mobile_number")
	WebElement mobileNumber;
	
	@FindBy(xpath = "//button[normalize-space()='Create Account']")
	WebElement submitForm;
	
	@FindBy(xpath = "//b[normalize-space()='Account Created!']")
	WebElement accountCreatedText;

	@FindBy(xpath="//a[@class='btn btn-primary']")
	WebElement continueButton;
	
	@FindBy(xpath="//a[contains(text(),'Logged in as')]")
	WebElement loggedIn;
	
	@FindBy(xpath = "//a[normalize-space()='Logout']")
	WebElement logoutButton;
	
//	@FindBy(xpath="//a[normalize-space()='Delete Account']")
//	WebElement DelAcc;
//	
//	@FindBy(xpath="//b[normalize-space()='Account Deleted!']")
//	WebElement DelAccVisible;
	
	public RegisterPage() {
		PageFactory.initElements(driver, this);  // Initialize elements using PageFactory
	}
	public boolean isEnterAccountInfoVisible() {
		WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));
	    wait.until(ExpectedConditions.visibilityOf(AccountInfoText));
	    return AccountInfoText.isDisplayed();
	}
	
	public void registerForm() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    try {
	        // Click on Mr. radio button
	        wait.until(ExpectedConditions.elementToBeClickable(titleRadio)).click();
	        // Enter password
	        wait.until(ExpectedConditions.visibilityOf(passwordField));
	        passwordField.sendKeys("rahul@123");

	        // Select date of birth
	        wait.until(ExpectedConditions.elementToBeClickable(daysDropdown));
	        wait.until(ExpectedConditions.elementToBeClickable(monthsDropdown));
	        wait.until(ExpectedConditions.elementToBeClickable(yearsDropdown));

	        new Select(daysDropdown).selectByValue("9");
	        new Select(monthsDropdown).selectByVisibleText("September");
	        new Select(yearsDropdown).selectByValue("1991");

	     // ✅ Take screenshot after form is filled
	        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

	        // ✅ Generate unique name using timestamp
	        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	        String fileName = "screenshots/register_form_filled_" + timestamp + ".png";

	        // ✅ Save screenshot
	        FileUtils.copyFile(screenshot, new File(fileName));
	        
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        System.out.println("Unexpected error during registration form fill: " + e.getMessage());
	    }
	}
	public void optionBox() {
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    
	    if (!newsletterClick.isSelected()) {
	        js.executeScript("arguments[0].click();", newsletterClick);
	    }

	    if (!optinClick.isSelected()) {
	        js.executeScript("arguments[0].click();", optinClick);
	    }
	}

//	public void optionBox() {
//		if(!newsletterClick.isSelected()) {
//			newsletterClick.click();
//		}
//		if(!optinClick.isSelected()) {
//			optinClick.click();
//		}
//	}
	public void selectCountry() {
		Select countrySelect=new Select(countryDropdown);
		countrySelect.selectByVisibleText("India");
	}
	public void fillAddressDetails() {
	    firstName.sendKeys("Rahul");
	    lastName.sendKeys("Verma");
	    company.sendKeys("TestCompany");
	    address1.sendKeys("123 Street A");
	    address2.sendKeys("Landmark B");
	    selectCountry();
	    state.sendKeys("Delhi");
	    city.sendKeys("New Delhi");
	    zipcode.sendKeys("110001");
	    mobileNumber.sendKeys("9876543210");
	}
	public void submit() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    WebElement createBtn = wait.until(ExpectedConditions.elementToBeClickable(submitForm));

	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].click();", createBtn);
	}

	public boolean isAccountCreatedVisible() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOf(accountCreatedText));
	    return accountCreatedText.isDisplayed();
	}
	// POM Class - RegisterPage.java
	public void clickContinueButton() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    // Wait until element is clickable (using WebElement directly)
	    wait.until(ExpectedConditions.elementToBeClickable(continueButton));
	    
	    // JavaScript click in case normal click fails due to overlay
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].click();", continueButton);
	}
	public boolean isLoggedInVisible() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOf(loggedIn));
	    return loggedIn.isDisplayed();
	}
	
	public void logout() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
	}
//	public void DeleteAccount() {
//		DelAcc.click();
//	}
//	public boolean isDeleteAccVisible() {
//	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//	    wait.until(ExpectedConditions.visibilityOf(DelAccVisible));
//	    return DelAccVisible.isDisplayed();
//	}
	 }
