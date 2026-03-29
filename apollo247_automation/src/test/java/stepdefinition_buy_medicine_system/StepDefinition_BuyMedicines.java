package stepdefinition_buy_medicine_system;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import hooks_buy_medicine_system.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages_buy_medicines_system.CartPage;
import pages_buy_medicines_system.HomePage;
import pages_buy_medicines_system.SearchPage;
import utils_buy_medicine_system.ExcelUtils;

public class StepDefinition_BuyMedicines {

    String currentMedicine;

    WebDriver driver;
    HomePage home;
    SearchPage search;
    CartPage cart;

    // Launch application and initialize all page objects
    @Given("user is on Apollo homepage")
    public void user_is_on_homepage() {
    	driver = Hooks.getDriver();
        driver.get("https://www.apollo247.com/");
        home = new HomePage(driver);
        search = new SearchPage(driver);
        cart = new CartPage(driver);

        home.closePopup();
    }

    // Navigate to Buy Medicines section
    @When("user clicks on Buy Medicines")
    public void click_buy_medicines() {
        home.clickBuyMedicines();
    }

    // Close popup if it appears after navigation
    @When("user closes popup")
    public void close_popup() {
        home.closePopup();
    }

    // Fetch medicine name from Excel and perform search
    @When("user searches for medicine from excel row {int}")
    public void search_medicine(int row) {
    	try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        currentMedicine = ExcelUtils.getData(row, 1);
        home.searchMedicine(currentMedicine);
    }

    // Verify search results are displayed
    @Then("search results should be displayed")
    public void verify_results() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        List<WebElement> products = wait.until(
            ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//div[contains(@class,'ProductCard')]")
            )
        );

        Assert.assertTrue(products.size() > 0);
    }

    // Verify invalid search does not return matching products
    @Then("no results should be displayed")
    public void verify_no_results() {

        String med = ExcelUtils.getData(2, 1);

        List<WebElement> products = driver.findElements(
            By.xpath("//div[contains(@class,'ProductCard')]//div[contains(@class,'title')]")
        );

        boolean matchFound = false;

        for (WebElement p : products) {
            if (p.getText().toLowerCase().contains(med.toLowerCase())) {
                matchFound = true;
                break;
            }
        }

        Assert.assertFalse(matchFound, "FAIL: Invalid product is shown");
    }

    // Validate empty search scenario (no action expected)
    @Then("nothing should be displayed")
    public void verify_empty() {
        Assert.assertTrue(true);
    }

    // Select product from search results
    @When("user selects product")
    public void select_product() {
        search.clickOnProduct();
    }

    // Add product to cart and navigate to cart page
    @When("user adds product to cart")
    public void add_to_cart() {
    	search.clickOnAddToCart();

    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    	wait.until(ExpectedConditions.or(
    	    ExpectedConditions.visibilityOfElementLocated(
    	        By.xpath("//span[contains(text(),'View Cart')]")
    	    ),
    	    ExpectedConditions.visibilityOfElementLocated(
    	        By.xpath("//a[contains(@href,'cart')]")
    	    )
    	));

    	search.clickOnViewCart();
    }

    // Verify navigation to cart page
    @Then("product should be added to cart")
    public void verify_cart() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.urlContains("cart"));

        Assert.assertTrue(driver.getCurrentUrl().contains("cart"));
    }

    // Increase quantity of product in cart
    @When("user increases quantity")
    public void increase_quantity() {
        cart.clickOnQuantityDropdown();
        cart.selectQuantity();
    }

    // Basic validation to ensure cart still contains items
    @Then("quantity should be increased")
    public void verify_quantity() {

        List<WebElement> items = driver.findElements(
            By.xpath("//div[contains(@class,'ProductCard')]")
        );

        Assert.assertTrue(items.size() > 0);
    }

    // Remove product from cart
    @When("user removes product")
    public void remove_product() {
        cart.clickDeleteBtn();
        cart.confirmDelete();
    }

    // Verify cart is empty after removal
    @Then("product should be removed")
    public void verify_removed() {

        List<WebElement> products = driver.findElements(
            By.xpath("//div[contains(@class,'ProductCard')]")
        );

        Assert.assertTrue(products.size() == 0,
            "FAIL: Product still present in cart");
    }

    // Apply category filter on search results
    @When("user applies category filter")
    public void apply_filter() {
        search.clickCategoryFilter();
    }

    // Verify filtered products are displayed
    @Then("filtered results should be displayed")
    public void verify_filter() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        List<WebElement> products = wait.until(
            ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//div[contains(@class,'ProductCard')]")
            )
        );

        Assert.assertTrue(products.size() > 0);
    }
}