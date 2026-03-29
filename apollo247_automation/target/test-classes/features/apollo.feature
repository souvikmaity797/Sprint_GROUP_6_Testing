
Feature: Apollo247 Lab Tests End-to-End Validation

  Background:
    Given User is logged in using data from "TC_LOGIN_01"

  # ===========================================================================
  # MODULE 1 – SEARCH FUNCTIONALITY
  # ===========================================================================

  @TC_AP_01_01
  Scenario Outline: Verify lab test search results contain relevant keywords
    Given User is on lab tests page
    When User searches for lab test using data from "<testCaseId>"
    Then Search results should be displayed
    And Each search result should contain expected keywords from "<testCaseId>"
    And Add button should be functional for each result

    Examples:
      | testCaseId  |
      | TC_AP_01_01 |

  # ---------------------------------------------------------------------------

 	 @TC_AP_02_02
  Scenario Outline: Verify search with invalid keyword shows error message
    Given User is on lab tests page
    When User searches for lab test using data from "<testCaseId>"
    And User presses Enter
    Then Error message for search should be visible using data from "<testCaseId>"

    Examples:
      | testCaseId  |
      | TC_AP_02_02 |

  # ===========================================================================
  # MODULE 2 – CART FUNCTIONALITY
  # ===========================================================================

   @TC_AP_03_01
  Scenario Outline: Verify lab test added to cart shows correct price, MRP and discount
    Given User adds lab test to cart using data from "<testCaseId>"
    When User clicks on Add button for the test
    And User navigates to cart page
    Then Lab test should be present in cart
    And Cart price details should match expected values from "<testCaseId>"

    Examples:
      | testCaseId  |
      | TC_AP_03_01 |

  # ---------------------------------------------------------------------------
 @TC_AP_03_03
  Scenario Outline: Verify removing an item from cart updates the total correctly
    Given User adds lab first test to cart using data from "TC_AP_03_01"
    Given Cart is prepared with tests using data from "<testCaseId>"
    When User removes first item from cart
    Then Remaining cart details should match expected values from "<testCaseId>"

    Examples:
      | testCaseId  |
      | TC_AP_03_03 |

  # ===========================================================================
  # MODULE 3 – ADDRESS / BOOKING VALIDATION
  # ===========================================================================

   @TC_AP_04_02
  Scenario Outline: Verify non-serviceable address blocks booking progress
    Given User has tests in cart
    When User proceeds to booking
    And User enters invalid address using data from "<testCaseId>"
    And User tries to proceed to slot selection
    Then System should block further progress
    And Address error message should be displayed using data from "<testCaseId>"

    Examples:
      | testCaseId  |
      | TC_AP_04_02 |

  # ===========================================================================
  # MODULE 4 – COUPON CODE VALIDATION
  # ===========================================================================

   @TC_AP_07_02
  Scenario Outline: Verify invalid coupon code shows error and does not change cart total
    Given User is on checkout page
    And Cart contains lab tests
    When User applies coupon using data from "<testCaseId>"
    Then Coupon error message should be displayed using data from "<testCaseId>"
    And Cart total should remain unchanged

    Examples:
      | testCaseId  |
      | TC_AP_07_02 |

  # ===========================================================================
  # MODULE 5 – CIRCLE MEMBERSHIP / PAYMENT
  # ===========================================================================

  @TC_AP_09_02
  Scenario Outline: Verify failed payment does not activate Circle membership
    Given User is on Circle Membership purchase page
    When User selects membership plan
    And User clicks on Buy Now
    And User enters invalid card details using data from "<testCaseId>"
    And User attempts payment
    Then Payment should fail
    And Payment error message should be displayed using data from "<testCaseId>"
    And Membership should not be activated

    Examples:
      | testCaseId  |
      | TC_AP_09_02 |
