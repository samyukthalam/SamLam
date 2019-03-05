package TestScripts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;

import LibraryFiles.ReUsableLibrary;
import ObjectRepository.HomePage;
import ObjectRepository.PendingInitialDataEntryPage;
import ObjectRepository.PreInitialDataEntryPage;
import junit.framework.Assert;

public class TC11_E2EFlowInMultiBrowsers extends ReUsableLibrary {
	public void e2eFlowInMultiBrowsers() {
		try {
			launchApplication();
			Login("User", "TC11_E2EFlowInMultiBrowsers");
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.presenceOfElementLocated(HomePage.lblMyWork));
			Actions action = new Actions(driver);
			action.moveToElement(driver.findElement(HomePage.btnNew)).perform();
			Thread.sleep(1000);
			driver.findElement(HomePage.btnNew).click();
			driver.findElement(HomePage.btnLOCC).click();
			Thread.sleep(1000);
			driver.findElement(PreInitialDataEntryPage.btnSubmit).click();
			try {
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(PreInitialDataEntryPage.btnSubmit).click();
				customSleep();
				driver.findElement(PreInitialDataEntryPage.btnSubmit).click();
			} catch (Exception e) {
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			}
			wait.until(ExpectedConditions.presenceOfElementLocated(PendingInitialDataEntryPage.txtCustDataSrc));
			String firstName = getData("TestDataSheet", "FirstName", "TC11_E2EFlowInMultiBrowsers");
			String lastName = getData("TestDataSheet", "LastName", "TC11_E2EFlowInMultiBrowsers");
			String gender = getData("TestDataSheet", "Gender", "TC11_E2EFlowInMultiBrowsers");
			String mobile = getData("TestDataSheet", "Mobile", "TC11_E2EFlowInMultiBrowsers");
			String email = getData("TestDataSheet", "Email", "TC11_E2EFlowInMultiBrowsers");
			String nik = getData("TestDataSheet", "NIK", "TC11_E2EFlowInMultiBrowsers");
			String country = getData("TestDataSheet", "Country", "TC11_E2EFlowInMultiBrowsers");
			String pin = getData("TestDataSheet", "PIN", "TC11_E2EFlowInMultiBrowsers");
			String loanAmount = getData("TestDataSheet", "LoanAmount", "TC11_E2EFlowInMultiBrowsers");
			String loanType = getData("TestDataSheet", "LoanType", "TC11_E2EFlowInMultiBrowsers");
			String decision1 = getData("TestDataSheet", "Decision1", "TC11_E2EFlowInMultiBrowsers");
			String decision2 = getData("TestDataSheet", "Decision2", "TC11_E2EFlowInMultiBrowsers");
			String decision3 = getData("TestDataSheet", "Decision3", "TC11_E2EFlowInMultiBrowsers");

			driver.findElement(PendingInitialDataEntryPage.txtFirstName).sendKeys(firstName);
			driver.findElement(PendingInitialDataEntryPage.txtLastName).sendKeys(lastName);
			Select sel = new Select(driver.findElement(PendingInitialDataEntryPage.selGender));
			sel.selectByValue(gender);
			driver.findElement(PendingInitialDataEntryPage.btnCalendar).click();
			driver.findElement(PendingInitialDataEntryPage.btnDate).click();
			driver.findElement(PendingInitialDataEntryPage.txtMobile).sendKeys(mobile);
			driver.findElement(PendingInitialDataEntryPage.txtEmail).sendKeys(email);
			driver.findElement(PendingInitialDataEntryPage.txtNIK).sendKeys(nik);
			driver.findElement(PendingInitialDataEntryPage.txtCountry).sendKeys(country);
			driver.findElement(PendingInitialDataEntryPage.txtPIN).sendKeys(pin);
			driver.findElement(PendingInitialDataEntryPage.btnContinue).click();
			dynamicWait(60, "id", PendingInitialDataEntryPage.loanAmount);
			driver.findElement(PendingInitialDataEntryPage.txtLoanAmount).sendKeys(loanAmount);
			Select sel2 = new Select(driver.findElement(PendingInitialDataEntryPage.selLoanType));
			sel2.selectByValue(loanType);
			driver.findElement(PendingInitialDataEntryPage.btnFinish).click();
			customSleep();
			Assert.assertEquals(decision1, driver.findElement(PendingInitialDataEntryPage.lblDecision1).getText());
			Assert.assertEquals(decision2, driver.findElement(PendingInitialDataEntryPage.lblDecision2).getText());
			Assert.assertEquals(decision3, driver.findElement(PendingInitialDataEntryPage.lblDecision3).getText());
			logger.log(LogStatus.INFO, "All the decisions are correctly displayed in Approval Page");
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Decisions are NOT correctly displayed in Approval Page");
			Assert.fail("Decisions are NOT correctly displayed in Approval Page" + e.toString());
		}
	}

}
