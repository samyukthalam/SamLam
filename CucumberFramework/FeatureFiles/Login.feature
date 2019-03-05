Feature: Login
  
  @BrowserLaunch
  Scenario: Successful Login With User Logins
  Given User is on Login Page
  When User enters "loanccdemouser1" and "rules"
  Then Home Page is displayed
  
  @BrowserLaunch
  Scenario: Successful Login With Manager Logins
  Given User is on Login Page
  When User enters "loanccdemomanager1" and "rules"
  Then Home Page is displayed
  
  @BrowserLaunch
  Scenario: UnSuccessful Login With User Logins
  Given User is on Login Page
  When User enters "loanccdemouser1" and Incorrect "rules1"
  Then Login Failed Error Message Displayed
  
  @BrowserLaunch
  Scenario: UnSuccessful Login With Manager Logins
  Given User is on Login Page
  When User enters "loanccdemomanager1" and Incorrect "rules1"
  Then Login Failed Error Message Displayed
