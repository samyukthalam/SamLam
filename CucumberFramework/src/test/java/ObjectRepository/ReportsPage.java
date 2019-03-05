package ObjectRepository;

import org.openqa.selenium.By;

public class ReportsPage {

	public static final By btnReports = By.xpath("//button[text()='Reports']");
	public static final By lblPrivateCategories = By.xpath("//h2[text()='Private categories']");
	public static final By btnAddCategory = By.xpath("//button[text()='Add category']");
	public static final By lblCreateNewCat = By.xpath("//span[contains(text(),'Create new category')]");
	public static final By txtCategoryName = By.id("pyCategoryName");
	public static final By txtCategoryDesc = By.id("pyCategoryDescription");
	public static final By selCategoryType = By.id("pyOwnerType");
	public static final By optCatTypePrivate = By.xpath("//option[text()='Private']");
	public static final By btnSubmit = By.xpath("//button[contains(text(),'Submit')]");
	public static final By btnAddReport = By.xpath("//button[text()='Add report']");
	public static final By lblCreateNewReport = By.xpath("//span[contains(text(),'Create New Report')]");
	public static final By selCaseType = By.xpath("//span[text()='Case type']/parent::label/following-sibling::div/select");
	public static final By selReportType = By.xpath("//span[text()='Report type']/parent::label/following-sibling::div/select");
	public static final String listRepTypeValue = "pyDefaultReport";
	public static final String summaryRepTypeValue = "pyDefaultSummaryReport";
	public static final By lblDataExplorer = By.xpath("//label[text()='Data Explorer']");
	public static final By txtReportTitle = By.id("pyReportTitle");
	public static final By lblDropColumn = By.xpath("//div[contains(text(),'Drop column to add Filter')]");
	public static final By btnApplyChanges = By.xpath("//div[text()='Apply changes']");
	public static final By btnDoneEditing = By.xpath("//button[text()='Done editing']");
	public static final By selCategory = By.id("pyCategoryDescription");
	public static final By btnCloseReport = By.xpath("//button[text()='Edit report']/ancestor::div[3]/following-sibling::div[2]//a");
	public static final By txtRepColValue = By.xpath("//h2[text()='Edit filter']/parent::div/parent::div/following-sibling::div[1]/div/div/div/div/div/div//table[1]/tbody/tr[2]//td[3]//input");
	public static final By lblSaveReportAs = By.xpath("//span[contains(text(),'Save report as')]");
}
