package TestScripts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import LibraryFiles.ReUsableLibrary;
import ObjectRepository.HomePage;
import ObjectRepository.MyWorkPage;
import ObjectRepository.NewFFMPage;
import ObjectRepository.PendingInitialDataEntryPage;
import ObjectRepository.PreInitialDataEntryPage;

public class TC12_SkillBasedRouting extends ReUsableLibrary {

	public void validateSkillBasedRouting() {
		try {
			launchApplication();
			Login("SilverOp", "TC12_SkillBasedRouting");
			WebDriverWait wait = new WebDriverWait(driver, 60);
			Actions action = new Actions(driver);
			action.moveToElement(driver.findElement(HomePage.btnNew)).perform();
			Thread.sleep(1000);
			driver.findElement(HomePage.btnNew).click();
			Thread.sleep(1000);
			driver.findElement(HomePage.btnFFM).click();
			driver.switchTo().frame("PegaGadget1Ifr");
			wait.until(ExpectedConditions.presenceOfElementLocated(HomePage.lblCaseDetails));
			driver.findElement(HomePage.lblCaseDetails).click();
			String custID = getData("TestDataSheet", "CustomerID", "TC12_SkillBasedRouting");
			String[] customerID = custID.split(",");
			driver.findElement(NewFFMPage.txtCustomerID).sendKeys(customerID[1]);
			Thread.sleep(500);
			driver.findElement(NewFFMPage.btnSearch).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(NewFFMPage.lblAmountEligible));
			driver.findElement(PreInitialDataEntryPage.btnSubmit).click();
			try {
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(PreInitialDataEntryPage.btnSubmit).click();
			} catch (Exception e) {
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			}
			wait.until(ExpectedConditions.presenceOfElementLocated(NewFFMPage.lnkFFM));
			logger.log(LogStatus.INFO, "Created a New FFM with customerID as: " + customerID[1]);
			String caseID = driver.findElement(NewFFMPage.lblCaseID).getText();
			logger.log(LogStatus.INFO, "Case ID for the new FFM is: " + caseID.substring(1, caseID.length() - 1));
			putData("TestDataSheet", "CaseID", "TC12_SkillBasedRouting", caseID.substring(1, caseID.length() - 1));
			driver.switchTo().defaultContent();
			LogOut();
			Login("GoldOp", "TC12_SkillBasedRouting");
			action.moveToElement(driver.findElement(HomePage.btnNew)).perform();
			Thread.sleep(1000);
			driver.findElement(HomePage.btnMyWork).click();
			Thread.sleep(1000);
			logger.log(LogStatus.INFO, "Navigated to User WorkList By clicking on My Work button");
			driver.switchTo().frame("PegaGadget1Ifr");
			wait.until(ExpectedConditions.presenceOfElementLocated(HomePage.lblMyWork));
			String trimmedCaseID = caseID.substring(1, caseID.length() - 1);
			Assert.assertEquals(driver.findElement(By.xpath("//a[text()='" + trimmedCaseID + "']")).getText(), trimmedCaseID, "Case ID is displayed in GoldOp WorkList");
			logger.log(LogStatus.INFO, "Case ID is displayed in GoldOp WorkList: " + driver.findElement(By.xpath("//a[text()='" + trimmedCaseID + "']")).getText());
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Case ID is NOT displayed in GoldOp WorkList");
			Assert.fail(e.toString());
		}
	}
}
