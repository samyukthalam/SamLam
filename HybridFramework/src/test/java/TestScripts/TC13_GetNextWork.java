package TestScripts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import LibraryFiles.ReUsableLibrary;
import ObjectRepository.ExamGradingPage;
import ObjectRepository.HomePage;
import ObjectRepository.MyWorkPage;
import ObjectRepository.NewFFMPage;
import ObjectRepository.PreInitialDataEntryPage;

public class TC13_GetNextWork extends ReUsableLibrary {

	public void validateGNW() {
		try {
			launchApplication();
			Login("MathOp", "TC13_GetNextWork");
			WebDriverWait wait = new WebDriverWait(driver, 60);
			Actions action = new Actions(driver);
			action.moveToElement(driver.findElement(HomePage.btnNew)).perform();
			Thread.sleep(1000);
			driver.findElement(HomePage.btnNew).click();
			Thread.sleep(1000);
			driver.findElement(HomePage.btnExamGrading).click();
			driver.switchTo().frame("PegaGadget1Ifr");
			wait.until(ExpectedConditions.presenceOfElementLocated(HomePage.lblCaseDetails));
			driver.findElement(HomePage.lblCaseDetails).click();
			Select selSubject = new Select(driver.findElement(ExamGradingPage.selSubject));
			String subject = getData("TestDataSheet", "Subject", "TC13_GetNextWork");
			selSubject.selectByValue(subject);
			driver.findElement(PreInitialDataEntryPage.btnSubmit).click();
			try {
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(PreInitialDataEntryPage.btnSubmit).click();
			} catch (Exception e) {
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			}
			wait.until(ExpectedConditions.presenceOfElementLocated(ExamGradingPage.lnkExamThesis));
			logger.log(LogStatus.INFO, "Created a New Exam Grading with Subject as: " + subject);
			String caseID = driver.findElement(ExamGradingPage.lblCaseID).getText();
			logger.log(LogStatus.INFO, "Case ID for the new Exam Grading is: " + caseID.substring(1, caseID.length() - 1));
			putData("TestDataSheet", "CaseID", "TC13_GetNextWork", caseID.substring(1, caseID.length() - 1));
			driver.switchTo().defaultContent();
			LogOut();
			Login("ScienceOp", "TC13_GetNextWork");
			driver.switchTo().frame("PegaGadget0Ifr");
			driver.findElement(HomePage.btnGetNextWork).click();
			logger.log(LogStatus.INFO, "Clicked on GetNextWork button in the Home Page");
			Thread.sleep(3000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("PegaGadget1Ifr");
			String trimmedCaseID = caseID.substring(1, caseID.length() - 1);
			Assert.assertEquals(driver.findElement(By.xpath("//span[contains(text(),'" + trimmedCaseID + "')]")).getText(), caseID, "Case ID is displayed in ScienceOp WorkBasket");
			logger.log(LogStatus.INFO, "Case ID is displayed in ScienceOp WorkBasket: " + driver.findElement(By.xpath("//span[contains(text(),'" + trimmedCaseID + "')]")).getText());
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Case ID is NOT displayed in ScienceOp WorkBasket");
			Assert.fail(e.toString());
		}
	}

}
