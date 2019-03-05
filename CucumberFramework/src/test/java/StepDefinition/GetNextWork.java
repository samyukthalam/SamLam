package StepDefinition;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import LibraryFiles.CreateLogger;
import LibraryFiles.ReUsableLibrary;
import ObjectRepository.ExamGradingPage;
import ObjectRepository.HomePage;
import ObjectRepository.NewFFMPage;
import ObjectRepository.PreInitialDataEntryPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GetNextWork extends ReUsableLibrary {
	WebDriverWait wait;
	public static String subjectValue;
	public static String caseIDValue;

	@When("New Exam Grading is created with Subject {string}")
	public void new_Exam_Grading_is_created_with_Subject(String subject) throws InterruptedException {
		subjectValue = subject;
		wait = new WebDriverWait(driver, 60);
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
		selSubject.selectByValue(subject);
		driver.findElement(PreInitialDataEntryPage.btnSubmit).click();
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(PreInitialDataEntryPage.btnSubmit).click();
		} catch (Exception e) {
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(ExamGradingPage.lnkExamThesis));
		CreateLogger.LOGGER.info("Created a New Exam Grading with Subject as: " + subject);
		driver.switchTo().defaultContent();
	}

	@Then("Exam Grading case is created")
	public void Exam_Grading_case_is_created() throws IOException {
		driver.switchTo().frame("PegaGadget1Ifr");
		String caseID = driver.findElement(ExamGradingPage.lblCaseID).getText();
		CreateLogger.LOGGER.info("Case ID for the new Exam Grading is: " + caseID.substring(1, caseID.length() - 1));
		caseIDValue = caseID;
		driver.switchTo().defaultContent();
	}

	@When("Clicked Get Next Work button")
	public void clicked_Get_Next_Work_button() throws InterruptedException {
		Thread.sleep(3000);
		driver.switchTo().frame("PegaGadget0Ifr");
		driver.findElement(HomePage.btnGetNextWork).click();
		CreateLogger.LOGGER.info("Clicked on GetNextWork button in the Home Page");
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
	}

	@Then("Exam Grading caseID is displayed")
	public void Exam_Grading_caseID_is_displayed() {
		driver.switchTo().frame("PegaGadget1Ifr");
		String trimmedCaseID = caseIDValue.substring(1, caseIDValue.length() - 1);
		Assert.assertEquals("Case ID is displayed in ScienceOp WorkBasket", caseIDValue, driver.findElement(By.xpath("//span[contains(text(),'" + trimmedCaseID + "')]")).getText());
		CreateLogger.LOGGER.info("Case ID is displayed in ScienceOp WorkBasket: " + driver.findElement(By.xpath("//span[contains(text(),'" + trimmedCaseID + "')]")).getText());
	}

}
