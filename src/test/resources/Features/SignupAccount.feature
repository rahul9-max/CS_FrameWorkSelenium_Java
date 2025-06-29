Feature: Full Signup, Login, and Cleanup Flow

  @setup
  Scenario: User account creation
    Given User launches the browser
    When User navigates to "http://automationexercise.com"
    Then Home page should be visible
    When User clicks on 'Signup / Login' button
    Then 'New User Signup!' should be visible for signup
    When User enters name and email
    And Clicks on Signup button
    Then Enter Account Information page should be visible
    When User fills account details
    And Selects newsletters and offers checkboxes
    And Fills address details
    And Clicks on 'Create Account' button
    Then Account Created message should be visible
    When User clicks Continue button
    Then Logged in as username should be visible
    Then User logs out from the application
    
    
  @signupnegative
  Scenario: Signup with already registered email
    Given User launches the browser
    When User navigates to "https://automationexercise.com"
    Then Home page should be visible
    When User clicks on 'Signup / Login' button
    Then 'New User Signup!' should be visible for signup
    When User enters name "Rahul" and already registered email "rahul.test123@testmail.com"
    And Clicks on Signup button
    Then Error message "Email Address already exist!" should be visible
    
  @negative
  Scenario: Login with invalid credentials
    Given User launches the browser for login test
    When User navigates to "https://automationexercise.com" for login
    Then Home page should be visible for login
    When User clicks on "Login" button for login
    Then Verify "Login to your account" is visible
    When User enters invalid email and password
    And Clicks login button
    Then Error message "Your email or password is incorrect!" should be visible

  @login
  Scenario: Login with existing user
    Given User launches the browser for login test
    When User navigates to "https://automationexercise.com" for login
    Then Home page should be visible for login
    When User clicks on "Login" button for login
    Then Verify "Login to your account" is visible
    When User enters correct email and password
    And Clicks login button
    Then Verify that "Logged in as username" is visible
    
   @contact
    Scenario: User submits contact form with valid details
    Given User launches the browser
    And User navigates to "http://automationexercise.com"
    Then Home page should be visible successfully
    When User clicks on "Contact us" option on contact page
    Then "GET IN TOUCH" should be visible
    When User enters name, email, subject and message
    And User uploads a file
    And User clicks on "Submit" button on contact page
    And User clicks "OK" on the alert popup
    Then Success message "Success! Your details have been submitted successfully." should be visible
    #When User clicks on "Home" button
    #Then User should be navigated to home page successfully
    
  @verify
  	Scenario: Verify user can navigate to Test Cases page successfully
    Given User launches the browser
    And User navigates to "http://automationexercise.com"
    Then Home page should be visible successfully
    When user clicks on "Test Cases" button on verify page
    Then user should be navigated to the Test Cases page
    
  @productDetail
  Scenario: User navigates to product detail page and verifies product information
    Given User launches the browser
    And User navigates to "http://automationexercise.com"
    Then Home page should be visible successfully
    When user clicks on "Products" button on productPage
    Then user should be navigated to the ALL PRODUCTS page
    And the products list should be visible
    When user clicks on "View Product" of the first product
    Then user should be navigated to the product detail page
    And product name, category, price, availability, condition, and brand should be visible
    
   @search
    Scenario: User searches for a product and verifies search results
     Given User launches the browser
		 And User navigates to "http://automationexercise.com"
     Then Home page should be visible successfully
     When User clicks on 'Products' button on search page
     Then User should be navigated to ALL PRODUCTS page successfully
     When User enters product name in search input and clicks search button
     Then 'SEARCHED PRODUCTS' section should be visible
     And All products related to the search should be visible
     
   @subscription 
    Scenario: Verify subscription in home page footer
    Given User launches the browser
	  And User navigates to "http://automationexercise.com"
    Then Home page should be visible successfully
    When Scroll down to footer
    Then Verify text "SUBSCRIPTION" is visible
    When Enter email address in input and click arrow button
    Then Verify success message "You have been successfully subscribed!" is visible
    
    @cart
    Scenario: User adds two products to cart and verifies prices, quantity, and total
    Given User launches the browser
	  And User navigates to "http://automationexercise.com"
    Then Home page should be visible successfully
    When Clicks on 'Products' button for cart
    And Hover over first product and click 'Add to cart'
    And Click 'Continue Shopping' button after adding to cart
    Then Hover over second product and click 'Add to cart'
    And Click 'View Cart' button on cart page
    Then Verify both products are added to Cart
    Then Verify their prices, quantity and total price
    
    @cartAdd
    Scenario: Verify adding a product to cart with exact quantity
    Given User launches the browser
	  And User navigates to "http://automationexercise.com"
    Then Home page should be visible successfully
    When User clicks on 'View Product' for any product on the home page
    Then User should see the product details page
    When User increase the quantity to 4
    And User clicks on the 'Add to cart' button on cartAdd page
    When User clicks on the 'View Cart' button finally
    Then User should see the product displayed in the cart page with quantity 4
    
	 @RegisterWhileCheckout
	  Scenario: Place Order: Register while Checkout
	  Given User launches the browser
	  And User navigates to "http://automationexercise.com"
    Then Home page should be visible successfully
	  When User add products to cart
	  And  User clicks on 'Cart' button on RegisterWhileCheckout page 
	  Then Cart page should be displayed
	  When User clicks on 'Proceed To Checkout' button in RegisterWhileCheckout page
	  And Clicks on "Register / Login" button for registering
	  Then 'New User Signup!' heading should be visible
    When User enters name and email
    And Clicks on Signup button
    Then Account Information form should be displayed
    When User fills account details
    And Selects newsletters and offers checkboxes
    And Fills address details
    And Clicks on 'Create Account' button
    Then Account Created message should be visible
    When User clicks Continue button
    Then Logged in as username should be visible
	  When User clicks on 'Cart' button on RegisterWhileCheckout page
	  And User clicks on 'Proceed To Checkout' button in RegisterWhileCheckout page
	  Then Verify Address Details and Review Your Order
    When User enters description in comment text area and clicks 'Place Order'
    And User enters payment details: Name on Card, Card Number, CVC, Expiration date
    And User clicks 'Pay and Confirm Order' button in the end
    Then Verify success message 'Your order has been placed successfully!'
    When User clicks 'Delete Account' button for deletion
    Then Verify 'ACCOUNT DELETED!' and click 'Continue' button
    
    
  @endToEndOrder
    Scenario: Place order and delete account after login
    Given User launches the browser
    And User navigates to "http://automationexercise.com"
    Then Home page should be visible successfully
    When User add products to cart
    And User clicks on 'Cart' button in endToEndOrder Page
    Then Cart page should be displayed
    When User clicks 'Proceed To Checkout' button on endToEndOrder page
    And User clicks on "Register / Login" button in popup box
    And User fills all details in Login
    Then Verify 'Logged in as Rahul' at top
    When User clicks on 'Cart' button in endToEndOrder Page
    And User clicks 'Proceed To Checkout' button on endToEndOrder page
    Then Verify Address Details and Review Your Order
    When User enters description in comment text area and clicks 'Place Order'
    And User enters payment details: Name on Card, Card Number, CVC, Expiration date
    And User clicks 'Pay and Confirm Order' button in the end
    Then Verify success message 'Your order has been placed successfully!'
    When User clicks 'Delete Account' button for deletion
    Then Verify 'ACCOUNT DELETED!' and click 'Continue' button
    
   @RemoveCart
    Scenario: User removes a product from the cart
    Given User launches the browser
    And User navigates to "http://automationexercise.com"
    Then Home page should be visible successfully
    When User add products to cart
    And User clicks on 'Cart' button on RemoveCart page
    Then Cart page should be displayed
    When User clicks 'X' button corresponding to the product
    Then Product should be removed from the cart
    
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
    
  @SearchAndCartAfterLogin
	  Scenario: Verify searched products are retained in cart after user logs in
    Given User launches the browser
    And User navigates to "http://automationexercise.com"
    Then Home page should be visible successfully
    When User clicks on "Products" button on SearchAndCartAfterLogin page
    Then User should be navigated to "ALL PRODUCTS" page on SearchAndCartAfterLogin page
    When User enters product_name in search input and clicks search button
    Then "SEARCHED PRODUCTS" section should be visible
    And All products related to search should be visible
    When User adds all searched products to cart
    And User clicks on "View Cart" button at SearchAndCartAfterLogin page
    Then Added products should be visible in cart
    When User clicks on "Signup / Login" button and submits login credentials
    And User navigates back to "Cart" page on SearchAndCartAfterLogin page
    Then Previously added products should still be visible in cart
    

  @teardown
  Scenario: Delete test account
    Given User is on the account page
    And Verify if the user is logged in
    And User clicks delete account button
    Then Verify account deleted message
