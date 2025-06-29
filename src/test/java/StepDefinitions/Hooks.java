package StepDefinitions;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;
import utilities.Driver;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Hooks {

    @AfterStep
    public void takeScreenshotOnFailure(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                // Capture screenshot
                File screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.FILE);

                // Timestamped filename
                String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String fileName = "screenshots/FAIL_" + scenario.getName().replaceAll(" ", "_") + "_" + timestamp + ".png";

                // Ensure folder exists
                new File("screenshots").mkdirs();

                // Save file
                FileUtils.copyFile(screenshot, new File(fileName));

                System.out.println("📸 Screenshot captured: " + fileName);
            } catch (IOException e) {
                System.out.println("❌ Failed to save screenshot: " + e.getMessage());
            }
        }
    }
}
