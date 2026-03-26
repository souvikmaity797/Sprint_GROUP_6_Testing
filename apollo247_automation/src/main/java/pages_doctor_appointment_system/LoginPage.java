package pages_doctor_appointment_system;

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

public class LoginPage {
	WebDriver driver;
	WebDriverWait wait;
	
	public LoginPage (WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	public void closePopup() {
	    try {
	        WebElement shadowHost = wait.until(
	            ExpectedConditions.presenceOfElementLocated(By.cssSelector("ct-web-popup-imageonly"))
	        );

	        SearchContext shadowRoot = shadowHost.getShadowRoot();
	        WebElement closeBtn = shadowRoot.findElement(By.cssSelector(".close"));
	        closeBtn.click();

	    } catch (Exception e) {
	        System.out.println("Popup not present, skipping...");
	    }
	}
	
//	@FindBy(xpath = "//span[text()=\"Login\"]")
//	WebElement login;

	@FindBy(name = "mobileNumber")
	WebElement mob;
	
	@FindBy(xpath = "//button[text()=\"Verify\"]")
	WebElement verifyBtn;
	
	@FindBy(xpath = "//button[@class=\"SignIn_submitBtn__k9oGb\"]")
	WebElement continueBtn;
	
//	public void clickLogin() {
//	    wait.until(ExpectedConditions.elementToBeClickable(login));
//		login.click();
//	}
	
	public void clickLogin() {
	    try {
	        WebElement loginBtn = driver.findElement(By.xpath("//span[text()='Login']"));
	        wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
	        loginBtn.click();
	    } catch (Exception e) {
	        System.out.println("Login button not present, maybe already logged in.");
	    }
	}
	
//	public void enterMob(String num) {
//		wait.until(ExpectedConditions.visibilityOf(mob));
//		mob.sendKeys(num);
//	}
	
	public void enterMob(String num) {
	    try {
	        WebElement mob = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.name("mobileNumber"))));
	        mob.sendKeys(num);
	    } catch (Exception e) {
	        System.out.println("Mobile number field not present — skipping enterMob()");
	    }
	}
	
//	public void clickContinue() {
//		continueBtn.click();
//	}
	
	public void clickContinue() {
	    try {
	        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(
	            driver.findElement(By.xpath("//button[@class='SignIn_submitBtn__k9oGb']"))
	        ));
	        continueBtn.click();
	    } catch (Exception e) {
	        System.out.println("Continue button not present — skipping clickContinue()");
	    }
	}
	
//	public void clickVerify() {
//		verifyBtn.click();
//	}
	
	public void clickVerify() {
	    try {
	        WebElement verifyBtn = wait.until(ExpectedConditions.elementToBeClickable(
	            driver.findElement(By.xpath("//button[text()='Verify']"))
	        ));
	        verifyBtn.click();
	    } catch (Exception e) {
	        System.out.println("Verify button not present — skipping clickVerify()");
	    }
	}
	
	public boolean isLoggedIn() {
	    try {
	        // Example: check if logout button or profile icon is visible
	        WebElement profileIcon = driver.findElement(By.cssSelector(".profile-icon-class"));
	        return profileIcon.isDisplayed();
	    } catch (Exception e) {
	        return false;
	    }
	}
}