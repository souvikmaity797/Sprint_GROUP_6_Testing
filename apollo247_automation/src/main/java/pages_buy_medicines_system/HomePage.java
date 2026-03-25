package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	WebDriver driver;
	WebDriverWait wait;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	public void closePopup() {
	    try {
	        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));

	        WebElement shadowHost = shortWait.until(
	            ExpectedConditions.presenceOfElementLocated(By.cssSelector("ct-web-popup-imageonly"))
	        );

	        SearchContext shadowRoot = shadowHost.getShadowRoot();

	        WebElement closeBtn = shadowRoot.findElement(By.cssSelector(".close"));

	        closeBtn.click();

	        System.out.println("Popup closed");

	    } catch (Exception e) {
	        System.out.println("Popup not present");
	    }
	}
	
	@FindBy(xpath = "//a[text() = \"Buy Medicines\"]")
	WebElement buyMedBtn;

	@FindBy(xpath = "//div[@data-placeholder=\"Search Medicines\"]")
	WebElement searchBox;
	
	public void clickBuyMedicines() {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	    WebElement btn = wait.until(
	        ExpectedConditions.elementToBeClickable(
	            By.xpath("//a[contains(text(),'Buy Medicines')]")
	        )
	    );

	    btn.click();

	    // ✅ WAIT FOR PAGE LOAD PROPERLY
	    wait.until(ExpectedConditions.visibilityOfElementLocated(
	        By.xpath("//div[@data-placeholder='Search Medicines']")
	    ));
	}
	
	public void searchMedicine(String medicine) {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	    // ✅ Step 1: Wait for search box container
	    WebElement searchBox = wait.until(
	        ExpectedConditions.elementToBeClickable(
	            By.xpath("//div[@data-placeholder='Search Medicines']")
	        )
	    );

	    searchBox.click();

	    // ✅ Step 2: Wait for actual input field (after click)
	    WebElement input = wait.until(
	        ExpectedConditions.presenceOfElementLocated(
	            By.xpath("//input[@placeholder='Search Medicines']")
	        )
	    );

	    input.sendKeys(medicine);

	    input.sendKeys(Keys.ENTER);
	}
	
}
