package StepDefinition;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import LibraryFiles.CreateLogger;
import LibraryFiles.ReUsableLibrary;
import ObjectRepository.HomePage;
import ObjectRepository.NewFFMPage;
import ObjectRepository.PreInitialDataEntryPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SkillBasedRouting extends ReUsableLibrary {
	WebDriverWait wait;
	public static String custIDValue;
	public static String caseIDValue;

	@Then("Manager Home Page is displayed")
	public void manager_Home_Page_is_displayed() {
		CreateLogger.LOGGER.info("Checking if Manager home page is displayed or not");
		wait = new WebDriverWait(driver, 60);
		driver.switchTo().frame("PegaGadget0Ifr");
		wait.until(ExpectedConditions.presenceOfElementLocated(HomePage.lblCaseManager));
		Assert.assertEquals("CASE MANAGER", driver.findElement(HomePage.lblCaseManager).getText());
		driver.switchTo().defaultContent();
	}

	@When("New FFM is created with customerID {string}")
	public void new_FFM_is_created_with_customerID(String custID) throws InterruptedException, IOException {
		custIDValue = custID;
		wait = new WebDriverWait(driver, 60);
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(HomePage.btnNew)).perform();
		Thread.sleep(1000);
		driver.findElement(HomePage.btnNew).click();
		Thread.sleep(1000);
		driver.findElement(HomePage.btnFFM).click();
		driver.switchTo().frame("PegaGadget1Ifr");
		wait.until(ExpectedConditions.presenceOfElementLocated(HomePage.lblCaseDetails));
		driver.findElement(HomePage.lblCaseDetails).click();
		driver.findElement(NewFFMPage.txtCustomerID).sendKeys(custID);
		Thread.sleep(500);
		driver.findElement(NewFFMPage.btnSearch).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(NewFFMPage.lblAmountEligible));
		customSleep();
		driver.findElement(PreInitialDataEntryPage.btnSubmit).click();
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(PreInitialDataEntryPage.btnSubmit).click();
		} catch (Exception e) {
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(NewFFMPage.lnkFFM));
		CreateLogger.LOGGER.info("Created a New FFM with customerID as :" + custIDValue);
	}

	@Then("case is created")
	public void case_is_created() throws IOException {
		String caseID = driver.findElement(NewFFMPage.lblCaseID).getText();
		CreateLogger.LOGGER.info("Case ID for the new FFM is: " + caseID.substring(1, caseID.length() - 1));
		caseIDValue = caseID.substring(1, caseID.length() - 1);
		driver.switchTo().defaultContent();
	}

	@When("Navigated to Work List")
	public void navigated_to_Work_List() throws InterruptedException {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(HomePage.btnNew)).perform();
		Thread.sleep(1000);
		driver.findElement(HomePage.btnMyWork).click();
		Thread.sleep(1000);
		CreateLogger.LOGGER.info("Navigated to User WorkList By clicking on My Work button");
	}

	@Then("caseID is displayed")
	public void caseid_is_displayed() {
		driver.switchTo().frame("PegaGadget1Ifr");
		wait.until(ExpectedConditions.presenceOfElementLocated(HomePage.lblMyWork));
		Assert.assertEquals("Case ID is displayed in GoldOp WorkList", caseIDValue, driver.findElement(By.xpath("//a[text()='" + caseIDValue + "']")).getText());
		CreateLogger.LOGGER.info("Case ID is displayed in GoldOp WorkList: " + driver.findElement(By.xpath("//a[text()='" + caseIDValue + "']")).getText());
	}

}
