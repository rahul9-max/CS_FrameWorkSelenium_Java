Feature: Full Signup, Login, and Cleanup Flow

 @CategoryNavigation
Scenario: Verify category navigation and display of correct category pages
    Given User launches the browser
    And User navigates to "http://automationexercise.com"
    Then Home page should be visible successfully
    Then Categories should be visible on the left sidebar
    When User clicks on "Women" category
    And User clicks on "Tops" sub-category under "Women" category
    Then Category page should be displayed with heading "WOMEN - TOPS PRODUCTS"
    When User clicks on any sub-category under "Men" category
    Then User should be navigated to the corresponding "Men" category page

@BrandProducts
Scenario: Verify navigation and product display for brand pages
     Given User launches the browser
    And User navigates to "http://automationexercise.com"
    Then Home page should be visible successfully
    And User clicks on "Products" button on BrandPage
    Then Brands are visible on the left side bar
    When User clicks on any brand name
    Then User is navigated to brand page and brand products are displayed
    When User clicks on another brand name from the left side bar
    Then User is navigated to that brand page and relevant products are displayed
