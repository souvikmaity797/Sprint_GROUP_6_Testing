package stepdefinitions_doctor_appointment_system;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import driver.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages_doctor_appointment_system.DoctorsListPage;
import pages_doctor_appointment_system.Find_DoctorsPage;
import pages_doctor_appointment_system.LoginPage;

public class StepDefinitionClass_Doctor_Appointment_System {
	WebDriver driver=Driver.getDriver();
	LoginPage loginPage;
	Find_DoctorsPage find_DoctorsPage=new Find_DoctorsPage(driver);
	DoctorsListPage doctorsListPage=new DoctorsListPage(driver);
@Given("the user has opened the browser and navigated to the Apollo247 website")
public void the_user_has_opened_the_browser_and_navigated_to_the_apollo247_website() {
    // Write code here that turns the phrase above into concrete actions
	Driver.init();
    driver.get("https://www.apollo247.com/");
}
@Given("the user is logged in to the website")
public void the_user_is_logged_in_to_the_website() throws InterruptedException {
    // Write code here that turns the phrase above into concrete actions
	    loginPage = new LoginPage(driver);
	    loginPage.closePopup();
	    loginPage.clickLogin();
	    loginPage.enterMob("8697615296");
	    loginPage.clickContinue();
	    Thread.sleep(20000);
	    loginPage.clickVerify();
	    
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



}
