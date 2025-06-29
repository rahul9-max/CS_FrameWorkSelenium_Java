//package TestRunner;
//
//import org.testng.TestNG;
//
//import io.cucumber.testng.AbstractTestNGCucumberTests;
//import io.cucumber.testng.CucumberOptions;
//
//@CucumberOptions(
//    features = "src/test/resources/Features",
//    glue = {"StepDefinitions"},
//    tags = "@teardown" // Only run teardown scenarios
//)
//public class CleanRunner extends AbstractTestNGCucumberTests {
//	public static void runCleanup() {
//	    TestNG testng = new TestNG();
//	    testng.setTestClasses(new Class[] { CleanRunner.class });
//	    testng.run();
//	}
//
//}
