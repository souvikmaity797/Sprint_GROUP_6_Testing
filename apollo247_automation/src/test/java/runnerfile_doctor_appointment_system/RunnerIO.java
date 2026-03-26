package runnerfile_doctor_appointment_system;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
@CucumberOptions(features = "src/test/java/featurefile_doctor_appointment_system/find_doctor_and_booking.feature",
glue = {"stepdefinitions_doctor_appointment_system","hooks"},
dryRun = false)
public class RunnerIO extends AbstractTestNGCucumberTests{

}
