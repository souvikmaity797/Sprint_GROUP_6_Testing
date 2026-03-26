package pages_buy_medicines_system;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[contains(@class,'arrowIcon')]")
	WebElement quantityDropdownBtn;
	
	@FindBy(xpath = "(//div[@class=\"MedicineProductCard_list__Wd7VW \"])[1]")
	WebElement quantity;
	
	@FindBy(xpath = "//span[contains(text(),'Apply Coupon')]")
	WebElement applyCouponBtn;
	
	@FindBy(xpath = "//input[@placeholder=\"Enter Coupon Code\"]")
	WebElement inputBoxCoupon;
	
	@FindBy(xpath = "(//span[text()=\"Apply\"])[1]")
	WebElement applyBtn;
	
	@FindBy(xpath = "//span[text()=\"Cancel\"]")
	WebElement cancelBtn;
	
	@FindBy(xpath = "//button[contains(text(),'Proceed')]")
	WebElement proceedBtn;
	
	@FindBy(xpath = "//span[text()=\"Skip Savings\"]")
	WebElement skipBtn;
	
	@FindBy(xpath = "//div[contains(@class,'deleteIcon')]")
	WebElement deleteBtn;
	
	
	public void clickOnQuantityDropdown() {

	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        WebElement btn = wait.until(
	            ExpectedConditions.elementToBeClickable(
	                By.xpath("//span[contains(@class,'arrowIcon')]")
	            )
	        );

	        btn.click();

	    } catch (Exception e) {
	        System.out.println("Quantity dropdown not found - skipping");
	    }
	}
	
	public void selectQuantity() {
		quantity.click();
	}
	
	public void applyCoupon() {
		applyCouponBtn.click();
	}
	
	public void enterCouponCode(String coupon) {
		inputBoxCoupon.sendKeys(coupon);
	}
	
	public void clickApply() {
		applyBtn.click();
	}
	
	public void exitCouponBox() {
		cancelBtn.click();
	}
	
	public void proceedToPaymentPage() {
		proceedBtn.click();
	}
	
	public void clickDeleteBtn() {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	    WebElement btn = wait.until(
	        ExpectedConditions.elementToBeClickable(
	            By.xpath("//div[contains(@class,'deleteIcon')]")
	        )
	    );

	    btn.click();
	}
	
	public void confirmDelete() {

	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        WebElement removeBtn = wait.until(
	            ExpectedConditions.elementToBeClickable(
	                By.xpath("//span[text()='Remove']")
	            )
	        );

	        removeBtn.click();

	    } catch (Exception e) {
	        System.out.println("No confirmation popup");
	    }
	}
	
	public void skip() {
		skipBtn.click();
	}
}
