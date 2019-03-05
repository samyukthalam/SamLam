package ObjectRepository;

import org.openqa.selenium.By;

public class PendingInitialDataEntryPage {
	public static final By txtCustDataSrc = By.xpath("//b[text()='Customer Data Source']");
	public static final By txtFirstName = By.id("FirstName");
	public static final By txtLastName = By.id("LastName");
	public static final By selGender = By.id("Gender");
	public static final By btnCalendar = By.xpath("//span[@aria-label='DOB']");
	public static final By btnDate = By.xpath("//a[text()='3']");
	public static final By txtMobile = By.id("MobileNumber");
	public static final By txtEmail = By.id("Email");
	public static final By txtNIK = By.id("NIK");
	public static final By txtCountry = By.id("Country");
	public static final By txtPIN = By.id("PIN");
	public static final By btnContinue = By.xpath("//button[text()='Continue']");
	public static final By btnEdit = By.xpath("//button[text()='Edit']");
	public static final String loanAmount = "LoanAmount";
	public static final By txtLoanAmount = By.id("LoanAmount");
	public static final By selLoanType = By.id("LoanType");
	public static final By btnFinish = By.xpath("//button[text()='Finish']");
	public static final By lblDecision1 = By.xpath("//span[text()='Decision1 Status']/parent::div/div[1]/span");
	public static final By lblDecision2 = By.xpath("//span[text()='Decision2 Status']/parent::div/div[1]/span");
	public static final By lblDecision3 = By.xpath("//span[text()='Decision3 Status']/parent::div/div[1]/span");
	public static final By lblFirstName = By.xpath("//span[text()='FirstName']");
	public static final By lblLastName = By.xpath("//span[text()='Last Name']");
	public static final By lblEmail = By.xpath("//span[text()='Email']");
	public static final By lblPlsEnterValidNIK = By.xpath("//div[contains(text(),'Please enter a valid NIK')]");
}
