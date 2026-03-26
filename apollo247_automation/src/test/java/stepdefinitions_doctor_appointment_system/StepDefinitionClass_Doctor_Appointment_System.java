package stepdefinitions_doctor_appointment_system;

import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import driver.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages_doctor_appointment_system.AppointmentDetailsPage;
import pages_doctor_appointment_system.DoctorProfilePage;
import pages_doctor_appointment_system.DoctorsListPage;
import pages_doctor_appointment_system.Find_DoctorsPage;
import pages_doctor_appointment_system.LoginPage;
import pages_doctor_appointment_system.PaymentPage;

public class StepDefinitionClass_Doctor_Appointment_System {
	WebDriver driver=Driver.getDriver();
	LoginPage loginPage;
	PaymentPage paymentPage=new PaymentPage(driver);
	Find_DoctorsPage find_DoctorsPage=new Find_DoctorsPage(driver);
	DoctorsListPage doctorsListPage=new DoctorsListPage(driver);
	DoctorProfilePage doctorProfilePage=new DoctorProfilePage(driver);
	AppointmentDetailsPage appointmentDetailsPage=new AppointmentDetailsPage(driver);
@Given("the user has opened the browser and navigated to the Apollo247 website")
public void the_user_has_opened_the_browser_and_navigated_to_the_apollo247_website() {
    // Write code here that turns the phrase above into concrete actions
	driver.get("https://www.apollo247.com/");
}
@Given("the user is logged in to the website")
public void the_user_is_logged_in_to_the_website() throws InterruptedException {
    // Write code here that turns the phrase above into concrete actions
//	    loginPage = new LoginPage(driver);
//	    loginPage.closePopup();
//	    loginPage.clickLogin();
//	    loginPage.enterMob("8697615296");
//	    loginPage.clickContinue();
//	    Thread.sleep(20000);
//	    loginPage.clickVerify();
	    loginPage = new LoginPage(driver);
	    if (!loginPage.isLoggedIn()) {
	        loginPage.closePopup();
	        loginPage.clickLogin();
//	        loginPage.enterMob("8697615296");
	        Thread.sleep(20000);
	        loginPage.clickContinue();
	        Thread.sleep(20000); // wait for OTP manually
	        loginPage.clickVerify();
	    }
}
	    
@Given("the user is on the Apollo247 home page")
public void the_user_is_on_the_apollo247_home_page() {
    // Write code here that turns the phrase above into concrete actions
}
@When("the user clicks on {string}")
public void the_user_clicks_on(String string) throws InterruptedException {
    // Write code here that turns the phrase above into concrete actions
	Thread.sleep(20000);
    find_DoctorsPage.findDoctorButtonClick();
}
@When("the user enters {string} in the search bar")
public void the_user_enters_in_the_search_bar(String string) {
    // Write code here that turns the phrase above into concrete actions
	find_DoctorsPage.searchBarClick();
	find_DoctorsPage.enterDoctorName(string);
}
@Then("a {string} message should appear")
public void a_message_should_appear(String string) {
    // Write code here that turns the phrase above into concrete actions
	Assert.assertEquals(find_DoctorsPage.getNoResultText(),string);
    
}

@Then("a list of doctors should appear")
public void a_list_of_doctors_should_appear() {
    // Write code here that turns the phrase above into concrete actions
	 Assert.assertTrue(doctorsListPage.isDoctorListDisplayed(), 
		        "Doctor list is not displayed");
}
@Then("{string} should be part of that list")
public void should_be_part_of_that_list(String string) {
    // Write code here that turns the phrase above into concrete actions
	Assert.assertTrue(doctorsListPage.isDoctorPresent(string), 
	        string + " not found in doctor list");
}



@Then("a list of doctors and specialties related to gynaecology should appear")
public void a_list_of_doctors_and_specialties_related_to_gynaecology_should_appear() {
    // Write code here that turns the phrase above into concrete actions
	   
	    boolean resultsDisplayed = find_DoctorsPage.areDoctorsDisplayed();
	    
	    if(!resultsDisplayed) {
	        throw new AssertionError("No doctors or specialties were displayed for gynaecology");
	    }
	}

@Given("the user has searched for a specialty on the {string} page")
public void the_user_has_searched_for_a_specialty_on_the_page(String string) {
    // Write code here that turns the phrase above into concrete actions
	find_DoctorsPage.findDoctorButtonClick();
	find_DoctorsPage.searchBarClick();
	find_DoctorsPage.enterSpecialty("Gynaecology");
}
@Given("the user has clicked on the required specialty from the list")
public void the_user_has_clicked_on_the_required_specialty_from_the_list() {
    // Write code here that turns the phrase above into concrete actions
	find_DoctorsPage.viewDoctorsButtonClick();
}
@When("the user selects the {string} option under the {string} filter section")
public void the_user_selects_the_option_under_the_filter_section(String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
	doctorsListPage.experienceFilterClick();
}
@Then("only doctors with {int} to {int} years of experience should be displayed")
public void only_doctors_with_to_years_of_experience_should_be_displayed(Integer int1, Integer int2) {
    // Write code here that turns the phrase above into concrete actions
		    List<Integer> experiences = doctorsListPage.getDoctorsExperience();
	    
	    for (Integer exp : experiences) {
	        if (exp < int1 || exp > int2) {
	            throw new AssertionError("Doctor with experience " + exp + " is outside the range " + int1 + "-" + int2);
	        }
	    }
	    
	    System.out.println("All doctors have experience within the range " + int1 + "-" + int2);
	}


@Given("the user has searched for a doctor or specialty")
public void the_user_has_searched_for_a_doctor_or_specialty() {
    // Write code here that turns the phrase above into concrete actions
	find_DoctorsPage.findDoctorButtonClick();
    find_DoctorsPage.searchBarClick();
    find_DoctorsPage.enterDoctorName("Ramna Banerjee");
    find_DoctorsPage.selectDoctorRamna();
}
@Given("the user is on the doctor's profile page")
public void the_user_is_on_the_doctor_s_profile_page() {
    // Write code here that turns the phrase above into concrete actions
    doctorProfilePage.onlineConsultButtonClick();
    doctorProfilePage.scheduleAppointmentClick();
}
@Then("the user should be navigated to the {string} page")
public void the_user_should_be_navigated_to_the_page(String string) {
    // Write code here that turns the phrase above into concrete actions
	Assert.assertTrue(
            appointmentDetailsPage.isOnAppointmentDetailsPage(),
            "User is NOT on Appointment Details page"
        );
}


@Given("the user has searched for a doctor and scheduled an appointment")
public void the_user_has_searched_for_a_doctor_and_scheduled_an_appointment() {
    // Write code here that turns the phrase above into concrete actions
    find_DoctorsPage.findDoctorButtonClick();
    find_DoctorsPage.searchBarClick();
    find_DoctorsPage.enterDoctorName("Ramna Banerjee");
    find_DoctorsPage.selectDoctorRamna();
    doctorProfilePage.onlineConsultButtonClick();
    doctorProfilePage.scheduleAppointmentClick();
}
@Given("the user is on the {string} page")
public void the_user_is_on_the_page(String pageName) {

    switch (pageName.toLowerCase()) {

        case "appointment details":
            Assert.assertTrue(
                appointmentDetailsPage.isOnAppointmentDetailsPage(),
                "User is NOT on Appointment Details page"
            );
            break;

        case "payment options":
        	appointmentDetailsPage.confirmAndPayButtonClick();
            Assert.assertTrue(
                paymentPage.isOnPaymentPage(),
                "User is NOT on Payment Options page"
            );
            break;

        default:
            throw new IllegalArgumentException("Unknown page: " + pageName);
    }
}
@When("the user navigates to the patient's contact number field")
public void the_user_navigates_to_the_patient_s_contact_number_field() {
    // Write code here that turns the phrase above into concrete actions
    appointmentDetailsPage.deletePhoneNumber();
}
@When("the user enters {string} in the contact number field")
public void the_user_enters_in_the_contact_number_field(String string) {
    // Write code here that turns the phrase above into concrete actions
    appointmentDetailsPage.enterPhoneNumber(string);
}
@When("the user clicks on the {string} button")
public void the_user_clicks_on_the_button(String string) {
    // Write code here that turns the phrase above into concrete actions
    appointmentDetailsPage.confirmAndPayButtonClick();
}
@Then("an {string} alert should be displayed")
public void an_alert_should_be_displayed(String expectedText) {
    // Write code here that turns the phrase above into concrete actions

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    // 1) Wait for alert
	    Alert alert = wait.until(ExpectedConditions.alertIsPresent());

	    // 2) Get alert text
	    String actualText = alert.getText();

	    // 3) Assert
	    Assert.assertEquals(actualText.trim(), expectedText.trim(),
	            "Alert text mismatch");

	    // 4) Close alert
	    alert.accept();   // or alert.dismiss();
	}


@When("the user clears the existing contact number")
public void the_user_clears_the_existing_contact_number() {
    // Write code here that turns the phrase above into concrete actions
	appointmentDetailsPage.deletePhoneNumber();
}

}

