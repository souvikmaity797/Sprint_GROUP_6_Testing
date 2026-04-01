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
    WebDriverWait wait;

    // -------- Loader locator--------
    private By loaderOverlay = By.cssSelector("div[class*='corporateLoading']");

    // Constructor
    public AppointmentDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
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

    // ----------------- Core Synchronization -----------------

    private void waitForLoaderToDisappear() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(loaderOverlay));
        } catch (Exception ignored) {}
    }

    private void safeClick(WebElement element) {
        waitForLoaderToDisappear();
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    private void safeType(WebElement element, String text) {
        waitForLoaderToDisappear();
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
    }

    // ----------------- Actions -----------------

    public boolean isOnAppointmentDetailsPage() {
        try {
            waitForLoaderToDisappear();
            return wait.until(ExpectedConditions.visibilityOfElementLocated(appointmentHeading))
                       .isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void changeClick() {
        safeClick(change);
    }

    public void phoneNumberClick() {
        safeClick(phoneNumber);
    }

    public void enterPhoneNumber(String number) {
        safeType(phoneNumber, number);
    }

    public void deletePhoneNumber() {
        waitForLoaderToDisappear();
        WebElement phone = wait.until(ExpectedConditions.elementToBeClickable(phoneNumber));
        phone.click();
        phone.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        phone.sendKeys(Keys.BACK_SPACE);
    }

    public void selectAnotherPatient() {
        safeClick(selectPatient);
    }

    public void proceedClick() {
        safeClick(proceedButton);
    }

    public void confirmAndPayButtonClick() {
        safeClick(confirmAndPayButton);
    }
}