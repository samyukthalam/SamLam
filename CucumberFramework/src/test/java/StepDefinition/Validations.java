package StepDefinition;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import LibraryFiles.CreateLogger;
import LibraryFiles.ReUsableLibrary;
import LibraryFiles.jsonDataReader;
import ObjectRepository.HomePage;
import ObjectRepository.PendingInitialDataEntryPage;
import ObjectRepository.PreInitialDataEntryPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Validations extends ReUsableLibrary {
	String decision1;
	String decision2;
	String decision3;

	@When("User navigates to pending-initialdataentry screen")
	public void user_navigates_to_pending_initialdataentry_screen() throws InterruptedException {
		CreateLogger.LOGGER.info("Navigating to Pending Intitial Data Entry Screen");
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
	}

	@When("User clickes on continue button without entering any data")
	public void user_clickes_on_continue_button_without_entering_any_data() {
		CreateLogger.LOGGER.info("Clicked Continue button");
		driver.findElement(PendingInitialDataEntryPage.btnContinue).click();
	}

	@Then("All the required fields should turn red with a star mark")
	public void all_the_required_fields_should_turn_red_with_a_star_mark() {
		CreateLogger.LOGGER.info("Validating client side validation Message");
		Alert alert = driver.switchTo().alert();
		alert.accept();
		customSleep();
		String color1 = driver.findElement(PendingInitialDataEntryPage.lblFirstName).getCssValue("color");
		String hex1 = Color.fromString(color1).asHex();
		String color2 = driver.findElement(PendingInitialDataEntryPage.lblLastName).getCssValue("color");
		String hex2 = Color.fromString(color2).asHex();
		String color3 = driver.findElement(PendingInitialDataEntryPage.lblEmail).getCssValue("color");
		String hex3 = Color.fromString(color3).asHex();

		Assert.assertEquals("#FF0000", hex1.toUpperCase());
		Assert.assertEquals("#FF0000", hex2.toUpperCase());
		Assert.assertEquals("#FF0000", hex3.toUpperCase());
	}

	@When("User clickes on continue button by entering data")
	public void user_clickes_on_continue_button_by_entering_data() {
		CreateLogger.LOGGER.info("Entering Data In Pending Initial Data Entry Screen");
		String jsonFileName = "Validations.json";
		String firstName = jsonDataReader.jsonDataReaderMeth("FirstName", jsonFileName);
		String lastName = jsonDataReader.jsonDataReaderMeth("LastName", jsonFileName);
		String gender = jsonDataReader.jsonDataReaderMeth("Gender", jsonFileName);
		String mobile = jsonDataReader.jsonDataReaderMeth("Mobile", jsonFileName);
		String email = jsonDataReader.jsonDataReaderMeth("Email", jsonFileName);
		String nik = jsonDataReader.jsonDataReaderMeth("NIK", jsonFileName);
		String country = jsonDataReader.jsonDataReaderMeth("Country", jsonFileName);
		String pin = jsonDataReader.jsonDataReaderMeth("PIN", jsonFileName);
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
	}

	@Then("Error message displayed for field entered with special characters")
	public void error_message_displayed_for_field_entered_with_special_characters() {
		CreateLogger.LOGGER.info("Validating server side validation Message");
		custom3Sleep();
		String erroMessage = driver.findElement(PendingInitialDataEntryPage.lblPlsEnterValidNIK).getText();
		Assert.assertEquals("Please enter a valid NIK", erroMessage);
	}
}
