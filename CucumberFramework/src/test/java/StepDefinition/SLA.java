package StepDefinition;

import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import LibraryFiles.CreateLogger;
import LibraryFiles.ReUsableLibrary;
import ObjectRepository.HomePage;
import ObjectRepository.PreInitialDataEntryPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SLA extends ReUsableLibrary {
	@When("User Creates New Assignment")
	public void user_Creates_New_Assignment() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//span[text()='New']"))).perform();
		Thread.sleep(500);
		driver.findElement(HomePage.btnNew).click();
		Thread.sleep(500);
		driver.findElement(HomePage.btnLOCC).click();
		driver.findElement(HomePage.txtLoanCC).click();
		Thread.sleep(500);
		driver.switchTo().frame("PegaGadget1Ifr");
		wait.until(ExpectedConditions.presenceOfElementLocated(PreInitialDataEntryPage.lblPreIniDataEntry));
	}

	@When("Clicks Cancel Button")
	public void clicks_Cancel_Button() {
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(PreInitialDataEntryPage.btnCancel).click();
		} catch (Exception e) {
			driver.findElement(PreInitialDataEntryPage.btnCancel).click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
	}

	@Then("Validate SLA")
	public void validate_SLA(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
		String slaAction = dataTable.asList().get(0);
		if (slaAction.equals("StateChangeToPendingApproval")) {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			Thread.sleep(90000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("PegaGadget1Ifr");
			int i = 1;
			do {
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(PreInitialDataEntryPage.btnActions).click();
				i++;
			} while (i <= 3);
			wait.until(ExpectedConditions.presenceOfElementLocated(PreInitialDataEntryPage.lnkRefresh));
			driver.findElement(PreInitialDataEntryPage.lnkRefresh).click();
			Thread.sleep(1000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("PegaGadget1Ifr");
			Assert.assertTrue(driver.findElement(PreInitialDataEntryPage.tabInitiation).isDisplayed());
			CreateLogger.LOGGER.info("Tick mark displayed in initiation section ");
			String text = driver.findElement(PreInitialDataEntryPage.tabApproval).getText();
			String color = driver.findElement(PreInitialDataEntryPage.tabApproval2).getCssValue("background-color");
			Assert.assertTrue(color.contains("0, 98, 230") && text.equals("Approval"));
			CreateLogger.LOGGER.info("Approval state changed to Blue Color");
		}
	}
}
