package pages_doctor_appointment_system;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    
    @FindBy(xpath = "//span[text()='0-5']")
    WebElement experienceFilter;
    

    @FindBy(xpath = "//div[@class='List_doctorList__H0eu4']")
    List<WebElement> firstPageDoctors;
    
    public void experienceFilterClick() {
    	experienceFilter.click();
    }
    
    By doctorNames = By.xpath("//ul[contains(@class,'Search_searchResults')]//p[contains(@class,'Search_name')]");    
    //Actions
    public void firstDoctorSelect() {
    	firstDoctor.click();
    }
    
    public List<Integer> getDoctorsExperience() {
        List<Integer> experienceList = new ArrayList<>();
        
        for (WebElement doctor : firstPageDoctors) {
            try {
                WebElement expElement = doctor.findElement(
                    By.xpath(".//div[@class='DoctorCard_doctorDetails__mqW6P']//p[contains(.,'Years')]")
                );

                String expText = expElement.getText().trim(); // multi-line text

                // Regex to get the number immediately before "Years"
                Pattern pattern = Pattern.compile("(\\d+)\\s*Years");
                Matcher matcher = pattern.matcher(expText);
                
                if (matcher.find()) {
                    int exp = Integer.parseInt(matcher.group(1));
                    experienceList.add(exp);
                } else {
                    System.out.println("No valid experience number found in text: " + expText);
                }
                
            } catch (Exception e) {
                System.out.println("Experience not found for a doctor, skipping...");
            }
        }
        
        return experienceList;
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
