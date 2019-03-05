package TestScripts;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;
import com.relevantcodes.extentreports.LogStatus;

import LibraryFiles.ReUsableLibrary;
import ObjectRepository.HomePage;

public class TC14_CiscoDemo extends ReUsableLibrary {
	public void ciscoDemo() throws InterruptedException {
		try {
			Dimension d = new Dimension(1366, 768);
			driver.manage().window().setSize(d);
			String environment = System.getenv("Environment");
			if (environment.equals("Dev")) {
				driver.get("http://ec2-13-232-62-136.ap-south-1.compute.amazonaws.com:8080/prweb");
				logger.log(LogStatus.INFO, "Launched The Application in Dev Environment");
				driver.findElement(By.id("txtUserID")).sendKeys("DemoTestInitiator");
				driver.findElement(By.id("txtPassword")).sendKeys("rules@123");
				driver.findElement(By.id("submit_row")).click();
			} else if (environment.equals("QA")) {
				driver.get("http://ec2-13-232-22-182.ap-south-1.compute.amazonaws.com:8080/prweb");
				logger.log(LogStatus.INFO, "Launched The Application in QA Environment");
				driver.findElement(By.id("txtUserID")).sendKeys("DemoTestQAInitiator");
				driver.findElement(By.id("txtPassword")).sendKeys("rules@123");
				driver.findElement(By.id("submit_row")).click();
			}
			WebDriverWait wait = new WebDriverWait(driver, 60);
			Actions action = new Actions(driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='Case Worker']")));
			logger.log(LogStatus.INFO, "Logged In As Case Creator");
			Thread.sleep(3000);
			action.moveToElement(driver.findElement(By.xpath("//span[text()='New']"))).perform();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//span[text()='New']")).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='CreateAccount']")));
			driver.findElement(By.xpath("//span[text()='CreateAccount']")).click();
			Thread.sleep(1000);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[text()='.FirstName']")));
			driver.findElement(By.xpath("//label[text()='.FirstName']/following-sibling::div[1]//input"))
					.sendKeys("Sam");
			Select selLocation = new Select(
					driver.findElement(By.xpath("//label[text()='.Location']/following-sibling::div[1]//select")));
			selLocation.selectByValue("Chennai");
			driver.findElement(By.xpath("//label[text()='.LastName']/following-sibling::div[1]//input"))
					.sendKeys("Lam");
			driver.findElement(By.xpath("//label[text()='.Comment']/following-sibling::div[1]//textarea"))
					.sendKeys("Comments");
			driver.findElement(By.xpath("//button[@title='Create this item']")).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='GetInformation']")));
			String caseID = driver.findElement(By.className("workarea_header_id")).getText();
			String caseIDtrimmed = caseID.substring(1, caseID.length() - 1);
			driver.findElement(By.xpath("//button[text()='Submit']")).click();
			try {
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(By.xpath("//button[text()='Submit']")).click();
			} catch (Exception e) {
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			}
			wait.until(ExpectedConditions.presenceOfElementLocated(
					By.xpath("//a[text()='GetInformation']/parent::span/parent::div/following-sibling::div[1]//i")));
			logger.log(LogStatus.INFO, "Created a New Case");
			driver.findElement(By.xpath("//*[@id=\"RULE_KEY\"]/div/div/div/div[5]/div/div/div/div/i")).click();
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			wait.until(ExpectedConditions.presenceOfElementLocated(HomePage.lnkLogOff));
			driver.findElement(HomePage.lnkLogOff).click();
			custom3Sleep();
			Assert.assertEquals("Log in", driver.findElement(By.xpath("//span[text()='Log in']")).getText());
			logger.log(LogStatus.INFO, "Logged Out Of The Application");
			driver.manage().window().setSize(d);
			if (environment.equals("Dev")) {
				driver.findElement(By.id("txtUserID")).sendKeys("DemoTestApprover");
				driver.findElement(By.id("txtPassword")).sendKeys("rules@123");
				driver.findElement(By.id("submit_row")).click();
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='Case Manager']")));
				logger.log(LogStatus.INFO, "Logged In As Case Manager in Dev Environment");
			} else if (environment.equals("QA")) {
				driver.findElement(By.id("txtUserID")).sendKeys("DemoTestQAApprover");
				driver.findElement(By.id("txtPassword")).sendKeys("rules@123");
				driver.findElement(By.id("submit_row")).click();
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='Case Manager']")));
				logger.log(LogStatus.INFO, "Logged In As Case Manager In QA Environment");
			}
			driver.switchTo().frame("PegaGadget0Ifr");
			action.moveToElement(driver.findElement(By.xpath("//h2[text()='Work queues']"))).perform();
			logger.log(LogStatus.INFO, "Moved to Manager Work Q");
			int j = 0;
			try {
				do {
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.findElement(
							By.xpath("//h2[text()='Work queues']/ancestor::div[3]/following-sibling::div[1]/div"))
							.click();
					j++;
				} while (j <= 6);
			} catch (Exception e) {
			}
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='BankManagers']")));
			Thread.sleep(3000);

			try {
				String pages = driver
						.findElement(
								By.xpath("//div[text()='Page']/ancestor::div[2]/following-sibling::Div[3]/div[1]/div"))
						.getText();
				int noOfPages = Integer.parseInt(pages);
				if (noOfPages > 1) {
					for (int i = 1; i <= noOfPages - 1; i++) {
						driver.findElement(By.xpath("//div[text()='Case']")).click();
						Thread.sleep(3000);
					}
				}
			} catch (Exception e) {
				System.out.println("There is no pagination");
			}
			int k = 0;
			try {
				do {
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.findElement(By.xpath("//a[contains(text(),'" + caseIDtrimmed + "')]")).click();
					k++;
				} while (k <= 8);
			} catch (Exception e) {
			}
			Thread.sleep(2000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("PegaGadget1Ifr");
			driver.findElement(By.xpath("//span[text()='Approval']")).click();
			int l = 0;
			try {
				do {
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.findElement(By.xpath("//button[text()='Approve']")).click();
					l++;
				} while (l <= 6);
			} catch (Exception e) {
			}
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Resolved-Completed']")));
			String expectedText = driver.findElement(By.xpath("//*[@id=\"RULE_KEY\"]/div/div/div/div[2]/span"))
					.getText();
			Assert.assertEquals(expectedText, "RESOLVED-COMPLETED");
			logger.log(LogStatus.INFO, "Approved The Case");
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(LogStatus.FAIL, "Test Failed");
			Assert.fail("Test Failed");
		}
	}
}
