package StepDefinition;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import LibraryFiles.CreateLogger;
import LibraryFiles.ReUsableLibrary;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks extends ReUsableLibrary {
	private static SimpleDateFormat strScreenShotName = new SimpleDateFormat("MMddyy_HHmmss");
	private static String strScreenShotDir = new SimpleDateFormat("MMddyy").format(new Date());

	@Before("@BrowserLaunch")
	public static void b4fore(Scenario s) throws IOException {
		CreateLogger.LOGGER.info("Starting Execution of the scenario: " + s.getName());
		OpenWDInstance();
	}

	@Before("@Chrome")
	public static void b4foreChrome(Scenario s) throws IOException {
		CreateLogger.LOGGER.info("Starting Execution of the scenario: " + s.getName() + " In Chrome Browser");
		String chromeDriverPath = getElementFromPropFile("ChromeDriverPath");
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@Before("@Firefox")
	public static void b4foreFirefox(Scenario s) throws IOException {
		CreateLogger.LOGGER.info("Starting Execution of the scenario: " + s.getName() + " In Firefox Browser");
		String GeckoDriverPath = getElementFromPropFile("GeckoDriverPath");
		System.setProperty("webdriver.gecko.driver", GeckoDriverPath);
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}

	@Before("@IE")
	public static void b4foreIE(Scenario s) throws IOException {
		CreateLogger.LOGGER.info("Starting Execution of the scenario: " + s.getName() + " In IE Browser");
		String ieDriverPath = getElementFromPropFile("InternetExplorerDriverPath");
		System.setProperty("webdriver.ie.driver", ieDriverPath);
		driver = new InternetExplorerDriver();
		driver.manage().window().maximize();
	}

	@After
	public static void after(Scenario scenario) throws IOException {
		try {
			String screenshotForFail = getElementFromPropFile("ScreenShotForFail");
			if (scenario.isFailed() && screenshotForFail.equals("True")) {
				CreateLogger.LOGGER.info("Taking Screenshot for the failed scenario: " + scenario.getName());
				File file = new File(System.getProperty("user.dir") + "\\ScreenShots\\" + "\\Failed\\" + strScreenShotDir);
				if (!file.exists()) {
					System.out.println("File created " + file);
					file.mkdir();
				}
				File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				byte[] scrFileInBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				scenario.embed(scrFileInBytes, "image/png");
				scenario.write("URL at failure: " + driver.getCurrentUrl());
				try {
					String screenshotName = scenario.getName() + strScreenShotName.format(new Date()) + ".png";
					File targetFile = new File(file, screenshotName);
					FileUtils.copyFile(scrFile, targetFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			driver.quit();
			CreateLogger.LOGGER.info("Completed Execution of scenario: " + scenario.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
