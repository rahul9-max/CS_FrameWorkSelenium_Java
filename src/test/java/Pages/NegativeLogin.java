package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NegativeLogin {
	WebDriver driver;

	@FindBy(xpath="//input[@data-qa='login-email']")
	WebElement emailField;

    @FindBy(xpath="//input[@placeholder='Password']")
    WebElement passwordField;

    @FindBy(xpath="//button[normalize-space()='Login']")
    WebElement loginButton;

    @FindBy(xpath= "//p[normalize-space()='Email Address already exist!']")  
    WebElement errorMessage;
    
 // ✅ Constructor that accepts WebDriver
    public NegativeLogin(WebDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver instance is null!");
        }
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void enterCredentials(String email, String password) {
        emailField.clear();
        emailField.sendKeys(email);
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLogin() {
        loginButton.click();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}
