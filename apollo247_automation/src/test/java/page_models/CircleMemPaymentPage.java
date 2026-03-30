package page_models;



import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CircleMemPaymentPage {

    WebDriver driver;
    WebDriverWait wait;

    public CircleMemPaymentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
    
    // click on credit card option
    @FindBy(xpath="//li[@class=' Juspay_desktopNavBelowActive__XCyuR']")
    WebElement creditCardOption;
      

    // Name on Card
    @FindBy(xpath = "//input[contains(@placeholder,'Name')]")
    WebElement nameOnCard;

    // Card Number
    @FindBy(xpath = "//input[contains(@placeholder,'Enter Card Number')]")
    WebElement cardNumber;

    //Expire Date
    @FindBy(xpath = "//input[@name='card_exp_month']")
    WebElement expiryDate;
    
    //expiryMonth
    @FindBy(xpath = "//input[@id='card_exp_year']")
    WebElement expiryYear;

    // CVV
    @FindBy(xpath = "//input[contains(@placeholder,'CVV')]")
    WebElement cvv;

    //Pay Button
    @FindBy(xpath = "//span[contains(text(),'Pay ')]")
    WebElement payBtn;
    
//    @FindBy(xpath ="//p[contains(text(),'invalid_card_number')]")
//    WebElement errorMsg;
    
    
    //after entering the card and pay
    @FindBy(xpath ="//button[@id='frn-btn']")
    WebElement payPLN;
    
  
    @FindBy(xpath ="//h5[contains(text(),'Payment Failed')]")
    WebElement errorMsg;

    // ---------------- ACTION METHODS ----------------
    
    public void clickOnCreditCard() {
    	creditCardOption.click();
    }


    
    public void clickOnPayPLN() {
    	payPLN.click();
    }

    // Frame 2 - Card Number
    

   
    public void enterName(String name) {
       // wait.until(ExpectedConditions.visibilityOf(nameOnCard));
    	
    	driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='name_on_card_iframe']")));
        nameOnCard.clear();
        nameOnCard.sendKeys(name);
        driver.switchTo().defaultContent();
    }

    public void enterCardNumber(String number) {
       // wait.until(ExpectedConditions.visibilityOf(cardNumber));
    	driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='card_number_iframe']")));
    	 cardNumber.clear();
         cardNumber.sendKeys(number);
        driver.switchTo().defaultContent();
       
    }

    public void enterExpirymonth(String month) {
      //  wait.until(ExpectedConditions.visibilityOf(expiryDate));
    	driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='card_exp_month_iframe']")));
    	
        expiryDate.clear();
        expiryDate.sendKeys(month);
        driver.switchTo().defaultContent();
        
    }
    
    public void enterExpiryyear(String year) throws InterruptedException {
        //  wait.until(ExpectedConditions.visibilityOf(expiryDate));
    	
    	Thread.sleep(2000);
    	driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='card_exp_year_iframe']")));
    	
          expiryYear.clear();
          expiryYear.sendKeys(year);
          driver.switchTo().defaultContent();
          
      }

    public void enterCVV(String cvvCode) throws InterruptedException {
      //  wait.until(ExpectedConditions.visibilityOf(cvv));
    	Thread.sleep(2000);
    	driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='security_code_iframe']")));
        cvv.clear();
        cvv.sendKeys(cvvCode);
        driver.switchTo().defaultContent();
        
    }

    public void clickPay() {
      //  wait.until(ExpectedConditions.elementToBeClickable(payBtn));
        payBtn.click();
    }
    
    public String errorMsg() {
    //	wait.until(ExpectedConditions.visibilityOf(errorMsg));
    	return errorMsg.getText();
    }

    // Combined method 
    public void makePayment(String name, String card, String mon,String year, String cvvCode) throws InterruptedException {
        enterName(name);
        enterCardNumber(card);
        enterExpirymonth(mon);
        enterExpiryyear(year);
        enterCVV(cvvCode);
        clickPay();
        
    }
}