Feature: Skill Based Routing 

@BrowserLaunch 
Scenario: 
	Verify the case created with Amount eligible <=3000 goes to GoldOp Worklist 
	Given User is on Login Page 
	When User enters "SilverOp" and "rules" 
	Then Manager Home Page is displayed
	When New FFM is created with customerID "1102"
	Then case is created
	When User Clicks LogOut 
	Then User Should Be Logged Out
	When User enters "GoldOp" and "rules" 
	Then Manager Home Page is displayed
	When Navigated to Work List
	Then caseID is displayed