package ObjectRepository;

import org.openqa.selenium.By;

public class HomePage {

	// User HomePage
	public static final By txtLoanCC = By.xpath("//div[text()='LOAN CC']");
	public static final By imgPegaLogo = By.xpath("//img[@alt='Pega logo']");
	public static final By lblCaseDetails = By.xpath("//h2[text()='Case details']");
	public static final By lnkResources = By.xpath("//a[@data-test-id='20140927131516034755853']");
	public static final By btnNew = By.xpath("//span[text()='New']");
	public static final By btnLOCC = By.xpath("//span[text()='LOCC']");
	public static final By lblMyWork = By.xpath("//span[text()='My Work']");
	public static final By btnMyWork = By.xpath("//span[text()='My Work']");
	public static final By btnGetNextWork = By.xpath("//button[text()='Get next work']");
	public static final By btnDashboard = By.xpath("//span[text()='Dashboard']");
	public static final By btnLogOut = By.xpath("//*[@id=\"RULE_KEY\"]/div/div/div/div[5]/div/div/div/div/i");
	public static final By lnkLogOff = By.xpath("//span[text()='Log off']");
	public static final By lnkFirstPreIniDataEntry = By.xpath("(//span[text()='Pre Initial Data Entry'])[1]");
	public static final By lnkFirstIniDataEntry = By.xpath("(//span[text()='Initial Data Entry'])[1]");
	public static final By btnFFM = By.xpath("//span[text()='FFM']");
	public static final By btnExamGrading = By.xpath("//span[text()='Exam Grading']");
	// Manager HomePage
	public static final By lblSummaryFor = By.xpath("//span[contains(text(),'Summary for')]");
	public static final By lblWelcomeTo = By.xpath("//div[contains(text(),'Welcome to')]");
	public static final By lnkDashboard = By.xpath("//span[text()='Dashboard']");
	public static final By lnkReports = By.xpath("//span[text()='Reports']");
	public static final By lnkFollowing = By.xpath("//h3[text()='Following']");
	public static final By lblCaseManager = By.xpath("//div[text()='Case Manager']");
}
