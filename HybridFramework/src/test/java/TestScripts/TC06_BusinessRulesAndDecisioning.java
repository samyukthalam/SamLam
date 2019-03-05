package TestScripts;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.relevantcodes.extentreports.LogStatus;
import LibraryFiles.ReUsableLibrary;
import ObjectRepository.HomePage;
import ObjectRepository.PendingInitialDataEntryPage;
import ObjectRepository.PreInitialDataEntryPage;
import junit.framework.Assert;

public class TC06_BusinessRulesAndDecisioning extends ReUsableLibrary {

	public void businessRulesAndDecisioning(String loanAmount, String loanType, String decision1, String decision2, String decision3) throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			launchApplication();
			Login("User", "TC06_BusinessRulesAndDecisioning");
			driver.findElement(HomePage.lnkFirstIniDataEntry).click();
			custom3Sleep();
			releaseLock();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			try {
				if (driver.findElement(PreInitialDataEntryPage.lblPreIniDataEntry).isDisplayed()) {
					driver.findElement(PreInitialDataEntryPage.btnSubmit).click();
					wait.until(ExpectedConditions.presenceOfElementLocated(PendingInitialDataEntryPage.txtFirstName));
				}
			} catch (Exception e) {
				System.out.println("Submit button is not displayed");
			}
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			String firstName = getData("TestDataSheet", "FirstName", "TC06_BusinessRulesAndDecisioning");
			String lastName = getData("TestDataSheet", "LastName", "TC06_BusinessRulesAndDecisioning");
			String gender = getData("TestDataSheet", "Gender", "TC06_BusinessRulesAndDecisioning");
			String mobile = getData("TestDataSheet", "Mobile", "TC06_BusinessRulesAndDecisioning");
			String email = getData("TestDataSheet", "Email", "TC06_BusinessRulesAndDecisioning");
			String nik = getData("TestDataSheet", "NIK", "TC06_BusinessRulesAndDecisioning");
			String country = getData("TestDataSheet", "Country", "TC06_BusinessRulesAndDecisioning");
			String pin = getData("TestDataSheet", "PIN", "TC06_BusinessRulesAndDecisioning");

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
			logger.log(LogStatus.INFO, "All the decisions are displayed correctly When the Loan Amount Is " + "\n" + loanAmount + " Decision1: " + decision1 + " Decision2: " + decision2 + " Decision3: " + decision3);
			LogOut();
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "All the decisions are NOT displayed correctly When the Loan Amount Is " + "\n" + loanAmount + " Decision1: " + decision1 + " Decision2: " + decision2 + " Decision3: " + decision3);
		}
	}
}