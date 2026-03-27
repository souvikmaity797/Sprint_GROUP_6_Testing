package pages_doctor_appointment_system;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DoctorsListPage {
    WebDriver driver;

    // Constructor
    public DoctorsListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Dynamic locators
    private By dentistFilter = By.xpath("//span[text()='Dentist']");
    private By clearAllButton = By.xpath("//span[text()='Clear All']");
    private By feesFilter = By.xpath("//span[text()='100-500']");
    private By firstDoctor = By.xpath("(//div[@class='DoctorCard_doctorInfo__i_vt5'])[1]");
    private By experienceFilter = By.xpath("//span[text()='0-5']");
    private By doctorNames = By.xpath("//ul[contains(@class,'Search_searchResults')]//p[contains(@class,'Search_name')]");
    private By firstPageDoctors = By.xpath("//div[@class='List_doctorList__H0eu4']");

    private WebDriverWait getWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ---------- Actions ----------

    public void experienceFilterClick() {
        try {
            WebElement expFilter = getWait().until(ExpectedConditions.elementToBeClickable(experienceFilter));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", expFilter);
            getWait().until(ExpectedConditions.elementToBeClickable(expFilter)).click();
        } catch (Exception e) {
            System.out.println("Experience filter click failed: " + e.getMessage());
        }
    }

    public void firstDoctorSelect() {
        try {
            WebElement doctor = getWait().until(ExpectedConditions.elementToBeClickable(firstDoctor));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", doctor);
            doctor.click();
        } catch (Exception e) {
            System.out.println("First doctor select failed: " + e.getMessage());
        }
    }

    public List<Integer> getDoctorsExperience() {
        List<Integer> experienceList = new ArrayList<>();

        List<WebElement> doctors = getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(firstPageDoctors));

        for (WebElement doctor : doctors) {
            try {
                WebElement expElement = doctor.findElement(
                        By.xpath(".//div[@class='DoctorCard_doctorDetails__mqW6P']//p[contains(.,'Years')]"));
                String expText = expElement.getText().trim();

                Pattern pattern = Pattern.compile("(\\d+)\\s*Years");
                Matcher matcher = pattern.matcher(expText);

                if (matcher.find()) {
                    experienceList.add(Integer.parseInt(matcher.group(1)));
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
        List<WebElement> doctors = getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(doctorNames));
        return doctors.size() > 0;
    }

    public boolean isDoctorPresent(String doctorName) {
        List<WebElement> doctors = getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(doctorNames));
        for (WebElement doc : doctors) {
            if (doc.getText().toLowerCase().contains(doctorName.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    // Optional filters if needed
    public void applyDentistFilter() {
        WebElement filter = getWait().until(ExpectedConditions.elementToBeClickable(dentistFilter));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", filter);
        filter.click();
    }

    public void applyFeesFilter() {
        WebElement filter = getWait().until(ExpectedConditions.elementToBeClickable(feesFilter));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", filter);
        filter.click();
    }

    public void clearAllFilters() {
        WebElement clearBtn = getWait().until(ExpectedConditions.elementToBeClickable(clearAllButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", clearBtn);
        clearBtn.click();
    }
}
