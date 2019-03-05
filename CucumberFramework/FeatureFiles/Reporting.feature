Feature: Reporting 

@BrowserLaunch 
Scenario: Create A Report
	Given User is on Login Page
	When User enters "loanccdemomanager1" and "rules" 
	Then Home Page is displayed
	When User Clicks On Reporting Button
	Then Reporting Page Is Displayed
	When User Creates A Report Category
	|RepCat10|RepCatDesc10|Public|
	And User Creates A Report
	|LOCC|List|RepTitle10|Case ID|pyID|
	Then Verify The Report Is Created Successfully
	 