Feature: Apollo247 End-to-End Automation

Background:
Given user is on Apollo homepage
And user closes popup

@TC_01
Scenario: TC_01 Verify valid medicine search
When user clicks on Buy Medicines
And user closes popup
And user searches for medicine from excel row 1
Then search results should be displayed

@TC_02
Scenario: TC_02 Verify invalid medicine search
When user clicks on Buy Medicines
And user closes popup
And user searches for medicine from excel row 2
Then no results should be displayed

@TC_03
Scenario: TC_03 Verify empty search
When user clicks on Buy Medicines
And user closes popup
And user searches for medicine from excel row 3
Then nothing should be displayed

@TC_04
Scenario: TC_04 Verify add to cart
When user clicks on Buy Medicines
And user closes popup
And user searches for medicine from excel row 1
And user selects product
And user adds product to cart
Then product should be added to cart

@TC_05
Scenario: TC_05 Verify quantity increase
When user clicks on Buy Medicines
And user closes popup
And user searches for medicine from excel row 1
And user selects product
And user adds product to cart
And user increases quantity
Then quantity should be increased

@TC_06
Scenario: TC_06 Verify remove from cart
When user clicks on Buy Medicines
And user closes popup
And user searches for medicine from excel row 1
And user selects product
And user adds product to cart
And user removes product
Then product should be removed

@TC_07
Scenario: TC_11 Verify filter functionality
When user clicks on Buy Medicines
And user closes popup
And user searches for medicine from excel row 1
And user applies category filter
Then filtered results should be displayed