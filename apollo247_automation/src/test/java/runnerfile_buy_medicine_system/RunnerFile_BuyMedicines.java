package runnerfile_buy_medicine_system;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/featurefile",
        glue = {"stepdefinitions", "hooks"},
//        tags = "@TC_06",
        		plugin = {
        		        "pretty",
        		        "html:target/report.html",
        		        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        		    },
        monochrome = true
)
public class RunnerFile_BuyMedicines extends AbstractTestNGCucumberTests {
//	@Override
//	@DataProvider(parallel = true)
//	public Object[][] scenarios() {
//
//	    try {
//	        Thread.sleep(15000); 
//	    } catch (InterruptedException e) {
//	        e.printStackTrace();
//	    }
//
//	    return super.scenarios();
//	}
}