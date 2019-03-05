Feature: Validate SLA 

@BrowserLaunch 
Scenario: Validate SLA In New Assignment Creation Flow 
	Given User is on Login Page 
	When User enters "loanccdemomanager1" and "rules" 
	Then Home Page is displayed 
	When User Creates New Assignment 
	And Clicks Cancel Button 
	Then Validate SLA
	|StateChangeToPendingApproval|
	