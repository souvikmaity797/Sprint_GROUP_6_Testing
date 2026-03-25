package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[@class=\"icon-ic-down_arrow  MedicineProductCard_arrowIcon__RPJC1\"]")
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
	
	public void clickOnQuantityDropdown() {
		quantityDropdownBtn.click();
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
	
	public void skip() {
		skipBtn.click();
	}
}
