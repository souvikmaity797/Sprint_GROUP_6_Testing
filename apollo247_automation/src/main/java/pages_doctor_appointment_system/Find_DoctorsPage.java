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

public class Find_DoctorsPage {

	WebDriver driver;

    // Constructor
    public Find_DoctorsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Initializes elements
    }
    @FindBy(xpath = "//a[@href='/specialties']")
    WebElement findDoctorButton;
    
    @FindBy(xpath = "//input[@placeholder='Search Doctors, Specialities, Conditions etc.']")
    WebElement searchBar;
    
    @FindBy(xpath = "//div[text()='View Doctors']")
    WebElement viewDoctorsButton;
    
    @FindBy(xpath = "//a[@href='/doctors/dr-ramna-banerjee-laparoscopic-and-robotic-surgeon-5719839d-4e83-4427-914e-3e554e45054c']")
    WebElement drRamna;
    
    By noResultMsg = By.xpath("//h2[text()='No result found']");
    
    //Actions
    public void selectDoctorRamna() {
    	drRamna.click();
    }
    
    	public void findDoctorButtonClick() {
    		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    	    wait.until(ExpectedConditions.elementToBeClickable(findDoctorButton)).click();
    	}

    public void searchBarClick() {
    	searchBar.click();
    }
    
    public void enterDoctorName(String name) {
    	searchBar.sendKeys(name);
    }
    public void enterSpecialty(String specialty) {
    	searchBar.sendKeys(specialty);
    }
    public void viewDoctorsButtonClick() {
    	viewDoctorsButton.click();
    }
    
//    public String getNoResultText() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        
//        return wait.until(ExpectedConditions.visibilityOfElementLocated(noResultMsg)).getText();
//    }
    
    public String getNoResultText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(noResultMsg));
            return msg.getText();
        } catch (org.openqa.selenium.StaleElementReferenceException e) {
            // Retry once if DOM refreshed
            WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(noResultMsg));
            return msg.getText();
        }
    }
    
    @FindBy(xpath = "//ul[contains(@class,'Search_searchResults__au0e4')]/li")
    List<WebElement> doctorResults;

    public boolean areDoctorsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(doctorResults));
            return doctorResults.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }
}
