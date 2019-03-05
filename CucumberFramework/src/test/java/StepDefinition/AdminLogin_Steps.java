package StepDefinition;

import java.util.List;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import LibraryFiles.CreateLogger;
import LibraryFiles.ReUsableLibrary;
import ObjectRepository.HomePage;
import ObjectRepository.LoginPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AdminLogin_Steps extends ReUsableLibrary {
	@When("user enters AdminUserName and AdminPassword")
	public void user_enters_AdminUserName_and_AdminPassword(List<List<String>> usercredentials) {
		CreateLogger.LOGGER.info("Entering Admin UserName And Password");
		String userName = usercredentials.get(0).get(0);
		String password = usercredentials.get(0).get(1);
		driver.findElement(LoginPage.txtUserName).sendKeys(userName);
		driver.findElement(LoginPage.txtPassword).sendKeys(password);
		driver.findElement(LoginPage.btnLogin).click();
	}

	@Then("Admin Login should be successful")
	public void admin_Login_should_be_successful() {
		CreateLogger.LOGGER.info("Checking if admin login is successful or not");
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(HomePage.lnkResources));
		Assert.assertEquals("Resources", driver.findElement(HomePage.lnkResources).getText());
	}
}
