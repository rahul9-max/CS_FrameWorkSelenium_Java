package Pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SubscriptionPage {
	 WebDriver driver;
	 WebDriverWait wait;

	 public SubscriptionPage(WebDriver driver) {
		 this.driver = driver;
		 PageFactory.initElements(driver, this); // PageFactory initialization
		 this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	 }

    // Locators
    @FindBy(xpath = "//h2[normalize-space()='Subscription']")
    WebElement subscriptionText;

    @FindBy(xpath = "//input[@id='susbscribe_email']")
    WebElement emailInput;

    @FindBy(xpath = "//button[@id='subscribe']")
    WebElement subscribeButton;

    @FindBy(xpath = "//div[@class='alert-success alert']")
    WebElement successAlert;

    // Actions
    public void scrollToFooter() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", subscriptionText);
    }

    public boolean isSubscriptionTextVisible(String expectedText) {
        wait.until(ExpectedConditions.visibilityOf(subscriptionText));
        return subscriptionText.getText().equalsIgnoreCase(expectedText);
    }

    public void enterEmailAndClickSubscribe(String email) {
        emailInput.clear();
        emailInput.sendKeys(email);
        subscribeButton.click();
    }

    public boolean isSuccessMessageVisible(String expectedMessage) {
        try {
            wait.until(ExpectedConditions.visibilityOf(successAlert));
            return successAlert.getText().trim().equalsIgnoreCase(expectedMessage);
        } catch (TimeoutException e) {
            return false;
        }
    }

}
