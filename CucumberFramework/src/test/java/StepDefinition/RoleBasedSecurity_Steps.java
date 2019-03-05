package StepDefinition;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import LibraryFiles.CreateLogger;
import LibraryFiles.ReUsableLibrary;
import ObjectRepository.HomePage;
import cucumber.api.java.en.Then;

public class RoleBasedSecurity_Steps extends ReUsableLibrary {

	@Then("Fields And Tabs specific to manager are displayed")
	public void fields_And_Tabs_specific_to_manager_are_displayed() {
		driver.switchTo().frame("PegaGadget0Ifr");
		customSleep();
		Assert.assertEquals(false, driver.findElement(HomePage.lblSummaryFor).isDisplayed());
		CreateLogger.LOGGER.info("Summary For Section Displayed When Logged In As a Manager ");
		Assert.assertEquals(true, driver.findElement(HomePage.lblWelcomeTo).isDisplayed());
		CreateLogger.LOGGER.info("Welcome To Section Displayed When Logged In As a Manager ");
		driver.switchTo().defaultContent();
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(HomePage.btnMyWork)).perform();
		customSleep();
		Assert.assertEquals(true, driver.findElement(HomePage.lnkDashboard).isDisplayed());
		CreateLogger.LOGGER.info("Dashboard link is Displayed When Logged In As a Manager ");
		Assert.assertEquals(true, driver.findElement(HomePage.lnkReports).isDisplayed());
		CreateLogger.LOGGER.info("Reports link is Displayed When Logged In As a Manager ");
		Assert.assertEquals(true, driver.findElement(HomePage.lnkFollowing).isDisplayed());
		CreateLogger.LOGGER.info("Following link is Displayed When Logged In As a Manager ");
	}

	@Then("Fields And Tabs specific to manager are NOT displayed")
	public void fields_And_Tabs_specific_to_manager_are_NOT_displayed() {
		customSleep();
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(HomePage.btnMyWork)).perform();
		customSleep();
		Assert.assertEquals(true, driver.findElement(HomePage.lnkDashboard).isDisplayed());
		CreateLogger.LOGGER.info("Dashboard link is Displayed When Logged In As a User ");
		Assert.assertEquals(true, driver.findElement(HomePage.lnkReports).isDisplayed());
		CreateLogger.LOGGER.info("Reports link is Displayed When Logged In As a User ");
		Assert.assertEquals(true, driver.findElement(HomePage.lnkFollowing).isDisplayed());
		CreateLogger.LOGGER.info("Following link is Displayed When Logged In As a User ");
	}

}
