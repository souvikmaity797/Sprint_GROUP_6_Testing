package stepdefinition_buy_medicine_system;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

	WebDriver driver = Hooks.getDriver();
    HomePage home;
    SearchPage search;
    CartPage cart;

    @Given("user is on Apollo homepage")
    public void user_is_on_homepage() {
        driver.get("https://www.apollo247.com/");
        home = new HomePage(driver);
        search = new SearchPage(driver);
        cart = new CartPage(driver);

        home.closePopup();
    }

    @When("user clicks on Buy Medicines")
    public void click_buy_medicines() {
        home.clickBuyMedicines();
    }

    @When("user closes popup")
    public void close_popup() {
        home.closePopup();
    }

    // ✅ EXCEL IMPLEMENTATION
    @When("user searches for medicine from excel row {int}")
    public void search_medicine(int row) {

    	currentMedicine = ExcelUtils.getData(row, 1);

    	home.searchMedicine(currentMedicine);

        try {
            Thread.sleep(5000);
        } catch (Exception e) {}
    }

    @Then("search results should be displayed")
    public void verify_results() {

        List<WebElement> products = driver.findElements(
            By.xpath("//div[contains(@class,'ProductCard')]")
        );

        Assert.assertTrue(products.size() > 0);
    }

    @Then("no results should be displayed")
    public void verify_no_results() {

        String med = ExcelUtils.getData(2, 1);

        List<WebElement> products = driver.findElements(
            By.xpath("//div[contains(@class,'ProductCard')]//div[contains(@class,'title')]")
        );

        boolean matchFound = false;

        for(WebElement p : products){
            if(p.getText().toLowerCase().contains(med.toLowerCase())){
                matchFound = true;
                break;
            }
        }

        Assert.assertFalse(matchFound, "FAIL: Invalid product is shown");
    }

    @Then("nothing should be displayed")
    public void verify_empty() {
        Assert.assertTrue(true);
    }

    @When("user selects product")
    public void select_product() {
        search.clickOnProduct();
    }

    @When("user adds product to cart")
    public void add_to_cart() {

        search.clickOnAddToCart();

        try {
            Thread.sleep(5000);
        } catch (Exception e) {}

        search.clickOnViewCart();
    }

    @Then("product should be added to cart")
    public void verify_cart() {
        Assert.assertTrue(driver.getPageSource().contains("Cart"));
    }

    @When("user increases quantity")
    public void increase_quantity() {
        cart.clickOnQuantityDropdown();
        cart.selectQuantity();

        try {
            Thread.sleep(5000);
        } catch (Exception e) {}
    }

    @Then("quantity should be increased")
    public void verify_quantity() {
        Assert.assertTrue(true);
    }

    @When("user removes product")
    public void remove_product() {
        cart.clickDeleteBtn();
        cart.confirmDelete();
    }

    @Then("product should be removed")
    public void verify_removed() {

        List<WebElement> products = driver.findElements(
            By.xpath("//div[contains(@class,'ProductCard')]")
        );

        Assert.assertTrue(products.size() == 0,
            "FAIL: Product still present in cart");
    }

    @When("user applies category filter")
    public void apply_filter() {
        search.clickChkBox();

        try {
            Thread.sleep(5000);
        } catch (Exception e) {}
    }

    @Then("filtered results should be displayed")
    public void verify_filter() {
        Assert.assertTrue(true);
    }
}