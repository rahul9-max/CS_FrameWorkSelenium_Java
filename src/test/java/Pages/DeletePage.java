package Pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.Driver;

public class DeletePage {
	WebDriver driver = Driver.getDriver();

	    @FindBy(xpath="//a[contains(text(),'Logged in as')]")
	    private WebElement loggedInText;

	    @FindBy(xpath="//a[normalize-space()='Logout']")
	    private WebElement logoutButton;

	    @FindBy(xpath="//a[normalize-space()='Delete Account']")
	    private WebElement deleteAccountButton;

	    @FindBy(xpath="//b[normalize-space()='Account Deleted!']")
	    private WebElement accountDeletedMessage;

	    public DeletePage() {
	       PageFactory.initElements(driver, this);
	    }

	    public boolean isUserLoggedIn() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	        wait.until(ExpectedConditions.visibilityOf(loggedInText));
	        return loggedInText.isDisplayed();
	    }

	    public void deleteAccount() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.elementToBeClickable(deleteAccountButton)).click();
	    }

	    public boolean isAccountDeleted() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.visibilityOf(accountDeletedMessage));
	        return accountDeletedMessage.isDisplayed();
	    }
}
