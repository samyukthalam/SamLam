Feature: Validations 

@SmokeTest @BrowserLaunch
Scenario: Client side validations 
	Given User is Logged In Using "loanccdemouser1" And "rules" 
	When User Is In Home Page 
	And User navigates to pending-initialdataentry screen 
	And User clickes on continue button without entering any data 
	Then All the required fields should turn red with a star mark 
	
@RegressionTest @SmokeTest @BrowserLaunch
Scenario: Server side validations 
	Given User is Logged In Using "loanccdemouser1" And "rules" 
	When User Is In Home Page 
	And User navigates to pending-initialdataentry screen 
	And User clickes on continue button by entering data 
	Then Error message displayed for field entered with special characters