package ObjectRepository;

import org.openqa.selenium.By;

public class NewFFMPage {

	public static final By txtCustomerID = By.id("CustomerID");
	public static final By btnSearch = By.xpath("//button[text()='Search']");
	public static final By lblAmountEligible = By.xpath("//label[@for='AmountEligible']");
	public static final By lnkFFM = By.xpath("//a[text()='FFM']");
	public static final By lblCaseID=By.xpath("//*[@id=\"RULE_KEY\"]/div/div/div/div[1]/span");
}
