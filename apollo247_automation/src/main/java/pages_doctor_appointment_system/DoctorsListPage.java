package pages_doctor_appointment_system;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DoctorsListPage {
	WebDriver driver;
	 // Constructor
    public DoctorsListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Initializes elements
    }
    @FindBy(xpath = "//span[text()='Dentist']")
    WebElement dentistFilter;
    
    @FindBy(xpath = "//span[text()='Clear All']")
    WebElement clearAllButton;
    
    @FindBy(xpath = "//span[text()='100-500']")
    WebElement feesFilter;
    
    @FindBy(xpath = "(//div[@class='DoctorCard_doctorInfo__i_vt5'])[1]")
    WebElement firstDoctor;
    
    By doctorNames = By.xpath("//ul[contains(@class,'Search_searchResults')]//p[contains(@class,'Search_name')]");    
    //Actions
    public void firstDoctorSelect() {
    	firstDoctor.click();
    }
    
    public boolean isDoctorListDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        List<WebElement> doctors = wait.until(
            ExpectedConditions.visibilityOfAllElementsLocatedBy(doctorNames)
        );

        return doctors.size() > 0;
    }
    
    public boolean isDoctorPresent(String doctorName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        List<WebElement> doctors = wait.until(
            ExpectedConditions.visibilityOfAllElementsLocatedBy(doctorNames)
        );

        for (WebElement doc : doctors) {
            if (doc.getText().toLowerCase().contains(doctorName.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
