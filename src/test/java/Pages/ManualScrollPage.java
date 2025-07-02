package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ManualScrollPage {

	WebDriver driver;

    public ManualScrollPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
 // Scroll manually to the top using JS
    public void scrollToTopManually() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0)");
    }

}
