package pages_buy_medicines_system;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage {
	WebDriver driver;
	
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy(xpath = "//div[contains(text(),'Pain')]")
	WebElement cateFilterChkbox;
	
//	@FindBy(xpath = "(//div[contains(@class,'ProductCard')])[1]")
//	WebElement product;
	
//	@FindBy(xpath = "//span[text()='Add to Cart']")
//	WebElement addToCartBtn;
	
	@FindBy(xpath = "//span[contains(text(),'View Cart')] | //a[contains(@href,'cart')]")
	WebElement viewCartBtn;
	
	public void clickChkBox() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	    WebElement filter = wait.until(
	        ExpectedConditions.elementToBeClickable(
	            By.xpath("//label[contains(text(),'Pain Relief')]")
	        )
	    );

	    filter.click();
	}
	
	public void clickOnProduct() {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	    By firstProduct = By.xpath("(//div[contains(@class,'ProductCard')]//a)[1]");

	    WebElement product = wait.until(
	        ExpectedConditions.elementToBeClickable(firstProduct)
	    );

	    ((JavascriptExecutor) driver).executeScript(
	        "arguments[0].scrollIntoView({block:'center'});", product
	    );

	    try { Thread.sleep(1000); } catch (Exception e) {}

	    // 🔥 FORCE CLICK
	    ((JavascriptExecutor) driver).executeScript(
	        "arguments[0].click();", product
	    );

	    // 🔥 WAIT FOR PAGE CHANGE (CRITICAL)
	    wait.until(ExpectedConditions.or(
	        ExpectedConditions.urlContains("medicine"),
	        ExpectedConditions.presenceOfElementLocated(
	            By.xpath("//button[contains(.,'Add to Cart')]")
	        )
	    ));
	}
	
	public void clickOnAddToCart() {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	    // ✅ CASE 1: Product page
	    List<WebElement> addToCart = driver.findElements(
	        By.xpath("//button[contains(.,'Add to Cart')]")
	    );

	    if (addToCart.size() > 0) {

	        WebElement btn = wait.until(
	            ExpectedConditions.elementToBeClickable(addToCart.get(0))
	        );

	        ((JavascriptExecutor) driver).executeScript(
	            "arguments[0].scrollIntoView({block:'center'});", btn
	        );

	        ((JavascriptExecutor) driver).executeScript(
	            "arguments[0].click();", btn
	        );

	        System.out.println("✅ Add to Cart clicked (Product Page)");

	    } else {

	        // ✅ CASE 2: Listing page fallback
	        By addBtn = By.xpath("(//div[contains(@class,'ProductCard')]//button)[1]");

	        WebElement btn = wait.until(
	            ExpectedConditions.elementToBeClickable(addBtn)
	        );

	        ((JavascriptExecutor) driver).executeScript(
	            "arguments[0].click();", btn
	        );

	        System.out.println("⚠️ ADD clicked (Listing Page)");
	    }
	}
	
	public void clickOnViewCart() {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	    // ✅ wait for View Cart to appear AFTER add to cart
	    By viewCartLocator = By.xpath(
	        "//span[contains(text(),'View Cart')] | //a[contains(@href,'cart')]"
	    );

	    WebElement viewCart = wait.until(
	        ExpectedConditions.visibilityOfElementLocated(viewCartLocator)
	    );

	    // scroll to avoid overlay issue
	    ((JavascriptExecutor) driver).executeScript(
	        "arguments[0].scrollIntoView({block:'center'});", viewCart
	    );

	    try { Thread.sleep(1000); } catch (Exception e) {}

	    // safe click
	    try {
	        viewCart.click();
	    } catch (Exception e) {
	        ((JavascriptExecutor) driver).executeScript(
	            "arguments[0].click();", viewCart
	        );
	    }

	    // ✅ OPTIONAL: confirm navigation
	    wait.until(ExpectedConditions.urlContains("cart"));
	}
	
}
