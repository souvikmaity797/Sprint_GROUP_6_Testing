Feature: Apollo247 End-to-End Automation

Background:
Given user is on Apollo homepage
And user closes popup

# -------- SEARCH FUNCTIONALITY --------
Scenario: TC_01 Verify valid medicine search
When user clicks on Buy Medicines
And user closes popup
And user searches for "Volini"
Then search results should be displayed

Scenario: TC_02 Verify invalid medicine search
When user clicks on Buy Medicines
And user closes popup
And user searches for "aeroplane"
Then no results should be displayed

Scenario: TC_03 Verify empty search
When user clicks on Buy Medicines
And user closes popup
And user searches for ""
Then nothing should be displayed

# -------- ADD TO CART --------
Scenario: TC_04 Verify add to cart
When user clicks on Buy Medicines
And user closes popup
And user searches for "Volini"
And user selects product
And user adds product to cart
Then product should be added to cart

# -------- CART OPERATIONS --------
Scenario: TC_05 Verify quantity increase
When user clicks on Buy Medicines
And user closes popup
And user searches for "Volini"
And user selects product
And user adds product to cart
And user increases quantity
Then quantity should be increased

Scenario: TC_06 Verify remove from cart
When user clicks on Buy Medicines
And user closes popup
And user searches for "Volini"
And user selects product
And user adds product to cart
And user removes product
Then product should be removed

# -------- FILTER --------
Scenario: TC_11 Verify filter functionality
When user clicks on Buy Medicines
And user closes popup
And user searches for "Volini"
And user applies category filter
Then filtered results should be displayed