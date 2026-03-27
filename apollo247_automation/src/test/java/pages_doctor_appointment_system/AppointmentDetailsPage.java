package pages_doctor_appointment_system;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AppointmentDetailsPage {

    WebDriver driver;

    // Constructor
    public AppointmentDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ----------------- Locators -----------------
    @FindBy(xpath = "//span[text()='Change']")
    WebElement change;

    @FindBy(id = "phone-number")
    WebElement phoneNumber;

    @FindBy(xpath = "(//div[@class='CheckoutPatientSelectionDialog_radioBtn__U8sBs'])[2]")
    WebElement selectPatient;

    @FindBy(xpath = "//span[text()='Proceed']")
    WebElement proceedButton;

    @FindBy(xpath = "//span[text()='Pay & Confirm']")
    WebElement confirmAndPayButton;

    By appointmentHeading = By.xpath("//p[text()='Appointment Details']");

    // ----------------- Wait Helper -----------------
    private WebDriverWait getWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ----------------- Actions -----------------
    public boolean isOnAppointmentDetailsPage() {
        try {
            return getWait().until(ExpectedConditions.visibilityOfElementLocated(appointmentHeading))
                            .isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void changeClick() {
        getWait().until(ExpectedConditions.elementToBeClickable(change)).click();
    }

    public void phoneNumberClick() {
        getWait().until(ExpectedConditions.elementToBeClickable(phoneNumber)).click();
    }

    public void enterPhoneNumber(String number) {
        getWait().until(ExpectedConditions.visibilityOf(phoneNumber)).sendKeys(number);
    }

    public void deletePhoneNumber() {
        WebElement phone = getWait().until(ExpectedConditions.elementToBeClickable(phoneNumber));
        phone.click();
        phone.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        phone.sendKeys(Keys.BACK_SPACE);
    }

    public void selectAnotherPatient() {
        getWait().until(ExpectedConditions.elementToBeClickable(selectPatient)).click();
    }

    public void proceedClick() {
        getWait().until(ExpectedConditions.elementToBeClickable(proceedButton)).click();
    }

    public void confirmAndPayButtonClick() {
        getWait().until(ExpectedConditions.elementToBeClickable(confirmAndPayButton)).click();
    }
}