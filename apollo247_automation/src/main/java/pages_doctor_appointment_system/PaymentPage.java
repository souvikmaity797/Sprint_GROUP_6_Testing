package pages_doctor_appointment_system;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage {
	WebDriver driver;
	 // Constructor
 public PaymentPage(WebDriver driver) {
     this.driver = driver;
     PageFactory.initElements(driver, this); // Initializes elements
 }
 
 //Locators
 @FindBy(id="name_on_card")
 WebElement nameOnCard;
 
 @FindBy(id="card_number")
 WebElement cardNumber;
 
 @FindBy(xpath = "//div[text()='Credit/Debit Cards']")
 WebElement paymentByCard;
 
 @FindBy(xpath = "//div[@class='NewCard_validityContainer__bjy_c']")
 WebElement expiryDate;
 
 @FindBy(id = "security_code")
 WebElement cvv;
 
 //Actions
 public void paymentByCardClick() {
	 paymentByCard.click();
 }
 
 public void enterNameOnCard(String name) {
	 nameOnCard.sendKeys(name);
 }
 public void enterCardNumber(String cNumber) {
	 cardNumber.sendKeys(cNumber);
 }
 public void enterExpiryDate(String date) {
	 expiryDate.sendKeys(date);
 }
 public void enterCVV(String cvvNumber) {
	 cvv.sendKeys(cvvNumber);
 }
}
