package TestScripts;

import java.io.File;
import Listeners.*;
import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

import LibraryFiles.CreateLogger;
import LibraryFiles.ReUsableLibrary;
import LibraryFiles.TestStats;

@Listeners(MyListener.class)
public class BaseSuiteFile extends ReUsableLibrary {

	private static String strHtmlDir = new SimpleDateFormat("MMddyy").format(new Date());
	private static SimpleDateFormat strExtentReportName = new SimpleDateFormat("MMddyy_HHmmss");
	public ATUTestRecorder recorder;

	TC01_UserLogin oTC01_UserLogin;
	TC02_ManagerLogin oTC02_ManagerLogin;
	TC03_NewUserE2EFlow oTC03_NewUserE2EFlow;
	TC04_LoginWithDiffUsers oTC04_LoginWithDiffUsers;
	TC05_RoleBasedSecurity oTC05_RoleBasedSecurity;
	TC06_BusinessRulesAndDecisioning oTC06_BusinessRulesAndDecisioning;
	TC07_Reporting oTC07_Reporting;
	TC08_WorkFlowCSV oTC08_WorkFlowCSV;
	TC09_WorkFlowSSV oTC09_WorkFlowSSV;
	TC10_validateSLA oTC10_validateSLA;
	TC11_E2EFlowInMultiBrowsers oTC11_E2EFlowInMultiBrowsers;
	TC12_SkillBasedRouting oTC12_SkillBasedRouting;
	TC13_GetNextWork oTC13_GetNextWork;
	TC14_CiscoDemo oTC14_CiscoDemo;
	TC15_CiscoDemoTwo oTC15_CiscoDemoTwo;

	@BeforeSuite
	public void beforeSuite() {
		CreateLogger.LOGGER.info("Suite STARTED");
		System.out.println("Suite Started");
	}

	@BeforeClass
	public void setup() {
		oTC01_UserLogin = new TC01_UserLogin();
		oTC02_ManagerLogin = new TC02_ManagerLogin();
		oTC03_NewUserE2EFlow = new TC03_NewUserE2EFlow();
		oTC04_LoginWithDiffUsers = new TC04_LoginWithDiffUsers();
		oTC05_RoleBasedSecurity = new TC05_RoleBasedSecurity();
		oTC06_BusinessRulesAndDecisioning = new TC06_BusinessRulesAndDecisioning();
		oTC07_Reporting = new TC07_Reporting();
		oTC08_WorkFlowCSV = new TC08_WorkFlowCSV();
		oTC09_WorkFlowSSV = new TC09_WorkFlowSSV();
		oTC10_validateSLA = new TC10_validateSLA();
		oTC11_E2EFlowInMultiBrowsers = new TC11_E2EFlowInMultiBrowsers();
		oTC12_SkillBasedRouting = new TC12_SkillBasedRouting();
		oTC13_GetNextWork = new TC13_GetNextWork();
		oTC14_CiscoDemo = new TC14_CiscoDemo();
		oTC15_CiscoDemoTwo = new TC15_CiscoDemoTwo();
	}

	@Parameters({ "optional-browser" })
	@BeforeMethod
	public void beforeMethod(@Optional("optionalvalue") String browserFromSuiteXML, Method method) throws IOException {
		try {
			if (browserFromSuiteXML.equals("optionalvalue")) {
				System.out.println("Browser value not passed in the suite xml");
				OpenWDInstance();
				logger = extent.startTest(method.getName(), method.getAnnotation(Test.class).description());
			} else {
				// Multi Browser SetUp
				if (browserFromSuiteXML.equals("Firefox")) {
					String GeckoDriverPath = getElementFromPropFile("GeckoDriverPath");
					System.setProperty("webdriver.gecko.driver", GeckoDriverPath);
					driver = new FirefoxDriver();
				} else if (browserFromSuiteXML.equals("IE")) {
					String ieDriverPath = getElementFromPropFile("InternetExplorerDriverPath");
					System.setProperty("webdriver.ie.driver", ieDriverPath);
					driver = new InternetExplorerDriver();
				} else if (browserFromSuiteXML.equals("Chrome")) {
					String chromeDriverPath = getElementFromPropFile("ChromeDriverPath");
					System.setProperty("webdriver.chrome.driver", chromeDriverPath);
					driver = new ChromeDriver();
				} else if (browserFromSuiteXML.equals("PhantomJS")) {
					String phantomJSexePath = getElementFromPropFile("PhantomJSPath");
					File file = new File(phantomJSexePath);
					System.setProperty("phantomjs.binary.path", file.getAbsolutePath());
					driver = new PhantomJSDriver();
				}
				driver.manage().window().maximize();
				System.out.println("Created Driver Instance and Launched Browser: " + browserFromSuiteXML);
				extent.addSystemInfo("Browser", browserFromSuiteXML);
				logger = extent.startTest(method.getName(), method.getAnnotation(Test.class).description());
				logger.log(LogStatus.INFO, "Created Driver Instance And Launched Browser: " + browserFromSuiteXML);
			}
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Failed to Create Driver Instance and Launched Browser: " + e.toString());
			Assert.fail("Failed to Create Driver Instance and Launched Browser: " + e.toString());
		}

	}

	@BeforeTest
	public void beforeTest() throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH-mm-ss");
		Date date = new Date();
		try {
			recorder = new ATUTestRecorder("D:\\ScriptVideos\\", "TestVideo-" + dateFormat.format(date), false);
			recorder.start();
		} catch (ATUTestRecorderException e1) {
			e1.printStackTrace();
		}

		try {
			// Extent Report Configuration
			String environment = getElementFromPropFile("Environment");
			String projectName = getElementFromPropFile("ProjectName");
			String release = getElementFromPropFile("Release");
			String moduleName = getElementFromPropFile("ModuleName");
			String strEnv = ReUsableLibrary.getElementFromPropFile("Environment");
			String strEnvDir = ".//TestReports//HTMLReports//" + strEnv;
			ReUsableLibrary.createDir(strEnvDir);
			String strModuleDir = strEnvDir + "//" + release;
			ReUsableLibrary.createDir(strModuleDir);
			String htmlDirPath = strModuleDir + "//" + strHtmlDir;
			ReUsableLibrary.createDir(htmlDirPath);
			String htmlReportPath = htmlDirPath;
			String file = htmlReportPath + "\\STMExtentReport" + strExtentReportName.format(new Date()) + ".html";

			extent = new ExtentReports(file, false);
			extent.addSystemInfo("Project Name", projectName).addSystemInfo("Environment", environment).addSystemInfo("Release", release).addSystemInfo("Module Name", moduleName);
			extent.loadConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));
			System.out.println("Extent Report Configuration Completed");
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Failed To Configure Extent Reports" + e.toString());
			Assert.fail("Failed To Configure Extent Reports" + e.toString());
		}
	}

	@Test(enabled = false, description = "Login to The Portal using User Logins")
	public void TC01_UserLogin() throws IOException {
		oTC01_UserLogin.UserLogin();
	}

	@Test(enabled = false, description = "Login to The Portal using Manager Logins")
	public void TC02_ManagerLogin() throws IOException {
		oTC02_ManagerLogin.ManagerLogin();
	}

	@Test(enabled = false, dataProvider = "Logins", description = "Login to The Portal using different Logins from the dataprovider")
	public void TC04_LoginWithDiffUsers(String UserName, String Password) throws IOException {
		oTC04_LoginWithDiffUsers.LoginWithDiffUsers(UserName, Password);
	}

	@Test(enabled = false, description = "Login to The Portal using User Logins and create an End to End Flow")
	public void TC03_NewUserE2EFlow() throws IOException {
		oTC03_NewUserE2EFlow.NewUserE2EFlow();
	}

	@Test(enabled = false, description = "Just for skipping a test")
	public void TC04_Test() throws IOException {
		throw new SkipException("Skipped Test");
	}

	@Test(enabled = false, description = "Role Based Security")
	public void TC05_RoleBasedSecurity() {
		oTC05_RoleBasedSecurity.roleBasedSecurity();
	}

	@Test(enabled = false, dataProvider = "DiffLoanAmounts", description = "Business Rules And Decisioning")
	public void TC06_BusinessRulesAndDecisioning(String loanAmount, String loanType, String decision1, String decision2, String decision3) throws IOException {
		oTC06_BusinessRulesAndDecisioning.businessRulesAndDecisioning(loanAmount, loanType, decision1, decision2, decision3);
	}

	@Test(enabled = false, description = "Report Category and Report creation With Manager Login")
	public void TC07_Reporting() {
		oTC07_Reporting.reporting();
	}

	@Test(enabled = false, description = "WorkFlow Client Side Validations")
	public void TC08_WorkFlowCSV() {
		oTC08_WorkFlowCSV.CSV();
	}

	@Test(enabled = false, description = "WorkFlow Server Side Validations")
	public void TC09_WorkFlowSSV() {
		oTC09_WorkFlowSSV.SSV();
	}

	@Test(enabled = false, description = "Validate SLA")
	public void TC10_validateSLA() {
		oTC10_validateSLA.validateSLA();
	}

	@Test(enabled = false, description = "Validate End To End Flows in Different Browsers")
	public void TC11_E2EFlowInMultiBrowsers() {
		oTC11_E2EFlowInMultiBrowsers.e2eFlowInMultiBrowsers();
	}

	@Test(enabled = false, description = "Validate Skill Based Routing")
	public void TC12_SkillBasedRouting() {
		oTC12_SkillBasedRouting.validateSkillBasedRouting();
	}

	@Test(enabled = false, description = "Validate Get Next Work")
	public void TC13_GetNextWork() {
		oTC13_GetNextWork.validateGNW();
	}

	@Test(enabled = false, description = "Create a case and get it approved by case manager")
	public void TC14_CiscoDemo() throws InterruptedException {
		oTC14_CiscoDemo.ciscoDemo();
	}

	@Test(enabled = true, description = "Create An EndToEnd Flow")
	public void TC15_CiscoDemoTwo() {
		oTC15_CiscoDemoTwo.ciscoDemoTwo();
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
			logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
		}
		driver.close();
		extent.endTest(logger);
	}

	@AfterTest
	public void afterTest() throws ATUTestRecorderException {
		extent.flush();
		extent.close();
		recorder.stop();
	}

	@AfterClass
	public void afterClass() {

	}

	@AfterSuite
	public void afterSuite() {
		TestStats.printTestStats();
		CreateLogger.LOGGER.info("Suite ENDED");
		System.out.println("Suite Ended");
	}

}
