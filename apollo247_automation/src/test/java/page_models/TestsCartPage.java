package page_models;



import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestsCartPage {

    WebDriver driver;
    WebDriverWait wait;

    public TestsCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    
    
  @FindBy(xpath="(//span[contains(text(),'60% off')])[1]")
		  WebElement discount;


  
  @FindBy(xpath="(//span[contains(text(),'Lipid Profile')])[1]")
  WebElement cartcontent;

  @FindBy(xpath="//span[contains(text(),'(₹')]")
  WebElement MRP;
  
    // Coupon Input Box
    @FindBy(xpath = "//input[@placeholder='Enter Coupon Code']")
    WebElement couponInput;

    //  Apply Coupon Button
    @FindBy(xpath = "//span[contains(text(),'Offers and Coupons')]")
    WebElement applyCouponBtn;

    //Get the span text
    @FindBy(xpath = "//div[contains(@class,'Coupon_pinErrorMsg')]")
    WebElement couponErrorMsg;
    
    
    // Remove item from cart
    @FindBy(xpath = "//img[contains(@src,'ic_clearinput.svg')]")
    WebElement removeFromCartBtn;

    // Total Payable Amount
    @FindBy(xpath = "//span[contains(text(),\"Proceed to Pay\")]")
    WebElement toPayAmount;
    
 
    @FindBy(xpath = " //span[contains(text(),'Cancel')]")
    WebElement couponcancelBtn;
    
    
    @FindBy(xpath = " (//div[contains(text(),'1')])[1]")
    WebElement iteminCart;
    
    
    

    // ---------------- ACTION METHODS ----------------

    
    	public String getMRP() {
    		return MRP.getText();
    	}

    	public String getDiscount() {
    		return discount.getText();
    	}
    
    
    	public String getCartContent() {
    		return cartcontent.getText();
    	}
    
    
    // Enter coupon
    public void enterCoupon(String coupon) {
        wait.until(ExpectedConditions.visibilityOf(couponInput));
        couponInput.clear();
        couponInput.sendKeys(coupon,Keys.ENTER);
        
        
        
    }
    
    public String getCouponErrMsg() {
    	return couponErrorMsg.getText();
    }

    // Apply coupon
    public void applyCoupon() {
        wait.until(ExpectedConditions.elementToBeClickable(applyCouponBtn));
        applyCouponBtn.click();
    }

  

    // Remove item from cart
    public void removeItemFromCart() {
        wait.until(ExpectedConditions.elementToBeClickable(removeFromCartBtn));
        removeFromCartBtn.click();
    }
    
    	
    public void clickCancel() {
    	couponcancelBtn.click();
    }

    	public String itemOnCart(){
    	String fullText = iteminCart.getText();  
    	// "1 Item | 30 tests included | ₹409"

    	// Extract only item count
    	String itemCount = fullText.split(" ")[0];  // 1

    	return itemCount;
    
    	}
    // Get To Pay amount
    public String getToPayAmount() {
        wait.until(ExpectedConditions.visibilityOf(toPayAmount));
        String text= toPayAmount.getText();
        return text.replaceAll("[^0-9.]", "");

    }
}