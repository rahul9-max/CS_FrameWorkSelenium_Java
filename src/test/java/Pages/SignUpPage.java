package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Driver;

public class SignUpPage {
WebDriver driver=Driver.getDriver();

// Constructor to initialize elements
public SignUpPage() {
	PageFactory.initElements(driver,this);
}
@FindBy(xpath="//h2[normalize-space()='New User Signup!']")
WebElement SignUpPage;

@FindBy(xpath="//input[@data-qa='signup-name']")
WebElement nameField ;

@FindBy(xpath="//input[@data-qa='signup-email']")
WebElement emailField;

@FindBy(xpath="//button[@data-qa='signup-button']")
WebElement signupButton;

public boolean isSignUpPageDisplayed() {
	return SignUpPage.isDisplayed();
}
public void enterNameEmail(String name, String email) {
    nameField.sendKeys(name);
    emailField.sendKeys(email);
}

public void clickSignup() {
    signupButton.click();
}
}
