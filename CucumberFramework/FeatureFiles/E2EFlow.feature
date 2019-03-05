Feature: End To End Flow 

@BrowserLaunch 
Scenario Outline: Create End To End Flow By Logging In As User 
	Given User is Logged In Using "loanccdemouser1" And "rules" 
	When User Is In Home Page 
	And Creates An Assignment From End To End using the json as "<jsonfilename>" 
	Then Assignment End To End Flow Should Be Completed 
	Examples: 
		|jsonfilename|
		|E2EFlow.json|