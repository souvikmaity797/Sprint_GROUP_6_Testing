package pages_buy_medicines_system;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

    // Initialize WebDriver, PageFactory elements, and default wait
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public void waitForPageLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(300));

        wait.until(driver -> ((JavascriptExecutor) driver)
            .executeScript("return document.readyState")
            .equals("complete"));
    }

    // Close homepage popup using Shadow DOM (Apollo site uses shadow-root for popup)
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

    // Navigate to Buy Medicines page and ensure search box is loaded
    public void clickBuyMedicines() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(200));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));

        WebElement btn = wait.until(
            ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[contains(text(),'Buy Medicines')]")
            )
        );

        // Scroll to avoid header overlay issues during click
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);

        wait.until(ExpectedConditions.elementToBeClickable(btn));
        btn.click();

        // Wait until search box appears on medicines page
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[@data-placeholder='Search Medicines']")
        ));
    }

    // Perform search operation and wait for results to load
    public void searchMedicine(String medicine) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(200));

        WebElement searchBox = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@data-placeholder='Search Medicines']")
            )
        );
        searchBox.click();
 
        WebElement input = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//input[contains(@placeholder,'Search')]")
            )
        );

        input.clear();
        input.sendKeys(medicine);

        if (medicine.trim().isEmpty()) {
            return;
        }

        input.sendKeys(Keys.ENTER);
        waitForPageLoad();
        wait.until(ExpectedConditions.presenceOfElementLocated(
        	    By.xpath("//div[contains(@class,'ProductCard')]")
        	));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 3; i++) {
            try {
                wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[contains(@class,'ProductCard')]")
                    ),
                    ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[contains(text(),'No results')]")
                    )
                ));
                break;
            } catch (Exception e) {
                try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        }
    }   
}