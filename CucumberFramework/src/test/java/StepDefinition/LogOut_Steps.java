package StepDefinition;

import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import LibraryFiles.CreateLogger;
import LibraryFiles.ReUsableLibrary;
import ObjectRepository.HomePage;
import ObjectRepository.LoginPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LogOut_Steps extends ReUsableLibrary {

	@Given("User navigates to Login Page")
	public void userNavigatesToLoginPage() {
		launchApplication();
	}

	@When("User enter UserName as {string} And Password as {string}")
	public void enter_UserName_And_Password(String userName, String password) {
		CreateLogger.LOGGER.info("Entering Username and Password");
		driver.findElement(LoginPage.txtUserName).sendKeys(userName);
		driver.findElement(LoginPage.txtPassword).sendKeys(password);
		driver.findElement(LoginPage.btnLogin).click();
	}

	@Then("User login should be successful")
	public void user_Login_Successful() {
		CreateLogger.LOGGER.info("Checking if Login is successful");
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(HomePage.txtLoanCC));
		Assert.assertEquals("LOAN CC", driver.findElement(HomePage.txtLoanCC).getText());
	}

	@When("User Clicks LogOut")
	public void user_Clicks_LogOut() {
		CreateLogger.LOGGER.info("Logging out");
		driver.findElement(HomePage.btnLogOut).click();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(HomePage.lnkLogOff));
		driver.findElement(HomePage.lnkLogOff).click();
	}

	@Then("User Should Be Logged Out")
	public void user_Should_Be_LoggedOut() {
		CreateLogger.LOGGER.info("Checking if logout is successful or not");
		custom3Sleep();
		Assert.assertEquals("Log in", driver.findElement(LoginPage.btnLogin).getText());
	}
}
