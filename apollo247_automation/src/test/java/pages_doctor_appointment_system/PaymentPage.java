package pages_doctor_appointment_system;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
 
 @FindBy(name = "card_number")
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
 
 public boolean isOnPaymentPage() {
	 
	    try {
	        WebElement heading = driver.findElement(
	            By.xpath("//span[text()='Payment Options']")
	        );
	        return heading.isDisplayed();
	    } catch (Exception e) {
	        return false;
	    }
	}
 
 @FindBy(id = "common_pay_btn")
 WebElement payButton;

 public boolean isPayButtonEnabled() {
     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
     wait.until(ExpectedConditions.visibilityOf(payButton));
     return payButton.isEnabled();
 }
 
 public void switchToCardFrame() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

	    WebElement frame = wait.until(ExpectedConditions
	        .presenceOfElementLocated(By.xpath("//iframe[contains(@src,'pay')]")));

	    driver.switchTo().frame(frame);
	}
}
