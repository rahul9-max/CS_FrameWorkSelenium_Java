package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Driver {
	private static WebDriver driver;

    public static void initialize() {
        if (driver == null) {
        	 System.setProperty("webdriver.chrome.driver", "C:\\Users\\thebe\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
             driver = new ChromeDriver();
             driver.manage().window().maximize();
//             driver.get("https://www.automationexercise.com/");
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.close(); // ✅ Close active window
            driver.quit();  // ✅ Quit driver
            driver = null;
        }
    }

}
