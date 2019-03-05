package TestScripts;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import LibraryFiles.ReUsableLibrary;
import org.openqa.selenium.support.ui.Select;

public class TC15_CiscoDemoTwo extends ReUsableLibrary {
	public void ciscoDemoTwo() {
		try {
			driver.get("http://ec2-13-233-11-186.ap-south-1.compute.amazonaws.com:8080/prweb");
			logger.log(LogStatus.INFO, "Launched The Application");
			driver.findElement(By.id("txtUserID")).sendKeys("Rmauser1");
			driver.findElement(By.id("txtPassword")).sendKeys("rules@123");
			driver.findElement(By.xpath("//span[text()='Log in']")).click();
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Product Returns and ReplacementsFramework")));
			logger.log(LogStatus.INFO, "Logged Into The Application");
			Actions action = new Actions(driver);
			Thread.sleep(3000);
			action.moveToElement(driver.findElement(By.xpath("//div[@data-node-id='pyWorkerPortalNavigationInner']//span[1]"))).perform();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//span[text()='New']")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//span[text()='Product Returns and Replacement']")).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='Create New RMA']")));
			logger.log(LogStatus.INFO, "Navigated To Create New RMA Screen");
			driver.findElement(By.xpath("//input[@placeholder='Enter Serial Number']")).sendKeys("FTX");
			Thread.sleep(6000);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Next')]")));
			try {
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(By.xpath("//button[contains(text(),'Next')]")).click();
			} catch (Exception e) {
				driver.findElement(By.xpath("//button[contains(text(),'Next')]")).click();
			}
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			logger.log(LogStatus.INFO, "Navigated To Part Information Screen");
			// Part Information
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Pending-Registration']")));
			Select sel1 = new Select(driver.findElement(By.xpath("//select[@aria-label='Select a Failure Code from the drop down.']")));
			sel1.selectByValue("DOA - Manufacturing");
			driver.findElement(By.xpath("//textarea[@aria-label='Enter Failure Description']")).sendKeys("failure description");
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Continue')]")));
			try {
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(By.xpath("//button[contains(text(),'Continue')]")).click();
			} catch (Exception e) {
				driver.findElement(By.xpath("//button[contains(text(),'Continue')]")).click();
			}
			logger.log(LogStatus.INFO, "Navigated To Site Information Screen");
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[text()='Shipping Address']")));
			// Site Information
			driver.findElement(By.xpath("//input[@aria-label='Enter Company Name']")).sendKeys("Company");
			Thread.sleep(1000);
			driver.findElement(By.xpath("//input[@aria-label='Enter Address Line 1']")).sendKeys("Address1");
			Select sel2 = new Select(driver.findElement(By.xpath("//select[@aria-label='Select Country from the Drop Down']")));
			sel2.selectByValue("India");
			driver.findElement(By.xpath("(//input[@aria-label='Enter Name'])[1]")).sendKeys("Name");
			driver.findElement(By.xpath("(//input[@aria-label='Enter Phone Number'])[1]")).sendKeys("3275623785");
			driver.findElement(By.xpath("(//input[@aria-label='Enter Email ID'])[1]")).sendKeys("swathi.lam@wipro.com");
			driver.findElement(By.xpath("//label[text()='Same as Acknowledgement Contact']")).click();
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Continue')]")));
			try {
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(By.xpath("//button[contains(text(),'Continue')]")).click();
			} catch (Exception e) {
				driver.findElement(By.xpath("//button[contains(text(),'Continue')]")).click();
			}
			logger.log(LogStatus.INFO, "Navigated To Delivery Details Screen");
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'Customer Reference Number')]")));
			// Delivery Details
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Continue')]")));
			try {
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(By.xpath("//button[contains(text(),'Continue')]")).click();
			} catch (Exception e) {
				driver.findElement(By.xpath("//button[contains(text(),'Continue')]")).click();
			}
			logger.log(LogStatus.INFO, "Navigated To Review And Submit Screen");
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'Failure Code')]")));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Finish')]")));
			driver.findElement(By.xpath("//button[contains(text(),'Finish')]")).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Pending-ShipmentReview')]")));
			logger.log(LogStatus.INFO, "Navigated To Pending Shipment Review Screen");
			String subcaseID=driver.findElement(By.xpath("//h1[text()='Shipment']/ancestor::div[5]/following-sibling::Div[1]//span[contains(text(),'S-')]")).getText();
			Assert.assertTrue(subcaseID.contains("S-"), "A sub case has been created");
			logger.log(LogStatus.INFO, "A sub case has been created");
		} catch (InterruptedException e) {
			e.printStackTrace();
			logger.log(LogStatus.FAIL, "A sub case is not being created");
		}
	}
}
