package TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

@CucumberOptions(
    features = "src/test/resources/Features",
    glue = {"StepDefinitions"},  
    tags = "@ScrollWithoutArrow",  // only run scenarios with this tag
    plugin = {
        "pretty",
        "html:target/cucumber-reports/cartAddReport.html",
        "json:target/cucumber-reports/cartAddReport.json",
        "junit:target/cucumber-reports/cartAddReport.xml"
    },
    monochrome = true
)
public class Test extends AbstractTestNGCucumberTests {
	
	@BeforeClass
    public void suiteStart() {
        System.out.println("=== TEST SUITE STARTED ===");
    }

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @AfterClass
    public void suiteEnd() {
        System.out.println("=== TEST SUITE COMPLETED ===");
    }
}
