Feature: Full Signup, Login, and Cleanup Flow

  @setup
  Scenario: User account creation
    Given User launches the browser
    When User navigates to "http://automationexercise.com"
    Then Home page should be visible
    When User clicks on 'Signup / Login' button
    Then 'New User Signup!' should be visible
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
    Then 'New User Signup!' should be visible
    When User enters name "Rahul" and already registered email "rahul.test123@testmail.com"
    And Clicks on Signup button
    Then Error message "Email Address already exist!" should be visible
    

