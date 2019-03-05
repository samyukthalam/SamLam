package ObjectRepository;

import org.openqa.selenium.By;

public class PreInitialDataEntryPage {
	public static final By btnSubmit = By.xpath("//button[text()='Submit']");
	public static final By lblPreIniDataEntry = By.xpath("//span[text()='Pending-PreinitialDataEntry']");
	public static final By lblPendingIniDataEntry = By.xpath("//span[text()='Pending-InitialDataEntry']");
	public static final By btnActions = By.xpath("//button[@title='Actions']");
	public static final By lnkRefresh = By.xpath("//span[text()='Refresh']");
	public static final By tabInitiation = By.xpath("//a[text()='Initiation']/ancestor::div[1]/following-sibling::div[1]//i");
	public static final By tabApproval = By.xpath("//a[@data-test-id='20150123134733087511578' and @class='List_link']");
	public static final By tabApproval2 = By.xpath("//a[@data-test-id='20150123134733087511578' and @class='List_link']/ancestor::div[2]");
	public static final By btnCancel = By.xpath("//button[text()='Submit']/parent::span/parent::div/preceding-sibling::div[2]//button");
}
