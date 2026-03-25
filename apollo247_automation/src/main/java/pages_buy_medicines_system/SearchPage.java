package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
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
	
	@FindBy(xpath = "(//div[contains(@class,'ProductCard')])[1]")
	WebElement product;
	
	@FindBy(xpath = "//button[contains(text(),'Add')]")
	WebElement addToCartBtn;
	
	@FindBy(xpath = "//span[contains(text(),'View Cart')]")
	WebElement viewCartBtn;
	
	public void clickChkBox() {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	    WebElement filter = wait.until(
	        ExpectedConditions.elementToBeClickable(
	            By.xpath("//span[contains(text(),'Pain Relief')]")
	        )
	    );

	    filter.click();
	}
	
	public void clickOnProduct() {
	    new WebDriverWait(driver, Duration.ofSeconds(15))
	            .until(ExpectedConditions.elementToBeClickable(product))
	            .click();
	}
	
	public void clickOnAddToCart() {
		addToCartBtn.click();
	}
	
	public void clickOnViewCart() {
	    new WebDriverWait(driver, Duration.ofSeconds(15))
	            .until(ExpectedConditions.elementToBeClickable(viewCartBtn))
	            .click();
	}
	
}
