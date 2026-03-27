//package runnerfile_doctor_appointment_system;
//
//import io.cucumber.testng.AbstractTestNGCucumberTests;
//import io.cucumber.testng.CucumberOptions;
//@CucumberOptions(features = "src/test/java/featurefile_doctor_appointment_system/find_doctor_and_booking.feature",
//glue = {"stepdefinitions_doctor_appointment_system","hooks"},
//dryRun = false)
//public class RunnerIO extends AbstractTestNGCucumberTests{
//
//}

package runnerfile_doctor_appointment_system;

import driver.BrowserFactory;
import driver.DriverManager;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

@CucumberOptions(
    features = "src/test/java/featurefile_doctor_appointment_system/find_doctor_and_booking.feature",
    glue = {"stepdefinitions_doctor_appointment_system","hooks"},
    plugin = {
            "pretty",
            "html:target/cucumber-report.html",
            "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
    }
)
public class RunnerIO extends AbstractTestNGCucumberTests {

    @Parameters("browser")
    @BeforeClass
    public void setUp(String browser) {
        WebDriver driver = BrowserFactory.createDriver(browser);
        DriverManager.setDriver(driver);
    }

    @AfterClass
    public void tearDown() {
        DriverManager.quitDriver();
    }
}