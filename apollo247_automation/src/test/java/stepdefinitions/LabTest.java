package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import utils.DriverManager;
import utils.ExcelUtil;
import utils.SessionManager;

import page_models.*;

import java.util.List;
import java.util.Map;

/**
 * Step definitions – parallel-safe.
 *
 * WebDriver is obtained from DriverManager.getDriver() (ThreadLocal).
 * Login runs once per thread thanks to SessionManager.isLoggedIn().
 */
public class LabTest {

    private LoginPage            loginPage;
    private HomePage             homePage;
    private LabTestPage          labTestPage;
    private TestsCartPage        testsCartPage;
    private CircleMembershipPage circleMembershipPage;
    private CircleMemPaymentPage circleMemPaymentPage;

    private String initialCartTotal;

    private WebDriver driver() { return DriverManager.getDriver(); }

    private void initPages() {
        WebDriver d      = driver();
        loginPage            = new LoginPage(d);
        homePage             = new HomePage(d);
        labTestPage          = new LabTestPage(d);
        testsCartPage        = new TestsCartPage(d);
        circleMembershipPage = new CircleMembershipPage(d);
        circleMemPaymentPage = new CircleMemPaymentPage(d);
    }

    // =========================================================================
    // BACKGROUND – single login per thread
    // =========================================================================

    @Given("User is logged in using data from {string}")
    public void user_is_logged_in_using_data_from(String testCaseId) throws InterruptedException {
        initPages();

        if (SessionManager.isLoggedIn()) {
            System.out.println("[Thread " + Thread.currentThread().getName()
                    + "] Session already active – skipping login.");
            return;
        }

        Map<String, String> data = ExcelUtil.getLoginData();
        driver().get(data.get("URL"));
        loginPage.closePopup();
        loginPage.clickLogin();
        loginPage.enterMob(data.get("MobileNumber"));
        loginPage.clickContinue();
        loginPage.clickVerify();
        SessionManager.markLoggedIn();
    }

    // =========================================================================
    // MODULE 1 – SEARCH
    // =========================================================================

    @Given("User is on lab tests page")
    public void user_is_on_lab_tests_page() throws InterruptedException {
        Thread.sleep(5000);
        homePage.clickLabTests();
    }

    @When("User searches for lab test using data from {string}")
    public void user_searches_for_lab_test_using_data_from(String testCaseId) throws InterruptedException {
        String keyword = ExcelUtil.getSearchData(testCaseId).get("SearchKeyword");
        Thread.sleep(5000);
        labTestPage.enterLabTest(keyword);
        Thread.sleep(5000);
    }

    @When("User presses Enter")
    public void user_presses_enter() {}

    @When("User clicks on search bar")
    public void user_clicks_on_search_bar() {}

    @Then("Search results should be displayed")
    public void search_results_should_be_displayed() {
        Assert.assertFalse(labTestPage.searchResultText().isEmpty(),
                "No search results found on the page.");
    }

    @Then("Each search result should contain expected keywords from {string}")
    public void each_search_result_should_contain_expected_keywords_from(String testCaseId) {

        Map<String, String> data = ExcelUtil.getSearchData(testCaseId);

        String k1 = data.getOrDefault("ExpectedKeyword1", "").toLowerCase();
        String k2 = data.getOrDefault("ExpectedKeyword2", "").toLowerCase();
        String k3 = data.getOrDefault("ExpectedKeyword3", "").toLowerCase();
        String k4 = data.getOrDefault("ExpectedKeyword4", "").toLowerCase();

        List<String> results = labTestPage.searchResultText();
        System.out.print(results);
        boolean allMatched = true;

        if (results.isEmpty()) {
            allMatched = false;
        } else {
            int limit = Math.min(2, results.size()); // only first 3

            for (int i = 0; i < limit; i++) {
                String text = results.get(i).toLowerCase();

                boolean matched = text.contains(k1) || text.contains(k2)
                        || text.contains(k3) || text.contains(k4);

                if (!matched) {
                    System.out.println("Mismatch found: " + results.get(i));
                    allMatched = false;
                    break;
                }
            }
        }

        Assert.assertTrue(allMatched, "Some search results did not contain expected keywords.");
    }

    @Then("Add button should be functional for each result")
    public void add_button_should_be_functional_for_each_result() {

         // first element

        try {

            Assert.assertTrue(labTestPage.addButton.isDisplayed(), "Add button not visible");
            Assert.assertTrue(labTestPage.addButton.isEnabled(), "Add button not enabled");

            // Click to verify functionality
          //  labTestPage.addButton.click();

        } catch (Exception e) {
            Assert.fail("Add button is not clickable | Error: " + e.getMessage());
        }
    }

    @Then("Error message for search should be visible using data from {string}")
    public void error_message_for_search_should_be_visible_using_data_from(String testCaseId) {

        String expected = ExcelUtil.getSearchData(testCaseId).get("ExpectedError");
        String actual   = labTestPage.invalidSearchErrMsg();

        // Normalize both strings
        expected = expected.replace("’", "'").toLowerCase().trim();
        actual   = actual.replace("’", "'").toLowerCase().trim();

        Assert.assertTrue(actual.contains(expected),
                "Expected: [" + expected + "] | Actual: [" + actual + "]");
    }

    // =========================================================================
    // MODULE 2 – CART
    // =========================================================================

    @Given("User adds lab test to cart using data from {string}")
    public void user_adds_lab_test_to_cart_using_data_from(String testCaseId) throws InterruptedException {
        String testName = ExcelUtil.getCartData(testCaseId).get("TestName");
        Thread.sleep(9000);
        homePage.clickLabTests();
        
      
        
        Thread.sleep(7000);
        labTestPage.searchLabTest(testName);
    }

    @When("User clicks on Add button for the test")
    public void user_clicks_on_add_button_for_the_test() throws InterruptedException {
        Thread.sleep(10000);
        labTestPage.addSearchItem();
        Thread.sleep(9000);
        labTestPage.click_go_to_cart();
        Thread.sleep(10000);
        labTestPage.addPatient();
        Thread.sleep(9000);
        labTestPage.clickNext();
        Thread.sleep(10000);
        labTestPage.clickSelectValidAddress();
        Thread.sleep(7000);
    }

    @When("User navigates to cart page")
    public void user_navigates_to_cart_page() throws InterruptedException {
    	Thread.sleep(15000);
        labTestPage.clickreviewCart();
    	Thread.sleep(15000);

    }

    @Then("Lab test should be present in cart")
    public void lab_test_should_be_present_in_cart() {
        Assert.assertNotNull(testsCartPage.getCartContent(),
                "Cart appears empty – expected test not found.");
    }

    @Then("Cart price details should match expected values from {string}")
    public void cart_price_details_should_match_expected_values_from(String testCaseId) {
        Map<String, String> data = ExcelUtil.getCartData(testCaseId);
        Assert.assertEquals(testsCartPage.getToPayAmount(), data.get("ExpectedPrice"),
                "Price mismatch.");
        Assert.assertNotNull(testsCartPage.getMRP(), "MRP not found.");
        Assert.assertNotNull(testsCartPage.getDiscount(), "Discount not found.");
    }

    
//    @Given("User adds lab first test to cart using data from {string}")
//    public void user_adds_lab_first_test_to_cart_using_data_from(String testCaseId) throws InterruptedException {
//        String testName = ExcelUtil.getCartData(testCaseId).get("TestName");
//        Thread.sleep(10000);
//        homePage.clickLabTests();
//        
//        Thread.sleep(7000);
//        labTestPage.searchLabTest(testName);
//        Thread.sleep(7000);
//        labTestPage.addSearchItem();
//    }
    
    
    @Given("Cart is prepared with tests using data from {string}")
    public void cart_is_prepared_with_tests_using_data_from(String testCaseId) throws InterruptedException {
        String testName = ExcelUtil.getCartData(testCaseId).get("TestName");
        homePage.clickLabTests();
        Thread.sleep(7000);
        labTestPage.searchLabTest(testName);
        Thread.sleep(5000);
        labTestPage.addSearchItem();
        Thread.sleep(5000);
        labTestPage.clickViewCart();
    }

    @When("User removes first item from cart")
    public void user_removes_first_item_from_cart() throws InterruptedException {
        Thread.sleep(5000);
        labTestPage.clickRemoveFromCart();
    }

    @Then("Remaining cart details should match expected values from {string}")
    public void remaining_cart_details_should_match_expected_values_from(String testCaseId) {
        String expectedTotal = ExcelUtil.getCartData(testCaseId).get("ExpectedPrice");
        String actual        = labTestPage.getCartPrice();
        System.out.print(actual);
       // Assert.assertNotNull(actual, "Cart appears empty after item removal.");
        Assert.assertEquals(actual, expectedTotal, "Cart total mismatch.");
        Assert.assertNotNull(labTestPage.getCartItems(), "Cart item count not found.");
    }

    // =========================================================================
    // MODULE 3 – ADDRESS / BOOKING
    // =========================================================================

    @Given("User has tests in cart")
    public void user_has_tests_in_cart() throws InterruptedException {
        Thread.sleep(10000);
        homePage.clickLabTests();
        Thread.sleep(5000);
        labTestPage.click_go_to_cart();
    }

    @When("User proceeds to booking")
    public void user_proceeds_to_booking() throws InterruptedException {
    	
    	 Thread.sleep(5000);
    	labTestPage.clickExitPopUp2();
    	
        Thread.sleep(10000);
        labTestPage.addPatient();
       Thread.sleep(10000);
        labTestPage.clickNext();
    }

    @When("User enters invalid address using data from {string}")
    public void user_enters_invalid_address_using_data_from(String testCaseId) throws InterruptedException {
        String addressType = ExcelUtil.getAddressData(testCaseId).get("AddressType");
        Thread.sleep(5000);
        if ("INVALID".equalsIgnoreCase(addressType)) labTestPage.clickSelectInvalidAddress();
        else                                          labTestPage.clickSelectValidAddress();
    }

    @When("User tries to proceed to slot selection")
    public void user_tries_to_proceed_to_slot_selection() {}

    @Then("System should block further progress")
    public void system_should_block_further_progress() {
        Assert.assertNotNull(labTestPage.getErrorMsg(), "Expected blocking error – none found.");
    }

    @Then("Address error message should be displayed using data from {string}")
    public void address_error_message_should_be_displayed_using_data_from(String testCaseId) {
        String expected = ExcelUtil.getAddressData(testCaseId).get("ExpectedError");
        String actual   = labTestPage.getErrorMsg();
        Assert.assertTrue(actual.contains(expected),
                "Expected: [" + expected + "] | Actual: [" + actual + "]");
    }

    // =========================================================================
    // MODULE 4 – COUPON
    // =========================================================================

    @Given("User is on checkout page")
    public void user_is_on_checkout_page() throws InterruptedException {
    	Thread.sleep(10000);
    	labTestPage.clickQuitbutton();
    	Thread.sleep(10000);
    	labTestPage.clickExitPopUp();

    	
        Thread.sleep(10000);
        homePage.clickLabTests();
    }

    @Given("Cart contains lab tests")
    public void cart_contains_lab_tests() throws InterruptedException {
    	
    	
//        Thread.sleep(10000);
//        labTestPage.searchLabTest("cbc");
//        Thread.sleep(5000);
//        labTestPage.addSearchItem();
//        Thread.sleep(5000);
    	
        labTestPage.click_go_to_cart();
        Thread.sleep(7000);
        labTestPage.addPatient();
        Thread.sleep(7000);
        labTestPage.clickNext();
        Thread.sleep(7000);
        labTestPage.clickSelectValidAddress();
        Thread.sleep(7000);
        
        labTestPage.clickreviewCart();
        Thread.sleep(5000);
        initialCartTotal = testsCartPage.getToPayAmount();
    }

    @When("User applies coupon using data from {string}")
    public void user_applies_coupon_using_data_from(String testCaseId) throws InterruptedException {
        String couponCode = ExcelUtil.getCouponData(testCaseId).get("CouponCode");
        Thread.sleep(5000);
        testsCartPage.applyCoupon();
        Thread.sleep(10000);
        testsCartPage.enterCoupon(couponCode);
    }

    @Then("Coupon error message should be displayed using data from {string}")
    public void coupon_error_message_should_be_displayed_using_data_from(String testCaseId) {
        String expected = ExcelUtil.getCouponData(testCaseId).get("ExpectedError");
        String actual   = testsCartPage.getCouponErrMsg();
        Assert.assertTrue(actual.contains(expected),
                "Expected: [" + expected + "] | Actual: [" + actual + "]");
    }

    @Then("Cart total should remain unchanged")
    public void cart_total_should_remain_unchanged() {
        String currentTotal = testsCartPage.getToPayAmount();
        Assert.assertEquals(currentTotal, initialCartTotal,
                "Cart total changed. Before: [" + initialCartTotal + "] After: [" + currentTotal + "]");
    }

    // =========================================================================
    // MODULE 5 – CIRCLE MEMBERSHIP / PAYMENT
    // =========================================================================

    @Given("User is on Circle Membership purchase page")
    public void user_is_on_circle_membership_purchase_page() throws InterruptedException {
        Thread.sleep(10000);
        driver().get("https://www.apollo247.com/circle-landing");
    }

    @When("User selects membership plan")
    public void user_selects_membership_plan() throws InterruptedException {
        Thread.sleep(10000);
        circleMembershipPage.clickJoinCircle();
    }

    @When("User clicks on Buy Now")
    public void user_clicks_on_buy_now() throws InterruptedException {
        Thread.sleep(5000);
        circleMembershipPage.clickProceed();
    }

    @When("User enters invalid card details using data from {string}")
    public void user_enters_invalid_card_details_using_data_from(String testCaseId) throws InterruptedException {
        Map<String, String> data = ExcelUtil.getPaymentData(testCaseId);
        Thread.sleep(10000);
        circleMemPaymentPage.clickOnCreditCard();
        Thread.sleep(10000);
        Thread.sleep(5000);
        circleMemPaymentPage.enterName(data.get("CardHolderName"));
        Thread.sleep(5000);
        circleMemPaymentPage.enterCardNumber(data.get("CardNumber"));
        Thread.sleep(5000);
        circleMemPaymentPage.enterExpirymonth(data.get("ExpiryMonth"));  // "12"
        circleMemPaymentPage.enterExpiryyear(data.get("ExpiryYear"));    // "28"
        Thread.sleep(5000);
        circleMemPaymentPage.enterCVV(data.get("CVV"));
    }

    @When("User attempts payment")
    public void user_attempts_payment() throws InterruptedException {
        Thread.sleep(10000);
        circleMemPaymentPage.clickPay();
        Thread.sleep(10000);
        circleMemPaymentPage.clickOnPayPLN();
        
    }

    @Then("Payment should fail")
    public void payment_should_fail() {
        Assert.assertNotNull(circleMemPaymentPage.errorMsg(),
                "Payment Failed");
    }


    @Then("Payment error message should be displayed using data from {string}")
    public void payment_error_message_should_be_displayed_using_data_from(String testCaseId) {
        String expected = ExcelUtil.getPaymentData(testCaseId).get("ExpectedError");

        Assert.assertNotNull(expected,
                "ExpectedError is null for [" + testCaseId + "] in PaymentData sheet.");

        String actual = circleMemPaymentPage.errorMsg();

        // Case-insensitive comparison — page shows "Payment Failed",
        // guards against capitalisation changes in future deployments
        Assert.assertTrue(actual.toLowerCase().contains(expected.toLowerCase()),
                "Expected: [" + expected + "] | Actual: [" + actual + "]");
    }

   
}