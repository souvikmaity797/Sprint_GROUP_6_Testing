package pages_doctor_appointment_system;

import java.time.Duration;

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
    
    By noResultMsg = By.xpath("//h2[text()='No result found']");
    
    //Actions
    	public void findDoctorButtonClick() {
    		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
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
    
    public String getNoResultText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        return wait.until(ExpectedConditions.visibilityOfElementLocated(noResultMsg)).getText();
    }
}
