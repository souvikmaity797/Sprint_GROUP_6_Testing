package testrunner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/apollo.feature",
        glue = "stepdefinitions",
        dryRun=false,
        		 plugin = {
        		            "pretty",
        		            "html:target/cucumber-report.html",
        		            "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        		    },
        		 monochrome = true
)
public class RunnerIO extends AbstractTestNGCucumberTests {
	
	/**
     * Overriding scenarios() with parallel=true tells TestNG to run
     * each row returned by the DataProvider (i.e. each Cucumber scenario)
     * in a separate thread simultaneously.
     *
     * Thread count is controlled by the <thread-count> in testng.xml.
     */
 
}
