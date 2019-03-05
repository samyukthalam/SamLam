Feature: Get Next Work

@BrowserLaunch
Scenario: 
	Verify the case created with Science as Subjec goes to ScienceOp WorkBasket 
	Given User is on Login Page
	When User enters "MathOp" and "rules" 
	Then Manager Home Page is displayed
	When New Exam Grading is created with Subject "Science"
	Then Exam Grading case is created
	When User Clicks LogOut
	Then User Should Be Logged Out 
	When User enters "ScienceOp" and "rules" 
	Then Manager Home Page is displayed
	When Clicked Get Next Work button
	Then Exam Grading caseID is displayed