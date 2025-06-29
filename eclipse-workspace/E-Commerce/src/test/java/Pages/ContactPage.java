package Pages;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactPage {
    WebDriver driver;
    WebDriverWait wait;

    // ====== Elements ======
    @FindBy(xpath = "//section[@id='slider']//div[@class='item active']")
	 WebElement homeCarousel;
    
    @FindBy(css = "a[href='/contact_us']")
    WebElement contactUsBtn;

    @FindBy(xpath = "//h2[normalize-space()='Get In Touch']")
    WebElement getInTouchHeading;

    @FindBy(xpath = "//input[@placeholder='Name']")
    WebElement nameInput;

    @FindBy(xpath = "//input[@placeholder='Email']")
    WebElement emailInput;

    @FindBy(xpath = "//input[@placeholder='Subject']")
    WebElement subjectInput;

    @FindBy(id = "message")
    WebElement messageInput;

    @FindBy(xpath = "//input[@name='upload_file']")
    WebElement uploadFileBtn;

    @FindBy(xpath = "//input[@name='submit']")
    WebElement submitBtn;

    @FindBy(xpath = "//div[contains(@class,'status')]")
    WebElement successMessage;

    @FindBy(xpath = "//a[normalize-space()='Home']")
    WebElement homeBtn;



    // ====== Actions ======
    public ContactPage(WebDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver instance is null!");
        }
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        Injecting a dependency (WebDriver) into a class that depends on it (ContactPage),
//        Instead of creating the driver inside the class, you're decoupling the logic.
    }

    // Method to verify home page is visible via carousel
    public boolean isHomePageVisible1() {
        try {
            wait.until(ExpectedConditions.visibilityOf(homeCarousel));
            return homeCarousel.isDisplayed();
        } catch (Exception e) {
            System.err.println("Home page carousel not visible: " + e.getMessage());
            return false;
        }
    }
//    public void clickButton(String buttonName) {
//        String btn = buttonName.trim();
//        if (btn.equalsIgnoreCase("Contact us")) {
//            contactUsBtn.click();
//        } else if (btn.equalsIgnoreCase("Submit")) {
//            submitBtn.click();
//        } else if (btn.equalsIgnoreCase("Home")) {
//            homeBtn.click();
//        } else {
//            throw new IllegalArgumentException("Unknown button: " + buttonName);
//        }
//    }
//    public void clickButton(String buttonName) {
//    	System.out.println("Attempting to click: " + buttonName);
//        String btn = buttonName.trim();
//        if (btn.equalsIgnoreCase("Contact us")) {
//            wait.until(ExpectedConditions.elementToBeClickable(contactUsBtn)).click(); // 👈 Updated line
//        } else if (btn.equalsIgnoreCase("Submit")) {
//            wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click(); // (optional)
//        } else if (btn.equalsIgnoreCase("Home")) {
//            wait.until(ExpectedConditions.elementToBeClickable(homeBtn)).click(); // (optional)
//        } else {
//            throw new IllegalArgumentException("Unknown button: " + buttonName);
//        }
//    }
    public void clickContactUs() {
        wait.until(ExpectedConditions.elementToBeClickable(contactUsBtn)).click();
    }

    public boolean isHeadingVisible(String expectedHeading) {
    	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	    try {
    	        wait.until(ExpectedConditions.visibilityOf(getInTouchHeading));
    	        return getInTouchHeading.getText().equalsIgnoreCase(expectedHeading);
    	    } catch (TimeoutException e) {
    	        return false;
    	    }
    }

    public void fillContactForm(String name, String email, String subject, String message) {
        nameInput.clear();
        nameInput.sendKeys(name);

        emailInput.clear();
        emailInput.sendKeys(email);

        subjectInput.clear();
        subjectInput.sendKeys(subject);

        messageInput.clear();
        messageInput.sendKeys(message);
    }

    public void uploadFile(String path) {
        uploadFileBtn.sendKeys(path); // Since uploadFileBtn is an <input type="file"> element, sendKeys(path) works directly without needing to scroll.
    }
    
    public void clickSubmit() {
        try {
            // Scroll into view to minimize overlay chance
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitBtn);

            // Add short delay to ensure layout settles (ads often shift)
            Thread.sleep(800);

            // Now attempt the click
            wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();

        } catch (ElementClickInterceptedException e) {
            System.out.println("Element click intercepted. Trying JavaScript click...");

            // Fallback to JS click if normal click fails
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitBtn);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    
    public void handleAlert(String action) {
        try {
            // 1. Try standard alert first
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
        } catch (TimeoutException e) {
            // 2. If no standard alert, check for custom success message
            List<WebElement> successMsg = driver.findElements(
                By.xpath("//div[contains(text(), 'Success! Your details have been submitted')]")
            );
            
            if (!successMsg.isEmpty()) {
                System.out.println("Success message found: " + successMsg.get(0).getText());
                return; // Exit if success message is visible
            }
            
            // 3. If nothing works, fail with screenshot
//            takeScreenshot("alert_not_found");
            throw new RuntimeException("No alert or success message appeared after submission.");
        }
    }
 //  method for alert handling
//    public void handleAlert(String action) {
//        try {
//            wait.until(ExpectedConditions.alertIsPresent()); // Wait for alert
//            Alert alert = driver.switchTo().alert();         // Switch to alert
//            if (action.equalsIgnoreCase("OK")) {
//                alert.accept();                              // Accept alert
//            } else {
//                alert.dismiss();                             // Dismiss alert
//            }
//        } catch (TimeoutException e) {
//            throw new RuntimeException("Timed out waiting for alert to appear", e);
//        } catch (NoAlertPresentException e) {
//            throw new RuntimeException("No alert was present to handle", e);
//        }
//    }


    public boolean isSuccessMessageVisible(String expectedMessage) {
        wait.until(ExpectedConditions.visibilityOf(successMessage));
        return successMessage.getText().trim().equalsIgnoreCase(expectedMessage.trim());
    }

//    public boolean isHomeVisible() {
//        wait.until(ExpectedConditions.visibilityOf(homeLogo));
//        return homeLogo.isDisplayed();
//    }
}
