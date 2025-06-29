package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class VerifyPage {

    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public VerifyPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    @FindBy(xpath = "//a[normalize-space()='Test Cases']")
    WebElement testCasesButton;

    @FindBy(xpath = "//b[normalize-space()='Test Cases']")
    WebElement testCasesHeading;

    // Actions
    public void clickTestCases() {
        wait.until(ExpectedConditions.elementToBeClickable(testCasesButton)).click();
    }

    public boolean isTestCasesPageVisible() {
        return wait.until(ExpectedConditions.visibilityOf(testCasesHeading)).isDisplayed();
    }
}
