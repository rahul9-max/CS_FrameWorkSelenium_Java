package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ScrollNavigationPage {

    WebDriver driver;

    public ScrollNavigationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Subscription section at the bottom
    @FindBy(xpath = "//h2[normalize-space()='Subscription']")
    public WebElement subscriptionSection;

    // Arrow button to scroll to top
    @FindBy(xpath = "//i[@class='fa fa-angle-up']")
    public WebElement scrollUpArrow;

    // Header text at top after scroll
    @FindBy(xpath = "//div[@class='item active']//h2[contains(text(),'Full-Fledged practice website for Automation Engin')]")
    public WebElement fullFledgedHeader;

    public void scrollToBottom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    // Optional method to scroll down (can be used via JSExecutor or Actions class)
    public void clickScrollUpArrow() {
        scrollUpArrow.click();
    }

    public boolean isSubscriptionVisible() {
        return subscriptionSection.isDisplayed();
    }

    public boolean isTopHeaderVisible() {
        return fullFledgedHeader.isDisplayed();
    }
    
    public String getTopHeaderText() {
        return fullFledgedHeader.getText();
    }

}
