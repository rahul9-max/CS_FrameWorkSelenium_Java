package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    WebDriver driver;

    @FindBy(xpath="//a[normalize-space()='Signup / Login']") 
    private WebElement loginButton;

    @FindBy(xpath="//h2[normalize-space()='Login to your account']")
    private WebElement loginHeading;

    @FindBy(xpath="//input[@data-qa='login-email']")
    private WebElement emailField;

    @FindBy(xpath="//input[@placeholder='Password']")
    private WebElement passwordField;

    @FindBy(xpath="//button[normalize-space()='Login']")
    private WebElement submitLogin;

    @FindBy(xpath="//a[contains(text(),'Logged in as')]")
    private WebElement loggedInText;

    public LoginPage(WebDriver driver) {
    	 if (driver == null) {
    	        throw new IllegalArgumentException("WebDriver instance is null!");
    	    }
    	    this.driver = driver;
    	    PageFactory.initElements(driver, this);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public boolean isLoginHeadingVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(loginHeading));
        return loginHeading.isDisplayed();
    }

    public void enterCredentials(String email, String password) {
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
    }

    public void submitLogin() {
        submitLogin.click();
    }

    public boolean isLoggedInVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(loggedInText));
        return loggedInText.isDisplayed();
    }
}
