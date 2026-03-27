package pages_doctor_appointment_system;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Find_DoctorsPage {

    private WebDriver driver;

    public Find_DoctorsPage(WebDriver driver) {
        this.driver = driver;
    }

    // ----------------- Locators (By) -----------------
    private By findDoctorButton = By.xpath("//a[@href='/specialties']");
    private By searchBar = By.xpath("//input[@placeholder='Search Doctors, Specialities, Conditions etc.']");
    private By viewDoctorsButton = By.xpath("//div[text()='View Doctors']");
    private By drRamna = By.xpath("//a[contains(@href,'dr-ramna-banerjee')]");
    private By doctorResults = By.xpath("//ul[contains(@class,'Search_searchResults__au0e4')]/li");
    private By noResultMsg = By.xpath("//h2[text()='No result found']");

    // The overlay that blocks clicks
    private By topBarOverlay = By.cssSelector("div.tb");

    // ----------------- Wait Helper -----------------
    private WebDriverWait getWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // ----------------- Core Safe Actions -----------------
    private void safeClick(By locator) {
        try {
            waitForOverlayToDisappear();

            WebElement element = getWait().until(ExpectedConditions.elementToBeClickable(locator));
            scrollToCenter(element);

            try {
                element.click(); // normal click
            } catch (Exception e) {
                // JS fallback when header intercepts
                jsClick(element);
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to click element: " + locator, e);
        }
    }

    private void safeSendKeys(By locator, String text) {
        try {
            WebElement element = getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
            scrollToCenter(element);
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send keys to element: " + locator, e);
        }
    }

    private void waitForOverlayToDisappear() {
        try {
            getWait().until(ExpectedConditions.invisibilityOfElementLocated(topBarOverlay));
        } catch (Exception ignored) {}
    }

    private void scrollToCenter(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block:'center', inline:'nearest'});",
            element
        );
    }

    private void jsClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    // ----------------- Actions -----------------
    public void findDoctorButtonClick() {
        safeClick(findDoctorButton);
    }

    public void searchBarClick() {
        safeClick(searchBar);
    }

    public void enterDoctorName(String name) {
        safeSendKeys(searchBar, name);
    }

    public void enterSpecialty(String specialty) {
        safeSendKeys(searchBar, specialty);
    }

    public void viewDoctorsButtonClick() {
        safeClick(viewDoctorsButton);
    }

    public void selectDoctorRamna() {
        safeClick(drRamna);
    }

    public String getNoResultText() {
        WebElement msg = getWait().until(ExpectedConditions.visibilityOfElementLocated(noResultMsg));
        return msg.getText();
    }

    public boolean areDoctorsDisplayed() {
        try {
            List<WebElement> results =
                getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(doctorResults));
            return results.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }
}