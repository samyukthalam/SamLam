package StepDefinition;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import LibraryFiles.CreateLogger;
import LibraryFiles.ReUsableLibrary;
import LibraryFiles.jsonDataReader;
import ObjectRepository.HomePage;
import ObjectRepository.LoginPage;
import ObjectRepository.PendingInitialDataEntryPage;
import ObjectRepository.PreInitialDataEntryPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import LibraryFiles.jsonDataReader;

public class E2EFlow_Steps extends ReUsableLibrary {
	String decision1;
	String decision2;
	String decision3;

	@Given("User is Logged In Using {string} And {string}")
	public void user_is_Logged_In_Using_And(String userName, String password) {
		launchApplication();
		CreateLogger.LOGGER.info("Entering User Name And Password");
		driver.findElement(LoginPage.txtUserName).sendKeys(userName);
		driver.findElement(LoginPage.txtPassword).sendKeys(password);
		driver.findElement(LoginPage.btnLogin).click();
	}

	@When("User Is In Home Page")
	public void user_Is_In_Home_Page() {
		CreateLogger.LOGGER.info("Checking if user is in home page or not");
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(HomePage.txtLoanCC));
		Assert.assertEquals("LOAN CC", driver.findElement(HomePage.txtLoanCC).getText());
	}

	@When("Creates An Assignment From End To End using the json as {string}")
	public void creates_An_Assignment_From_End_To_End_using_the_json_as(String jsonFileName) throws IOException, InterruptedException {
		CreateLogger.LOGGER.info("Creating end to end assigmnent flow");
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(HomePage.btnNew)).perform();
		Thread.sleep(1000);
		driver.findElement(HomePage.btnNew).click();
		Thread.sleep(1000);
		driver.findElement(HomePage.btnLOCC).click();
		Thread.sleep(3000);
		driver.findElement(PreInitialDataEntryPage.btnSubmit).click();
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(PreInitialDataEntryPage.btnSubmit).click();
		} catch (Exception e) {
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		}
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(PendingInitialDataEntryPage.txtCustDataSrc));
		String firstName = jsonDataReader.jsonDataReaderMeth("FirstName", jsonFileName);
		String lastName = jsonDataReader.jsonDataReaderMeth("LastName", jsonFileName);
		String gender = jsonDataReader.jsonDataReaderMeth("Gender", jsonFileName);
		String mobile = jsonDataReader.jsonDataReaderMeth("Mobile", jsonFileName);
		String email = jsonDataReader.jsonDataReaderMeth("Email", jsonFileName);
		String nik = jsonDataReader.jsonDataReaderMeth("NIK", jsonFileName);
		String country = jsonDataReader.jsonDataReaderMeth("Country", jsonFileName);
		String pin = jsonDataReader.jsonDataReaderMeth("PIN", jsonFileName);
		String loanAmount = jsonDataReader.jsonDataReaderMeth("LoanAmount", jsonFileName);
		String loanType = jsonDataReader.jsonDataReaderMeth("LoanType", jsonFileName);
		decision1 = jsonDataReader.jsonDataReaderMeth("Decision1", jsonFileName);
		decision2 = jsonDataReader.jsonDataReaderMeth("Decision2", jsonFileName);
		decision3 = jsonDataReader.jsonDataReaderMeth("Decision3", jsonFileName);

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
	}

	@Then("Assignment End To End Flow Should Be Completed")
	public void assignment_End_To_End_Flow_Should_Be_Completed() {
		CreateLogger.LOGGER.info("Verifying decisions");
		Assert.assertEquals(decision1, driver.findElement(PendingInitialDataEntryPage.lblDecision1).getText());
		Assert.assertEquals(decision2, driver.findElement(PendingInitialDataEntryPage.lblDecision2).getText());
		Assert.assertEquals(decision3, driver.findElement(PendingInitialDataEntryPage.lblDecision3).getText());
	}

}
