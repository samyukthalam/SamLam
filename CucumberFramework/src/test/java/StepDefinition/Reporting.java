package StepDefinition;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import LibraryFiles.CreateLogger;
import LibraryFiles.ReUsableLibrary;
import ObjectRepository.ReportsPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Reporting extends ReUsableLibrary {
	public static String repCatDesc;
	public static String reportTitle;

	@When("User Clicks On Reporting Button")
	public void user_Clicks_On_Reporting_Button() {
		driver.switchTo().frame("PegaGadget0Ifr");
		custom3Sleep();
		driver.findElement(ReportsPage.btnReports).click();

	}

	@Then("Reporting Page Is Displayed")
	public void reporting_Page_Is_Displayed() {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(ReportsPage.lblPrivateCategories));
	}

	@When("User Creates A Report Category")
	public void user_Creates_A_Report_Category(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
		CreateLogger.LOGGER.info("Creating Report Category");
		String repCatName = dataTable.asList().get(0);
		repCatDesc = dataTable.asList().get(1);
		String repCatType = dataTable.asList().get(2);
		WebDriverWait wait = new WebDriverWait(driver, 60);
		custom3Sleep();
		driver.findElement(ReportsPage.btnAddCategory).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(ReportsPage.lblCreateNewCat));
		driver.findElement(ReportsPage.txtCategoryName).sendKeys(repCatName);
		int i = 1;
		do {
			try {
				driver.findElement(ReportsPage.txtCategoryDesc).clear();
				Thread.sleep(200);
				driver.findElement(ReportsPage.txtCategoryDesc).sendKeys(repCatDesc);
				i++;
				Thread.sleep(200);
			} catch (Exception e) {
			}
		} while (i <= 2);
		Thread.sleep(1000);
		int j = 1;
		do {
			try {
				driver.findElement(ReportsPage.selCategoryType).click();
				if (repCatType.equals("Private"))
					driver.findElement(ReportsPage.optCatTypePrivate).click();
				j++;
				Thread.sleep(200);
			} catch (Exception e) {
			}
		} while (j <= 2);
		try {
			driver.findElement(ReportsPage.btnSubmit).click();
		} catch (Exception e) {
			driver.findElement(ReportsPage.btnSubmit).click();
		}
		customSleep();
		String repCatDescActual = driver.findElement(By.linkText(repCatDesc)).getText();
		Assert.assertEquals(repCatDesc, repCatDescActual);
	}

	@When("User Creates A Report")
	public void user_Creates_A_Report(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
		CreateLogger.LOGGER.info("Creating a Report");
		String caseType = dataTable.asList().get(0);
		String reportType = dataTable.asList().get(1);
		reportTitle = dataTable.asList().get(2);
		String repColumn = dataTable.asList().get(3);
		String repColumnValue = dataTable.asList().get(4);
		WebDriverWait wait = new WebDriverWait(driver, 60);
		driver.findElement(ReportsPage.btnAddReport).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(ReportsPage.lblCreateNewReport));
		Select sel2 = new Select(driver.findElement(ReportsPage.selCaseType));
		sel2.selectByVisibleText(caseType);
		Thread.sleep(500);
		int k = 1;
		do {
			Select sel3 = new Select(driver.findElement(ReportsPage.selReportType));
			Thread.sleep(500);
			if (reportType.equals("List"))
				sel3.selectByValue(ReportsPage.listRepTypeValue);
			else if (reportType.equals("Summary"))
				sel3.selectByValue(ReportsPage.summaryRepTypeValue);
			k++;
		} while (k == 2);
		driver.findElement(ReportsPage.btnSubmit).click();
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(ReportsPage.btnSubmit).click();
		} catch (Exception e) {
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		}
		customSleep();
		driver.switchTo().defaultContent();
		driver.switchTo().frame("PegaGadget1Ifr");
		wait.until(ExpectedConditions.presenceOfElementLocated(ReportsPage.lblDataExplorer));
		driver.findElement(ReportsPage.txtReportTitle).clear();
		driver.findElement(ReportsPage.txtReportTitle).sendKeys(reportTitle);
		Actions action = new Actions(driver);
		WebElement target = driver.findElement(ReportsPage.lblDropColumn);
		WebElement source = driver.findElement(By.xpath("//label[contains(text(),'" + repColumn + "')]"));
		action.dragAndDrop(source, target).perform();
		customSleep();
		driver.findElement(ReportsPage.txtRepColValue).sendKeys(repColumnValue);
		driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='" + repColumnValue + "']")));
		driver.findElement(By.xpath("//span[text()='" + repColumnValue + "']")).click();
		driver.switchTo().frame("PegaGadget1Ifr");
		driver.findElement(ReportsPage.btnApplyChanges).click();
		customSleep();
		try {
			driver.findElement(ReportsPage.btnDoneEditing).click();
		} catch (Exception e) {
			driver.findElement(ReportsPage.btnDoneEditing).click();
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(ReportsPage.lblSaveReportAs));
		Select selRepCat = new Select(driver.findElement(ReportsPage.selCategory));
		try {
			selRepCat.selectByVisibleText(repCatDesc);
		} catch (Exception e) {
			selRepCat.selectByVisibleText(repCatDesc);
		}
		customSleep();
		try {
			driver.findElement(ReportsPage.btnSubmit).click();
		} catch (Exception e) {
			driver.findElement(ReportsPage.btnSubmit).click();
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(ReportsPage.btnCloseReport));
		try {
			action.moveToElement(driver.findElement(ReportsPage.btnCloseReport)).perform();
			driver.findElement(ReportsPage.btnCloseReport).click();
		} catch (Exception e) {
			action.moveToElement(driver.findElement(ReportsPage.btnCloseReport)).perform();
			driver.findElement(ReportsPage.btnCloseReport).click();
		}
		custom3Sleep();
		driver.switchTo().defaultContent();
		driver.switchTo().frame("PegaGadget0Ifr");
		custom3Sleep();
		driver.findElement(By.linkText(repCatDesc)).click();
		customSleep();

	}

	@Then("Verify The Report Is Created Successfully")
	public void verify_The_Report_Is_Created_Successfully() {
		CreateLogger.LOGGER.info("Verifying if the created report is displayed or not");
		Assert.assertEquals(reportTitle, driver.findElement(By.linkText(reportTitle)).getText());
	}
}
