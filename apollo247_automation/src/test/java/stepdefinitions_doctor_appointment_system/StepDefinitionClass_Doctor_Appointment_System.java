package stepdefinitions_doctor_appointment_system;

import driver.DriverManager;
import hooks.Hooks;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.*;

import pages_doctor_appointment_system.*;
import utils_doctor_appointment_system.ExcelReader;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.testng.Assert;

public class StepDefinitionClass_Doctor_Appointment_System {
    ExcelReader excel = new ExcelReader(
            "src/test/resources/testdata_doctor_appointment_system/TestData_doctor_appointment_system.xlsx",
            "DoctorData"
    );
    private WebDriver driver;
    private LoginPage loginPage;
    private Find_DoctorsPage findDoctorsPage;
    private DoctorsListPage doctorsListPage;
    private DoctorProfilePage doctorProfilePage;
    private AppointmentDetailsPage appointmentDetailsPage;
    String searchText, doctorName, phoneNumber, expectedAlert;

        @Before
        public void init() {
            driver = DriverManager.getDriver();

            loginPage = new LoginPage(driver);
            findDoctorsPage = new Find_DoctorsPage(driver);
            doctorsListPage = new DoctorsListPage(driver);
            doctorProfilePage = new DoctorProfilePage(driver);
            appointmentDetailsPage = new AppointmentDetailsPage(driver);
        }

    // ---------- Background ----------

    @Given("the user has opened the browser and navigated to the Apollo247 website")
    public void openWebsite() {
        driver.get("https://www.apollo247.com/");
    }
//
//    @Given("the user is logged in to the website")
//    public void login() throws InterruptedException {
//    	LoginPage loginPage = new LoginPage(driver);
//        if (!loginPage.isLoggedIn()) {
//            loginPage.closePopup();
//            loginPage.clickLogin();
//            Thread.sleep(20000); // OTP manual
//            loginPage.clickContinue();
//            Thread.sleep(20000);
//            loginPage.clickVerify();
//        }
//    }
    
//    @Given("the user is logged in to the website")
//    public void login() throws InterruptedException {
//        
//
//        if (!Hooks.isLoggedIn && !loginPage.isLoggedIn()) {
//            loginPage.closePopup();
//            loginPage.clickLogin();
//            Thread.sleep(20000);
//            loginPage.clickContinue();
//
//            System.out.println("Waiting for OTP manually...");
//            Thread.sleep(20000); // temporary manual OTP entry
//            loginPage.clickVerify();
//
//            Hooks.isLoggedIn = true;
//
//            // Wait until the profile icon is visible to ensure login is complete
//            new WebDriverWait(driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".profile-icon-class")));
//        }
//    }

    
    @Given("the user is logged in to the website")
    public void login() throws InterruptedException {

        if (!loginPage.isLoggedIn()) {
            loginPage.closePopup();
            loginPage.clickLogin();
            Thread.sleep(20000);
            loginPage.clickContinue();
            Thread.sleep(20000);
            loginPage.clickVerify();
        }
    }
    @Given("the user is on the Apollo247 home page")
    public void onHomePage() { }

    // ---------- Doctor Search ----------

    @When("the user performs doctor search using {string}")
    public void performDoctorSearch(String tcid) throws InterruptedException {
    	Thread.sleep(2000);
    	    Map<String, String> data = excel.getData(tcid);

    	    searchText = data.get("searchText");
    	    findDoctorsPage.findDoctorButtonClick();
    	    findDoctorsPage.searchBarClick();
    	    findDoctorsPage.enterDoctorName(searchText);
    	    Thread.sleep(3000);
    	}

    @Then("relevant doctors should be displayed")
    public void verifyDoctorsDisplayed() throws InterruptedException {
    	Thread.sleep(3000);
    	
        Assert.assertTrue(doctorsListPage.isDoctorListDisplayed());
    }

    @Then("the searched doctor should be present in the results")
    public void verifyDoctorPresent() {
        Assert.assertTrue(doctorsListPage.isDoctorPresent(searchText));
    }

    @Then("doctors related to the specialty should be displayed")
    public void verifySpecialtyDoctors() throws InterruptedException {
    	Thread.sleep(2000);
        Assert.assertTrue(findDoctorsPage.areDoctorsDisplayed());
    }

    	@Then("a no result message should be displayed for {string}")
    	public void verifyNoResult(String tcid) throws InterruptedException {

    	    Map<String, String> data = excel.getData(tcid);

    	    String actual = findDoctorsPage.getNoResultText();
    	    Thread.sleep(2000);
    	    String expected = data.get("expectedMsg");
    	    Thread.sleep(2000);
    	    Assert.assertEquals(actual, expected);
    	}

    // ---------- Experience Filter ----------

    @Given("the user has already searched for doctors using {string}")
    public void searchBeforeFilter(String tcid) throws InterruptedException {
        performDoctorSearch(tcid);
        findDoctorsPage.viewDoctorsButtonClick();
    }

    @When("the user applies experience filter from {int} to {int} years")
    public void applyExperienceFilter(Integer min, Integer max) throws InterruptedException {
    	Thread.sleep(3000);
        doctorsListPage.experienceFilterClick();
    }

    @Then("only doctors within the selected experience range should be displayed")
    public void verifyExperienceRange() throws InterruptedException {
    	Thread.sleep(2000);
        List<Integer> expList = doctorsListPage.getDoctorsExperience();
        for (Integer exp : expList) {
            Assert.assertTrue(exp >= 0 && exp <= 5);
        }
    }

    // ---------- Appointment Flow ----------

    @Given("the user has searched and selected a doctor using {string}")
    public void searchAndSelectDoctor(String tcid) throws InterruptedException {
        performDoctorSearch(tcid);
        Thread.sleep(5000);
        findDoctorsPage.selectDoctorRamna();
    }

    @When("the user proceeds to schedule an appointment")
    public void scheduleAppointment() {
        doctorProfilePage.onlineConsultButtonClick();
        doctorProfilePage.scheduleAppointmentClick();
    }

    @Then("the appointment details page should be displayed")
    public void verifyAppointmentPage() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertTrue(appointmentDetailsPage.isOnAppointmentDetailsPage());
    }

    // ---------- Patient Validation ----------

    @When("the user completes appointment process using {string}")
    public void completeAppointmentProcess(String tcid) throws InterruptedException {

    	    Map<String, String> data = excel.getData(tcid);

    	    searchText   = data.get("searchText");
    	    phoneNumber  = data.get("phoneNumber");
    	    expectedAlert = data.get("expectedMsg");

    	    // search doctor
    	    performDoctorSearch(tcid);
    	    Thread.sleep(2000);
    	    findDoctorsPage.selectDoctorRamna();

    	    // go to appointment
    	    scheduleAppointment();

    	    // enter phone
    	    appointmentDetailsPage.deletePhoneNumber();
    	    appointmentDetailsPage.enterPhoneNumber(phoneNumber);
    	    appointmentDetailsPage.confirmAndPayButtonClick();
    	}

    @Then("an appropriate validation alert should be displayed")
    public void verifyAlert() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        Assert.assertEquals(alert.getText().trim(), expectedAlert.trim());
        alert.accept();
    }
}
