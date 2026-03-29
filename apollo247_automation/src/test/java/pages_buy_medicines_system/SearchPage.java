package pages_buy_medicines_system;

import java.time.Duration;
// import java.util.List;

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

    @FindBy(xpath = "//span[contains(text(),'View Cart')] | //a[contains(@href,'cart')]")
    WebElement viewCartBtn;

    // Apply category filter to refine search results
    public void clickCategoryFilter() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement filter = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//label[contains(text(),'Pain Relief')]")
            )
        );

        filter.click();
    }

    // Select first product from search results and navigate to product page
    public void clickOnProduct() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//div[contains(@class,'ProductCard')]")
        ));

        By firstProduct = By.xpath("(//div[contains(@class,'ProductCard')]//a)[1]");

        WebElement product = wait.until(
            ExpectedConditions.elementToBeClickable(firstProduct)
        );

        // Scroll to avoid header overlap issues
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block:'center'});", product
        );

        // Use JS click to avoid click interception
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].click();", product
        );

        wait.until(ExpectedConditions.urlContains("medicine"));
    }

    // Click Add to Cart (handles both product page and listing page)
//    public void clickOnAddToCart() {
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//
//        try {
//            // CASE 1: Product detail page
//            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath("//button[.//span[contains(text(),'Add to Cart')] or contains(.,'Add to Cart')]")
//            ));
//
//            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
//
//        } catch (Exception e) {
//
//            // CASE 2: Listing page fallback
//            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath("(//button[contains(.,'Add to Cart')])[1]")
//            ));
//
//            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
//        }
//    }
    
//
    
    public void clickOnAddToCart() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        try {
            // ✅ STEP 1: Wait for page load properly
            wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//body")
            ));

            // ✅ STEP 2: Wait until Add to Cart is PRESENT (NOT clickable yet)
            WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//span[text()='Add to Cart']")
            ));

            // ✅ STEP 3: Scroll to center (VERY IMPORTANT)
            ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", btn
            );

            Thread.sleep(1000); // small stabilization

            // ✅ STEP 4: Use JS click (bypass overlay issues)
            ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", btn
            );

        } catch (Exception e) {

            System.out.println("Primary Add to Cart failed, trying fallback...");

            // ✅ FALLBACK (listing page)
            WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("(//button[contains(.,'Add to Cart')])[1]")
            ));

            ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", btn
            );
        }
    }
    // Navigate to cart page after adding product
    public void clickOnViewCart() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

        By viewCartLocator = By.xpath(
            "//span[contains(text(),'View Cart')] | //a[contains(@href,'/cart')]"
        );

        WebElement viewCart = wait.until(
            ExpectedConditions.visibilityOfElementLocated(viewCartLocator)
        );

        wait.until(ExpectedConditions.elementToBeClickable(viewCart));

        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].click();", viewCart
        );

        wait.until(ExpectedConditions.urlContains("cart"));
    }
}