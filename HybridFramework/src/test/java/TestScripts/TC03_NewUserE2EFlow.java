package TestScripts;

import java.io.IOException;
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

public class TC03_NewUserE2EFlow extends ReUsableLibrary {
	public void NewUserE2EFlow() throws IOException {
		try {
			launchApplication();
			Login("User","TC03_NewUserE2EFlow");
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.presenceOfElementLocated(HomePage.lblMyWork));
			Actions action = new Actions(driver);
			action.moveToElement(driver.findElement(HomePage.btnNew)).perform();
			Thread.sleep(1000);
			driver.findElement(HomePage.btnNew).click();
			driver.findElement(HomePage.btnLOCC).click();
			Thread.sleep(3000);
			driver.findElement(PreInitialDataEntryPage.btnSubmit).click();
			try {
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(PreInitialDataEntryPage.btnSubmit).click();
			} catch (Exception e) {
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			}
			wait.until(ExpectedConditions.presenceOfElementLocated(PendingInitialDataEntryPage.txtCustDataSrc));
			String firstName = getData("TestDataSheet", "FirstName", "TC03_NewUserE2EFlow");
			String lastName = getData("TestDataSheet", "LastName", "TC03_NewUserE2EFlow");
			String gender = getData("TestDataSheet", "Gender", "TC03_NewUserE2EFlow");
			String mobile = getData("TestDataSheet", "Mobile", "TC03_NewUserE2EFlow");
			String email = getData("TestDataSheet", "Email", "TC03_NewUserE2EFlow");
			String nik = getData("TestDataSheet", "NIK", "TC03_NewUserE2EFlow");
			String country = getData("TestDataSheet", "Country", "TC03_NewUserE2EFlow");
			String pin = getData("TestDataSheet", "PIN", "TC03_NewUserE2EFlow");
			String loanAmount = getData("TestDataSheet", "LoanAmount", "TC03_NewUserE2EFlow");
			String loanType = getData("TestDataSheet", "LoanType", "TC03_NewUserE2EFlow");
			String decision1 = getData("TestDataSheet", "Decision1", "TC03_NewUserE2EFlow");
			String decision2 = getData("TestDataSheet", "Decision2", "TC03_NewUserE2EFlow");
			String decision3 = getData("TestDataSheet", "Decision3", "TC03_NewUserE2EFlow");

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
			logger.log(LogStatus.INFO, "Completed New Assignment End To End Flow");
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
