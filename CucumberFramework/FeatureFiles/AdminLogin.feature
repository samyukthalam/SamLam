Feature: Admin Login 

@BrowserLaunch
Scenario: Login as Admin 
	Given User navigates to Login Page 
	When user enters AdminUserName and AdminPassword 
		|admin.loancc|rules|
	Then Admin Login should be successful