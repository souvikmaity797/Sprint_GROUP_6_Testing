package pages_doctor_appointment_system;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AppointmentDetailsPage {
	WebDriver driver;
	 // Constructor
  public AppointmentDetailsPage(WebDriver driver) {
      this.driver = driver;
      PageFactory.initElements(driver, this); // Initializes elements
  }
  
  //Locators
  @FindBy(xpath = "//span[text()='Change']")
  WebElement change;
  
  @FindBy(id = "phone-number")
  WebElement phoneNumber;
  
  @FindBy(xpath = "(//div[@class=\"CheckoutPatientSelectionDialog_radioBtn__U8sBs\"])[2]")
  WebElement selectPatient;
  
  @FindBy(xpath = "//span[text()='Proceed']")
  WebElement proceedButton;
  
  @FindBy(xpath = "//span[text()='Pay & Confirm']")
  WebElement confirmAndPayButton;
  //Actions
  
  public boolean isOnAppointmentDetailsPage() {
	    try {
	        WebElement heading = driver.findElement(
	            By.xpath("//p[text()='Appointment Details']")
	        );
	        return heading.isDisplayed();
	    } catch (Exception e) {
	        return false;
	    }
	}
  public void changeClick() {
	  change.click();
  }
  
  public void phoneNumberClick() {
	  phoneNumber.click();
  }
  public void enterPhoneNumber(String number) {
	  phoneNumber.sendKeys(number);
  }
  
  public void deletePhoneNumber() {
	    phoneNumber.click();
	    phoneNumber.sendKeys(Keys.chord(Keys.CONTROL, "a"));
	    phoneNumber.sendKeys(Keys.BACK_SPACE);
	}
  public void selectAnotherPatient() {
	  selectPatient.click();
  }
  
  public void proceedClick() {
	  proceedButton.click();
  }
  public void confirmAndPayButtonClick() {
	  confirmAndPayButton.click();
  }
  
}
