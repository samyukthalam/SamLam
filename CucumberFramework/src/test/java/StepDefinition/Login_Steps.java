package StepDefinition;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import LibraryFiles.CreateLogger;
import LibraryFiles.ReUsableLibrary;
import ObjectRepository.HomePage;
import ObjectRepository.LoginPage;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Login_Steps extends ReUsableLibrary {
	@Given("User is on Login Page")
	public void user_is_on_Login_Page() {
		launchApplication();
	}

	@When("User enters {string} and {string}")
	public void user_enters_and(String userName, String password) {
		CreateLogger.LOGGER.info("Entering username and password");
		driver.findElement(LoginPage.txtUserName).sendKeys(userName);
		driver.findElement(LoginPage.txtPassword).sendKeys(password);
		driver.findElement(LoginPage.btnLogin).click();
	}

	@Then("Home Page is displayed")
	public void home_Page_is_displayed() {
		CreateLogger.LOGGER.info("Checking if home page is displayed or not");
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(HomePage.txtLoanCC));
		Assert.assertEquals("LOAN CC", driver.findElement(HomePage.txtLoanCC).getText());
	}

	@When("User enters {string} and Incorrect {string}")
	public void user_enters_and_Incorrect(String userName, String password) {
		CreateLogger.LOGGER.info("Entering username and password");
		driver.findElement(LoginPage.txtUserName).sendKeys(userName);
		driver.findElement(LoginPage.txtPassword).sendKeys(password);
		driver.findElement(LoginPage.btnLogin).click();
	}

	@Then("Login Failed Error Message Displayed")
	public void login_Failed_Error_Message_Displayed() {
		try {
			CreateLogger.LOGGER.info("Checking if login failed error message is displayed or not");
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.presenceOfElementLocated(LoginPage.lblInfoEnteredIsIncorrect));
			String errorMessage = driver.findElement(LoginPage.lblInfoEnteredIsIncorrect).getText().trim();
			Assert.assertEquals("The information you entered was not recognized.", errorMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
