package pages_doctor_appointment_system;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DoctorProfilePage {
	WebDriver driver;
	 // Constructor
   public DoctorProfilePage(WebDriver driver) {
       this.driver = driver;
       PageFactory.initElements(driver, this); // Initializes elements
   }
   
   //Locators
   By onlineConsultButton=By.xpath("//button[contains(.,'Consult Online')]");
   
   By scheduleAppointment=By.xpath("//div[@class='slots_buttons__AtQpB slots_switchProfileBookingCTA__hOBdH']/button/span[text()='Schedule Appointment']");
   
   //Actions
   public void onlineConsultButtonClick() {
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    wait.until(ExpectedConditions.elementToBeClickable(onlineConsultButton)).click();
   }
   
   public void scheduleAppointmentClick() {
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    wait.until(ExpectedConditions.elementToBeClickable(scheduleAppointment)).click();
		}
}
