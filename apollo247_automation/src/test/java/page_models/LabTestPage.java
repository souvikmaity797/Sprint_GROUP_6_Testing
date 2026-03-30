package page_models;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LabTestPage {

    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public LabTestPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //Search box locator
    @FindBy(xpath = "//input[@placeholder='Search for lab tests']")
    WebElement searchBox;
    
    
    //Search result
//    @FindBy(xpath="//*[contains(@class, \"QX\")]/*[1]")
//    List<WebElement> resultlist;
//    
    
    // add button on search result
  @FindBy(xpath="(//span[contains(text(),'Add')])[1]")
public
  WebElement addButton;
    
@FindBy(xpath="//li[@class='SearchResult_noResultsFound__srSdT'] ")
  WebElement invalidSearchErr;
  
  
    //Cart Button
    @FindBy(xpath="//span[text()='Go To Cart']")
    WebElement go_to_cart;
    
 
    
    //Cart price
    @FindBy(xpath=" //div[contains(@class,'GoToCartBannerNew_header')]//p[contains(text(),'₹')]")
    WebElement cartprice;
    
    //view details button

    @FindBy(xpath=" //span[contains(text(),'View Details')]")
    WebElement viewcart;
    
    
    //removefrom cart
   	@FindBy(xpath=" (//span[contains(text(),'Remove')])[3]")
     WebElement removecart;
    

    //add patient
    @FindBy(xpath="(//div[@class='cl dl DiagItemAccordionPatient_checkbox__Rz7Wh ']/input[@type='checkbox'])[1]")
    WebElement addPatient;
    
    
    
    // after add patient -> proceed button 
  @FindBy(xpath="//button[text()='Next']")
  WebElement nexttoCart;
  
  // select address 
 @FindBy(xpath="//p[text()='Maity, Chuadanga - 712617']")
 WebElement selectinvalidAddress;
 
 //quit after selecting invalid address 
@FindBy(xpath="//img[@alt='Close'] ")
WebElement quitButton;

//pop up after quit
@FindBy(xpath="//span[contains(text(),'Exit for now')]")
WebElement exitPopUp;


		 // call for doc
		@FindBy(xpath="(//img[@alt='close'])[2]")
		WebElement exitPopUp2;

  
@FindBy(xpath="//p[text()='uem, Newtown, New Town - 743502']")
WebElement selectvalidAddress;
 
 //error msg for unservicable location
@FindBy(xpath="//span[contains(text(),'Unservicable location. Please use another address.')]")
WebElement errorLocation;


    // review cart button
  @FindBy(xpath="//button[text()='Review Cart']")
  WebElement reviewCart;
  
  
  
  
  
  
    // Action: enter test name
    public void enterLabTest(String testName) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        searchBox.click();
        searchBox.clear();
        searchBox.sendKeys(testName,Keys.ENTER);
    }

    // Action: press Enter to search
    public void searchLabTest(String testName) {
        enterLabTest(testName);
      
    }
    
    // check whether result contains searched test or not
 // REMOVE this static @FindBy list:
 // @FindBy(xpath="//*[contains(@class, \"QX\")]/*[1]")
 // List<WebElement> resultlist;

 // ADD this import at the top:

 // REPLACE searchResultText() with this:
    public List<String> searchResultText() {
        List<String> list = new ArrayList<>();
       for(int i=1;i<5;i++) {
               
    	   list.add(driver.findElement(
    		        By.xpath("(//*[contains(@class, 'QX')]/*[1])[" + i + "]")
    		).getText());           
       }
        return list;
       
    }
 
 //add search item
 
//Click on View Details
public void clickViewCart() {
  viewcart.click();
}

//Click on Remove button
public void clickRemoveFromCart() {
  removecart.click();
}

//Get ONLY cart price (₹1418)
public String getCartPrice() {
  String fullText = cartprice.getText();  
  // Example: "₹1418 (2 Items)"

  return fullText.split(" ")[0];  // ₹1418
}
public String getCartItems() {
	  String fullText = cartprice.getText();  
	  // Example: "₹1418 (2 Items)"

	  return fullText.split(" ")[1];  // ₹1418
	}
 
 public void addSearchItem() {
	 addButton.click();
 }
 
 

 
 
 
 public void click_go_to_cart() {
	 go_to_cart.click();
 }

    //for add patient
    public void addPatient() {
    	WebElement checkbox = driver.findElement(By.xpath(
    		    "(//div[@class='cl dl DiagItemAccordionPatient_checkbox__Rz7Wh ']/input[@type='checkbox'])[1]"
    		));

    		JavascriptExecutor js = (JavascriptExecutor) driver;

    		// 1) Ensure it becomes checked
    		js.executeScript(
    		    "arguments[0].checked = true;" +
    		    "arguments[0].dispatchEvent(new Event('change', {bubbles: true}));" +
    		    "arguments[0].dispatchEvent(new Event('click', {bubbles: true}));",
    		    checkbox
    		);
    	
    }
    
    // proceed after selected patient
    public void clickNext() {
    	nexttoCart.click();
    }
    
    //Address selection
    public void clickSelectInvalidAddress() {
    	selectinvalidAddress.click();
    }
    
    
    public void clickQuitbutton() {
    	quitButton.click();
    	}
    
    public void clickExitPopUp() {
    	exitPopUp.click();
    	}
    
    
    
    public void clickExitPopUp2() {
    	exitPopUp2.click();
    	}
    
    //Address selection
    public void clickSelectValidAddress() {
    	selectvalidAddress.click();
    }
    
    //invalid search error msg
    public String invalidSearchErrMsg() {
    	return invalidSearchErr.getText();
    }
    
    //error msg for unservicable location
    public String getErrorMsg() {
    	
    	return errorLocation.getText();
    }
    
    
    //go to cart
    public void clickreviewCart() {
    	reviewCart.click();
    }
    
    
    
    
    // Optional: get entered text
    public String getSearchText() {
        return searchBox.getAttribute("value");
    }
}
