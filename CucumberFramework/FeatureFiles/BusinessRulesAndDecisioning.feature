Feature: Business Rules And Decisioning 

@BrowserLaunch 
Scenario Outline: Verify all three decisions are approved 
	Given User is Logged In Using "loanccdemouser1" And "rules" 
	When User Is In Home Page 
	And Creates An Assignment From End To End using the json as "<jsonfilename>" 
	Then Assignment End To End Flow Should Be Completed
		Examples: 
		|jsonfilename|
		|E2EFlow.json|
		
@BrowserLaunch 
Scenario Outline: Verify the three decisions are approved, approved and rejected 
	Given User is Logged In Using "loanccdemouser1" And "rules" 
	When User Is In Home Page 
	And Creates An Assignment From End To End using the json as "<jsonfilename>" 
	Then Assignment End To End Flow Should Be Completed 
		Examples: 
		|jsonfilename|
		|BRD.json|