package pages_doctor_appointment_system;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DoctorProfilePage {
	WebDriver driver;
	 // Constructor
   public DoctorProfilePage(WebDriver driver) {
       this.driver = driver;
       PageFactory.initElements(driver, this); // Initializes elements
   }
   
   //Locators
   @FindBy(id = "headlessui-tabs-tab-:r0:")
   WebElement onlineConsultButton;
   
   @FindBy(xpath = "(//span[text()='Schedule Appointment'])[1]")
   WebElement scheduleAppointment;
   
   //Actions
   public void onlineConsultButtonClick() {
	   onlineConsultButton.click();
   }
   
   public void scheduleAppointmentClick() {
	   scheduleAppointment.click();
   }
}
