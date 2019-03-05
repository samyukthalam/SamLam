package TestScripts;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import LibraryFiles.ReUsableLibrary;
import ObjectRepository.HomePage;

public class TC05_RoleBasedSecurity extends ReUsableLibrary {

	public void roleBasedSecurity() {
		try {
			launchApplication();

			// When Logged In As a Manager
			Login("Manager", "TC05_RoleBasedSecurity");
			driver.switchTo().frame("PegaGadget0Ifr");
			customSleep();
			try {
				Assert.assertEquals(false, driver.findElement(HomePage.lblSummaryFor).isDisplayed());
				logger.log(LogStatus.INFO, "Summary For Section Displayed When Logged In As a Manager ");
			} catch (Exception e) {
				logger.log(LogStatus.FAIL, "Summary For Section is NOT Displayed When Logged In As a Manager ");
			}

			try {
				Assert.assertEquals(true, driver.findElement(HomePage.lblWelcomeTo).isDisplayed());
				logger.log(LogStatus.INFO, "Welcome To Section Displayed When Logged In As a Manager ");
			} catch (Exception e) {
				logger.log(LogStatus.FAIL, "Welcome To Section is NOT Displayed When Logged In As a Manager ");
			}
			driver.switchTo().defaultContent();

			Actions action = new Actions(driver);
			action.moveToElement(driver.findElement(HomePage.btnMyWork)).perform();
			customSleep();

			try {
				Assert.assertEquals(true, driver.findElement(HomePage.lnkDashboard).isDisplayed());
				logger.log(LogStatus.INFO, "Dashboard link is Displayed When Logged In As a Manager ");
			} catch (Exception e) {
				logger.log(LogStatus.FAIL, "Dashboard link is NOT Displayed When Logged In As a Manager ");
			}

			try {
				Assert.assertEquals(true, driver.findElement(HomePage.lnkReports).isDisplayed());
				logger.log(LogStatus.INFO, "Reports link is Displayed When Logged In As a Manager ");
			} catch (Exception e) {
				logger.log(LogStatus.FAIL, "Reports link is NOT Displayed When Logged In As a Manager ");
			}

			try {
				Assert.assertEquals(true, driver.findElement(HomePage.lnkFollowing).isDisplayed());
				logger.log(LogStatus.INFO, "Following link is Displayed When Logged In As a Manager ");
			} catch (Exception e) {
				logger.log(LogStatus.FAIL, "Following link is NOT Displayed When Logged In As a Manager ");
			}

			// Log Out
			LogOut();

			// When Logged In as a User
			Login("User", "TC05_RoleBasedSecurity");
			customSleep();
			action.moveToElement(driver.findElement(HomePage.btnMyWork)).perform();
			customSleep();

			try {
				Assert.assertEquals(true, driver.findElement(HomePage.lnkDashboard).isDisplayed());
				logger.log(LogStatus.FAIL, "Dashboard link is Displayed When Logged In As a User ");
			} catch (Exception e) {
				logger.log(LogStatus.INFO, "Dashboard link is NOT Displayed When Logged In As a User ");
			}

			try {
				Assert.assertEquals(true, driver.findElement(HomePage.lnkReports).isDisplayed());
				logger.log(LogStatus.FAIL, "Reports link is Displayed When Logged In As a User ");
			} catch (Exception e) {
				logger.log(LogStatus.INFO, "Reports link is NOT Displayed When Logged In As a User ");
			}

			try {
				Assert.assertEquals(true, driver.findElement(HomePage.lnkFollowing).isDisplayed());
				logger.log(LogStatus.FAIL, "Following link is Displayed When Logged In As a User ");
			} catch (Exception e) {
				logger.log(LogStatus.INFO, "Following link is NOT Displayed When Logged In As a User ");
			}

			LogOut();

		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Role Based Security Test Case Failed" + e.toString());
		}

	}

}
