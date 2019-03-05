Feature: Role Based Security 

@BrowserLaunch 
Scenario: Verifying certain fields and tabs are only displayed for Manager 
	Given User is on Login Page 
	When User enters "loanccdemomanager1" and "rules" 
	Then Home Page is displayed 
	And Fields And Tabs specific to manager are displayed 
@BrowserLaunch 
Scenario: Verifying certain fields and tabs are Not displayed for User 
	Given User is on Login Page 
	When User enters "loanccdemouser1" and "rules" 
	Then Home Page is displayed 
	And Fields And Tabs specific to manager are NOT displayed