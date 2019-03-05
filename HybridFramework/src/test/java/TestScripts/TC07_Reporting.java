package TestScripts;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;
import LibraryFiles.ReUsableLibrary;
import ObjectRepository.ReportsPage;

public class TC07_Reporting extends ReUsableLibrary {

	public void reporting() {
		try {
			String repCatName = getData("TestDataSheet", "ReportCategoryName", "TC07_Reporting");
			String repCatDesc = getData("TestDataSheet", "ReportCategoryDescription", "TC07_Reporting");
			String repCatType = getData("TestDataSheet", "ReportCategoryType", "TC07_Reporting");
			String caseType = getData("TestDataSheet", "ReportCaseType", "TC07_Reporting");
			String reportType = getData("TestDataSheet", "ReportType", "TC07_Reporting");
			String reportTitle = getData("TestDataSheet", "ReportTitle", "TC07_Reporting");
			String repColumn = getData("TestDataSheet", "ReportColumn", "TC07_Reporting");
			String repColumnValue = getData("TestDataSheet", "ReportColumnValue", "TC07_Reporting");
			WebDriverWait wait = new WebDriverWait(driver, 60);
			launchApplication();
			Login("Manager", "TC07_Reporting");
			customSleep();
			driver.switchTo().frame("PegaGadget0Ifr");
			custom3Sleep();
			driver.findElement(ReportsPage.btnReports).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(ReportsPage.lblPrivateCategories));
			createACetegory(repCatName, repCatDesc, repCatType);
			createAReport(caseType, reportType, reportTitle, repColumn, repColumnValue, repCatName,repCatDesc);
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Reporting Failed");
			Assert.fail("Reporting Failed" + e.toString());
		}
	}

}
