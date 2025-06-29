package Pages;

import java.sql.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupNegativePage {
	WebDriver driver;

    @FindBy(xpath = "//input[@placeholder='Name']")
    WebElement nameField;

    @FindBy(xpath = "//input[@data-qa='signup-email']")
    WebElement emailField;

    @FindBy(xpath = "//button[text()='Signup']")
    WebElement signupButton;

    @FindBy(xpath = "//p[contains(text(),'Email Address already exist!')]")
    private WebElement errorMessage;
    
    public SignupNegativePage(WebDriver driver) {
	    if (driver == null) {
	        throw new IllegalArgumentException("WebDriver instance is null!");
	    }
	    this.driver = driver;  // Assign passed driver instance
	    PageFactory.initElements(driver, this);
	}

    public void enterNameAndEmail(String name, String email) {
        nameField.clear();
        nameField.sendKeys(name);
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void clickSignup() {
        signupButton.click();
    }

    public String getErrorMessage() {
        return errorMessage.getText().trim();
    }
}
