Feature: Logout 

@BrowserLaunch
Scenario Outline: Logout When Logged In As User And Manager 
	Given User navigates to Login Page 
	When User enter UserName as "<UserName>" And Password as "<Password>" 
	Then User login should be successful
	When User Clicks LogOut 
	Then User Should Be Logged Out 
	Examples: 
		|UserName|				|Password|
		|loanccdemouser1|		|rules|
		|loanccdemomanager1| 	|rules|