Feature: End To End Flow On Multiple Browsers

@IE
Scenario: Create End To End Flow By Logging In As User 
	Given User is Logged In Using "loanccdemouser1" And "rules" 
	When User Is In Home Page 
	And Creates An Assignment From End To End 
	Then Assignment End To End Flow Should Be Completed 
@Chrome
Scenario: Create End To End Flow By Logging In As User 
	Given User is Logged In Using "loanccdemouser1" And "rules" 
	When User Is In Home Page 
	And Creates An Assignment From End To End 
	Then Assignment End To End Flow Should Be Completed 
@Firefox
Scenario: Create End To End Flow By Logging In As User 
	Given User is Logged In Using "loanccdemouser1" And "rules" 
	When User Is In Home Page 
	And Creates An Assignment From End To End 
	Then Assignment End To End Flow Should Be Completed 