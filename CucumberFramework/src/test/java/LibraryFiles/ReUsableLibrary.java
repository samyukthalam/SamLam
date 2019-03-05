package LibraryFiles;

import java.io.IOException;
import java.lang.reflect.Field;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.DataProvider;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.sun.media.jfxmedia.logging.Logger;

import ObjectRepository.HomePage;
import ObjectRepository.LoginPage;
import ObjectRepository.PendingInitialDataEntryPage;
import ObjectRepository.PreInitialDataEntryPage;
import ObjectRepository.ReportsPage;
import ObjectRepository.SessionLockPage;

public class ReUsableLibrary {
	public static String propFilePath = System.getProperty("user.dir") + "\\GlobalSettings.properties";
	public static WebDriver driver;
	public static int colIndex = 0;
	public static String value;
	private static int waittime = 3000;
	private static int timecount = 0;
	private int maxtimecount = 90000;
	private int maxtimecounter = 120;
	public static ExtentReports extent;
	public static ExtentTest logger;
	private static SimpleDateFormat strScreenShotName = new SimpleDateFormat("MMddyy_HHmmss");
	public static String browser;

	public static void OpenWDInstance() throws IOException {
		try {
			browser = getElementFromPropFile("Browser");
			if (browser.equals("Firefox")) {
				String GeckoDriverPath = getElementFromPropFile("GeckoDriverPath");
				System.setProperty("webdriver.gecko.driver", GeckoDriverPath);
				driver = new FirefoxDriver();
			} else if (browser.equals("IE")) {
				String ieDriverPath = getElementFromPropFile("InternetExplorerDriverPath");
				System.setProperty("webdriver.ie.driver", ieDriverPath);
				driver = new InternetExplorerDriver();
			} else if (browser.equals("Chrome")) {
				String chromeDriverPath = getElementFromPropFile("ChromeDriverPath");
				System.setProperty("webdriver.chrome.driver", chromeDriverPath);
				driver = new ChromeDriver();
			} else if (browser.equals("PhantomJS")) {
				String phantomJSexePath = getElementFromPropFile("PhantomJSPath");
				File file = new File(phantomJSexePath);
				System.setProperty("phantomjs.binary.path", file.getAbsolutePath());
				try {
					driver = new PhantomJSDriver();
				} catch (UnreachableBrowserException e) {
					System.out.println(e);
					driver = new PhantomJSDriver();
				}
			}
			driver.manage().window().maximize();
			System.out.println("Created Driver Instance and Launched Browser: " + browser);
		} catch (Exception e) {
			Assert.fail("Failed To Create Driver Instance " + browser + " " + e.toString());
		}
	}

	public static void launchApplication() {
		CreateLogger.LOGGER.info("Launching Application");
		if (browser != null)
			System.out.println("Launched Browser " + browser + " Successfully ");
		String appURL;
		try {
			appURL = getElementFromPropFile("ApplicationUrl");
			driver.get(appURL);
			customSleep();
		} catch (IOException e) {
			Assert.fail("Failed To Launch Application" + e.toString());
		}
	}

	public static void customSleep() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void custom3Sleep() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void custom6Sleep() {
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getElementFromPropFile(String property) throws IOException {
		FileInputStream file = new FileInputStream(propFilePath);
		Properties prop = new Properties();
		prop.load(file);
		return prop.getProperty(property);

	}

	public static void takeScreenShot(ITestResult result) throws IOException {
		String testCaseName = result.getName();
		File file = null;
		if (result.getStatus() == ITestResult.SUCCESS)
			file = new File(System.getProperty("user.dir") + "\\ScreenShots\\" + testCaseName + "\\Passed\\");
		if (result.getStatus() == ITestResult.FAILURE)
			file = new File(System.getProperty("user.dir") + "\\ScreenShots\\" + testCaseName + "\\Failed\\");
		if (result.getStatus() == ITestResult.SKIP)
			file = new File(System.getProperty("user.dir") + "\\ScreenShots\\" + testCaseName + "\\Skipped\\");
		if (!file.exists()) {
			System.out.println("File created " + file);
			file.mkdir();
		}
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			String screenshotName = testCaseName + strScreenShotName.format(new Date()) + ".png";
			File targetFile = new File(file, screenshotName);
			FileUtils.copyFile(scrFile, targetFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getData(String SheetName, String columnName, String tcName) throws IOException {
		String defDataSheet = getElementFromPropFile("DefaultDataSheet");
		String fileName = System.getProperty("user.dir") + "\\TestData\\" + defDataSheet + ".xlsx";
		String dataSheetName = SheetName;
		String colName = columnName;
		String rowName = tcName;

		List cellDataList = new ArrayList();
		FileInputStream fileInputStream = new FileInputStream(fileName);
		XSSFWorkbook workBook = new XSSFWorkbook(fileInputStream);
		XSSFSheet sheet = workBook.getSheet(dataSheetName);
		Iterator rowIterator = sheet.rowIterator();
		while (rowIterator.hasNext()) {
			XSSFRow row = (XSSFRow) rowIterator.next();
			Iterator iterator = row.cellIterator();
			List cellTempList = new ArrayList();
			while (iterator.hasNext()) {
				XSSFCell cell = (XSSFCell) iterator.next();
				cellTempList.add(cell);
			}
			cellDataList.add(cellTempList);
		}
		// System.out.println(cellDataList);
		for (int i = 0; i < cellDataList.size(); i++) {
			int k = 0;
			List cellTempList = (List) cellDataList.get(i);
			int sizeOfList = cellTempList.size();
			do {
				if (cellTempList.get(k).toString().equals(colName)) {
					// System.out.println(cellTempList.get(k).toString());
					XSSFCell hssfCell = (XSSFCell) cellTempList.get(k);
					colIndex = hssfCell.getColumnIndex();
					// System.out.print(colIndex);
				} else {
					if (cellTempList.get(k).toString().equals(rowName)) {
						// System.out.println(cellTempList.get(k).toString());
						XSSFCell hssfCell = (XSSFCell) cellTempList.get(k);
						// int rowIndex = hssfCell.getRowIndex();
						// System.out.print(rowIndex);
						XSSFRow row = sheet.getRow(i);
						value = cellToString(row.getCell(colIndex));
						// System.out.println("Value of " + colName +
						// " for the test case " + rowName + " is :" + value);
					}
				}
				k++;
			} while (k < sizeOfList);
			System.out.println();
			workBook.close();
		}
		return value;
	}

	public static String cellToString(XSSFCell cell) {

		int type;
		Object result = null;
		type = cell.getCellType();

		switch (type) {
		case XSSFCell.CELL_TYPE_NUMERIC:
			result = BigDecimal.valueOf(cell.getNumericCellValue()).toPlainString();
			break;
		case XSSFCell.CELL_TYPE_STRING:
			result = cell.getStringCellValue();
			break;
		case XSSFCell.CELL_TYPE_BLANK:
			result = "";
			break;
		case XSSFCell.CELL_TYPE_FORMULA:
			result = cell.getStringCellValue();
		}
		return result.toString();
	}

	public static void putData(String SheetName, String columnName, String tcName, String data) throws IOException {
		String defDataSheet = getElementFromPropFile("DefaultDataSheet");
		String fileName = System.getProperty("user.dir") + "\\TestData\\" + defDataSheet + ".xlsx";
		String dataSheetName = SheetName;
		String colName = columnName;
		String rowName = tcName;
		String dataToUpdate = data;
		List cellDataList = new ArrayList();
		FileInputStream fileInputStream = new FileInputStream(fileName);
		XSSFWorkbook workBook = new XSSFWorkbook(fileInputStream);
		XSSFSheet sheet = workBook.getSheet(dataSheetName);
		Iterator rowIterator = sheet.rowIterator();
		while (rowIterator.hasNext()) {
			XSSFRow row = (XSSFRow) rowIterator.next();
			Iterator iterator = row.cellIterator();
			List cellTempList = new ArrayList();
			while (iterator.hasNext()) {
				XSSFCell cell = (XSSFCell) iterator.next();
				cellTempList.add(cell);
			}
			cellDataList.add(cellTempList);
		}
		// System.out.println(cellDataList);
		for (int i = 0; i < cellDataList.size(); i++) {
			int k = 0;
			List cellTempList = (List) cellDataList.get(i);
			int sizeOfList = cellTempList.size();
			do {
				if (cellTempList.get(k).toString().equals(colName)) {
					System.out.println(cellTempList.get(k).toString());
					XSSFCell hssfCell = (XSSFCell) cellTempList.get(k);
					colIndex = hssfCell.getColumnIndex();
					System.out.print(colIndex);
				} else {
					if (cellTempList.get(k).toString().equals(rowName)) {
						System.out.println(cellTempList.get(k).toString());
						XSSFCell hssfCell = (XSSFCell) cellTempList.get(k);
						int rowIndex = hssfCell.getRowIndex();
						System.out.print(rowIndex);
						XSSFRow row = sheet.getRow(i);
						XSSFCell cell = row.createCell(colIndex);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(dataToUpdate);
						String value = cell.getStringCellValue();
						System.out.println("Value updated into " + colName + " for the test case " + rowName + " is :" + value);
						FileOutputStream fileOut = new FileOutputStream(fileName);
						workBook.write(fileOut);
						fileOut.close();
					}
				}
				k++;
			} while (k < sizeOfList);
			System.out.println();
		}
	}

	public static String convertSecondsToHours(Long time) {
		String formatTime = null;
		try {
			System.out.println("time in Seconds ...." + time);

			int hrs = (int) (time / 3600);
			System.out.println("No of Hrs ::" + hrs);

			int mins = (int) (time % 3600) / 60;
			System.out.println("No of Mins ::" + mins);

			int secs = (int) ((time % 3600) % 60);
			System.out.println("No of Secs ::" + secs);

			formatTime = hrs + " Hr " + mins + " Mins " + secs + " Secs";

		} catch (Exception e) {
			System.out.println("Error occured while converting the Time from Seconds to Hours..." + e.getMessage());
		}
		return formatTime;
	}

	private static String timeStamp() {
		return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
	}

	public static void createDir(String dirName) {
		File f = new File(dirName);
		try {
			if (!f.exists()) {
				f.mkdir();
				CreateLogger.LOGGER.info("Directory Created :: " + dirName);
			}
		} catch (Throwable e) {
			CreateLogger.LOGGER.error("Unable to create directory  '" + dirName + "'");
		}
	}

	public void customType(String Locator, String Text) {
		int count = 0;
		driver.manage().timeouts().implicitlyWait(300, TimeUnit.MILLISECONDS);
		try {
			int fieldLength = Text.length();
			String actualText;
			if (driver.findElement(By.name(Locator)).isDisplayed() && fieldLength != 0) {
				do {
					driver.findElement(By.name(Locator)).clear();
					driver.findElement(By.name(Locator)).sendKeys(Text);
					actualText = driver.findElement(By.name(Locator)).getAttribute("value");
					count = count + 1;
				} while (!Text.equals(actualText) && count != 10);
			}
		} catch (Exception e) {
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		}
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
	}

	public void comboCustomType(String identifier, String Locator, String Text) {
		int count = 0;
		customSleepOne();
		if (identifier.equals("name")) {
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			try {
				int fieldLength = Text.length();
				String actualText;
				if (driver.findElement(By.name(Locator)).isDisplayed() && fieldLength != 0) {
					do {
						driver.findElement(By.name(Locator)).clear();
						driver.findElement(By.name(Locator)).sendKeys(Text);
						actualText = driver.findElement(By.name(Locator)).getAttribute("value");
						count = count + 1;
					} while (!Text.equals(actualText) && count != 10);
					driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
					driver.findElement(By.name(Locator)).sendKeys(Keys.RETURN);
					customSleep();
					driver.findElement(By.xpath("//nobr[text(),'" + Text + "']")).click();
				}
			} catch (Exception e) {
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			}
		} else if (identifier.equals("xpath")) {
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			try {
				int fieldLength = Text.length();
				String actualText;
				if (driver.findElement(By.xpath(Locator)).isDisplayed() && fieldLength != 0) {
					do {
						driver.findElement(By.xpath(Locator)).clear();
						driver.findElement(By.xpath(Locator)).sendKeys(Text);
						actualText = driver.findElement(By.name(Locator)).getAttribute("value");
						count = count + 1;
					} while (!Text.equals(actualText) && count != 10);
					driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
					customSleep();
					driver.findElement(By.xpath("//nobr[text(),'" + Text + "']")).click();
				}
			} catch (Exception e) {
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			}
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		}
	}

	public void customType(String locatorType, String Locator, String Text) throws Exception {
		driver.manage().timeouts().implicitlyWait(300, TimeUnit.MILLISECONDS);
		if (locatorType.contains("name")) {
			try {
				if (driver.findElement(By.name(Locator)).isDisplayed()) {
					driver.findElement(By.name(Locator)).clear();
					for (int i = 0; i < Text.length(); i++) {
						try {
							driver.findElement(By.name(Locator)).sendKeys(Character.toString(Text.charAt(i)));
							try {
								Thread.sleep(40);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						} catch (Exception e) {
							System.out.println("Continue Typing");
						}
					}
				}
			} catch (Exception e) {

				throw new Exception("Error in entering data in " + Locator);
			}
		} else {
			try {
				int fieldLength = Text.length();
				if (driver.findElement(By.xpath(Locator)).isDisplayed() && fieldLength != 0) {
					driver.findElement(By.xpath(Locator)).clear();
					for (int i = 0; i < Text.length(); i++) {
						try {
							driver.findElement(By.xpath(Locator)).sendKeys(Character.toString(Text.charAt(i)));
							try {
								Thread.sleep(40);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						} catch (Exception e) {
							System.out.println("Continue Typing");
						}
					}
				}
			} catch (Exception e) {
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				throw new Exception("Error in entering data");
			}
		}
		driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
	}

	public void customType(String locatorType, String Locator, String Text, int waitTime) throws Exception {
		driver.manage().timeouts().implicitlyWait(300, TimeUnit.MILLISECONDS);
		if (locatorType.contains("name")) {
			try {
				if (driver.findElement(By.name(Locator)).isDisplayed()) {
					driver.findElement(By.name(Locator)).clear();
					for (int i = 0; i < Text.length(); i++) {
						try {
							driver.findElement(By.name(Locator)).sendKeys(Character.toString(Text.charAt(i)));
							try {
								Thread.sleep(waitTime);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						} catch (Exception e) {
							System.out.println("Continue Typing");
						}
					}
				}
			} catch (Exception e) {

				throw new Exception("Error in entering data in " + Locator);
			}
		} else {
			try {
				if (driver.findElement(By.xpath(Locator)).isDisplayed()) {
					driver.findElement(By.xpath(Locator)).clear();
					for (int i = 0; i < Text.length(); i++) {
						try {

							driver.findElement(By.xpath(Locator)).sendKeys(Character.toString(Text.charAt(i)));
							try {
								Thread.sleep(waitTime);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						} catch (Exception e) {
							System.out.println("Continue Typing");
						}
					}
				}
			} catch (Exception e) {

				throw new Exception("Error in entering data");
			}
		}
		customSleepOne();
		driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
	}

	public void customSleepOne() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void custom9Sleep() {
		try {
			Thread.sleep(9000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getElementXPath(WebDriver driver, WebElement element) {
		String javaScript = "function getElementXPath(elt){" + "var path = \"\";" + "for (; elt && elt.nodeType == 1; elt = elt.parentNode){" + "idx = getElementIdx(elt);" + "xname = elt.tagName;" + "if (idx > 1){" + "xname += \"[\" + idx + \"]\";" + "}" + "path = \"/\" + xname + path;" + "}" + "return path;" + "}" + "function getElementIdx(elt){" + "var count = 1;" + "for (var sib = elt.previousSibling; sib ; sib = sib.previousSibling){" + "if(sib.nodeType == 1 && sib.tagName == elt.tagName){"
				+ "count++;" + "}" + "}" + "return count;" + "}" + "return getElementXPath(arguments[0]).toLowerCase();";

		return (String) ((JavascriptExecutor) driver).executeScript(javaScript, element);
	}

	public void webdriverDynamicWait(String findby, final String Element) throws Exception {
		if (findby.equals("id")) {
			try {
				WebElement DynamicElement = (new WebDriverWait(driver, 180)).until(new ExpectedCondition<WebElement>() {
					public WebElement apply(WebDriver d) {
						return d.findElement(By.id(Element));

					}
				});
			} catch (Exception e) {
				throw new Exception("Element " + Element + " is not found");
			}
		} else if (findby.equals("name")) {
			try {
				WebElement DynamicElement = (new WebDriverWait(driver, 180)).until(new ExpectedCondition<WebElement>() {
					public WebElement apply(WebDriver d) {
						return d.findElement(By.name(Element));

					}
				});
			} catch (Exception e) {
				throw new Exception("Element " + Element + " is not found");
			}

		} else if (findby.equals("xpath")) {
			try {
				WebElement DynamicElement = (new WebDriverWait(driver, 180)).until(new ExpectedCondition<WebElement>() {
					public WebElement apply(WebDriver d) {
						return d.findElement(By.xpath(Element));

					}
				});
			} catch (Exception e) {
				throw new Exception("Element " + Element + " is not found");
			}

		} else if (findby.equals("css")) {
			try {
				WebElement DynamicElement = (new WebDriverWait(driver, 60)).until(new ExpectedCondition<WebElement>() {
					public WebElement apply(WebDriver d) {
						return d.findElement(By.cssSelector(Element));

					}
				});
			} catch (Exception e) {
				throw new Exception("Element " + Element + " is not found");
			}
		} else if (findby.equals("link")) {
			try {
				WebElement DynamicElement = (new WebDriverWait(driver, 180)).until(new ExpectedCondition<WebElement>() {
					public WebElement apply(WebDriver d) {
						return d.findElement(By.linkText(Element));

					}
				});
			} catch (Exception e) {
				throw new Exception("Element " + Element + " is not found");
			}

		} else
			System.out.println("data not entered");
	}

	public void customWait(String Type, String Element) {
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		if (Type.equals("name")) {
			do {
				try {
					Thread.sleep(waittime);
					timecount = timecount + waittime;
					System.out.println("current waitime for " + Element + " is " + timecount);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} while (!driver.findElement(By.name(Element)).isDisplayed() && timecount != maxtimecount);
			timecount = 0;
		}
		if (Type.equals("id")) {
			do {
				try {
					Thread.sleep(waittime);
					timecount = timecount + waittime;
					System.out.println("current waitime for " + Element + " is " + timecount);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} while (!driver.findElement(By.id(Element)).isDisplayed() && timecount != maxtimecount);
			timecount = 0;
		}
		if (Type.equals("xpath")) {
			do {
				try {
					Thread.sleep(waittime);
					timecount = timecount + waittime;
					System.out.println("current waitime for " + Element + " is " + timecount);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} while (!driver.findElement(By.xpath(Element)).isDisplayed() && timecount != maxtimecount);
			timecount = 0;
		}
		if (Type.equals("text")) {
			String pageText = null;
			do {
				try {
					Thread.sleep(waittime);
					timecount = timecount + waittime;
					pageText = driver.findElement(By.tagName("body")).getText();
					System.out.println("current waitime for " + Element + " is " + timecount);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} while (!pageText.contains(Element) && timecount != maxtimecount);
			timecount = 0;
		}
	}

	public void dynamicWait(long waitTime, String Type, String Element) {
		if (Type.equals("name")) {
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.name(Element)));
		} else if (Type.equals("id")) {
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(Element)));
		} else if (Type.equals("xpath")) {
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Element)));
		} else if (Type.equals("text")) {
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			boolean element = wait.until(ExpectedConditions.textToBePresentInElement(By.tagName("td"), Element));
		} else if (Type.equals("div")) {
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			boolean element = wait.until(ExpectedConditions.textToBePresentInElement(By.tagName("div"), Element));
		}
	}

	public void customWaitForElement(String Type, String Element) {
		boolean isElementDisplayed = false;
		int counter = 0;
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		if (Type.equals("name")) {
			do {
				try {
					customSleep();
					driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
					if (driver.findElement(By.name(Element)).isDisplayed())
						isElementDisplayed = true;
				} catch (Exception e) {
					isElementDisplayed = false;
					counter = counter + 1;
				}
			} while (!isElementDisplayed && counter != maxtimecounter);
			counter = 0;
			isElementDisplayed = false;
		} else if (Type.equals("id")) {
			do {
				try {
					customSleep();
					driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
					if (driver.findElement(By.id(Element)).isDisplayed())
						isElementDisplayed = true;
				} catch (Exception e) {
					isElementDisplayed = false;
					counter = counter + 1;
				}
			} while (!isElementDisplayed && counter != maxtimecounter);
			counter = 0;
			isElementDisplayed = false;
		} else if (Type.equals("xpath")) {
			do {
				try {
					customSleep();
					driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
					if (driver.findElement(By.xpath(Element)).isDisplayed())
						isElementDisplayed = true;
				} catch (Exception e) {
					isElementDisplayed = false;
					counter = counter + 1;
				}
			} while (!isElementDisplayed && counter != maxtimecounter);
			counter = 0;
			isElementDisplayed = false;
		}
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
	}

	public void selectDropDownValue(String dropDown, String xPath, String Value) {
		driver.findElement(By.xpath(dropDown)).click();
		int count = 0;
		int a = xPath.indexOf("[i]");
		String newxPath = xPath.substring(0, a);
		String newxPath1 = xPath.substring(a + 2, xPath.length());
		int countOne = driver.findElements(By.xpath(newxPath)).size();

		for (int i = 1; i <= countOne; i++) {
			String actualValue = driver.findElement(By.xpath(newxPath + "[" + i + newxPath1)).getText().trim();
			if (!actualValue.isEmpty()) {
				if (actualValue.trim().toLowerCase().equals(Value.trim().toLowerCase())) {
					driver.findElement(By.xpath(newxPath + "[" + i + newxPath1)).click();
					count = count + 1;
					break;
				}
			}
		}
		if (count == 0)
			System.out.println("Error in selecting dropdown value: " + Value);
		if (count == 1)
			System.out.println(Value + " is selected as dropdown value");
	}

	public String getCurrentTime(String timeZone) {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		TimeZone T1 = TimeZone.getTimeZone(timeZone);
		dateFormat.setTimeZone(T1);
		String currentdate = dateFormat.format(date);
		return currentdate;
	}

	public String getCurrentDate(String timeZone) {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");
		TimeZone T1 = TimeZone.getTimeZone(timeZone);
		dateFormat.setTimeZone(T1);
		String currentdate = dateFormat.format(date);
		return currentdate;
	}

	public static void typeCharacter(Robot robot, String letter) {
		for (int i = 0; i < letter.length(); i++) {
			try {
				boolean upperCase = Character.isUpperCase(letter.charAt(i));
				String KeyVal = Character.toString(letter.charAt(i));
				String variableName = "VK_" + KeyVal.toUpperCase();
				Class clazz = KeyEvent.class;
				Field field = clazz.getField(variableName);
				int keyCode = field.getInt(null);
				robot.delay(500);
				if (upperCase)
					robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(keyCode);
				robot.keyRelease(keyCode);
				if (upperCase)
					robot.keyRelease(KeyEvent.VK_SHIFT);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	public void mouseMove() {
		Random r = new Random();
		int a = r.nextInt(800);
		int b = r.nextInt(700);
		System.out.println(a);
		System.out.println(b);
		try {
			Robot robot = new Robot();
			robot.mouseMove(a, b);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void clickElement(String Element, String Text, String ElementName) throws Exception {
		try {
			String pageText;
			int count = 0;
			do {
				driver.findElement(By.xpath(Element)).click();
				customSleep();
				customSleep();
				count = count + 1;
				pageText = driver.findElement(By.tagName("body")).getText();

			} while (!pageText.toLowerCase().contains(Text.trim().toLowerCase()) && count != 40);
			System.out.println(ElementName + " is clicked");
		} catch (Exception e) {
			throw new Exception("Error in entering clicking on" + ElementName);
		}
	}

	public boolean isDateBetween(String Stdate, String EndDt, String TestDt) throws ParseException {
		SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdfDate1 = new SimpleDateFormat("dd/MM/yyyy");
		Date DtStrt = sdfDate.parse(Stdate);
		Date DtEnd = sdfDate.parse(EndDt);
		Date DtTest = sdfDate.parse(TestDt);
		if ((DtStrt.compareTo(DtTest) <= 0) && (DtTest.compareTo(DtEnd) <= 0))
			return true;
		else
			return false;
	}

	public boolean isDateBetweenParsing(String Stdate, String EndDt, String TestDt) throws ParseException {
		SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MMM/yyyy");
		SimpleDateFormat sdfDate1 = new SimpleDateFormat("dd/MM/yyyy");
		Date DtStrt = sdfDate.parse(Stdate);
		Date DtEnd = sdfDate.parse(EndDt);
		Date DtTest = sdfDate1.parse(TestDt);
		if ((DtStrt.compareTo(DtTest) <= 0) && (DtTest.compareTo(DtEnd) <= 0))
			return true;
		else
			return false;
	}

	public String subtractDate(String timeZone, int minusDate) {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		TimeZone T1 = TimeZone.getTimeZone(timeZone);
		dateFormat.setTimeZone(T1);
		Date dateBefore = new Date(date.getTime() - minusDate * 24 * 3600 * 1000);
		String previousDate = dateFormat.format(dateBefore);
		return previousDate;
	}

	public void highlightElement(WebElement element, WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		String bgcolor = element.getCssValue("backgroundColor");
		changeColor(element, js);
		// changeColor(bgcolor, element, js);
	}

	public void changeColor(WebElement element, JavascriptExecutor js) {
		js.executeScript("arguments[0].style.border='2px solid red'", element);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
	}

	public void waitForElement(By by) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 120);
			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
		} catch (Exception e) {
		}
	}

	public void waitForElementToBeClickable(By by) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 120);
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
		} catch (Exception e) {
		}
	}

	public void scrollDown() {
		try {
			EventFiringWebDriver driver1 = new EventFiringWebDriver(driver);
			driver1.executeAsyncScript("scroll(0,2000)");
		} catch (Exception e) {
		}
	}

	public void validatePlaceHolderText(String pageName, String fieldName, String txtXPath, String expText) throws Exception {
		try {
			String getHelpText = driver.findElement(By.xpath(txtXPath)).getAttribute("placeholder");
			if (getHelpText.equals(expText))
				System.out.println("Place Holder Text is displayed as expected for the " + fieldName + " field in " + pageName);
			else
				System.out.println("Place Holder Text is NOT displayed as expected for the " + fieldName + " field in " + pageName);
		} catch (Exception e) {
			throw new Exception("Error in validating Default Help Text displayed in all the fields of different pages");
		}
	}

	public void Login(String LoginType, String TCName) {
		try {
			String userName = null, password = null;
			if (LoginType.equals("User")) {
				userName = getData("TestDataSheet", "UserName", TCName);
				password = getData("TestDataSheet", "Password", TCName);
			} else if (LoginType.equals("Manager")) {
				userName = getData("TestDataSheet", "ManagerUserName", TCName);
				password = getData("TestDataSheet", "ManagerPassword", TCName);
			} else if (LoginType.equals("Admin")) {
				userName = getData("TestDataSheet", "AdminUserName", TCName);
				password = getData("TestDataSheet", "AdminPassword", TCName);
			}
			driver.findElement(LoginPage.txtUserName).sendKeys(userName);
			driver.findElement(LoginPage.txtPassword).sendKeys(password);
			driver.findElement(LoginPage.btnLogin).click();
		} catch (Exception e) {
			Assert.fail("Application Login Failed" + e.toString());
		}
	}

	public void LoginDP(String userName, String password) {
		try {
			driver.findElement(LoginPage.txtUserName).sendKeys(userName);
			driver.findElement(LoginPage.txtPassword).sendKeys(password);
			driver.findElement(LoginPage.btnLogin).click();
			WebDriverWait wait = new WebDriverWait(driver, 60);
			if (userName.contains("admin")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(HomePage.lnkResources));
				Assert.assertEquals("Resources", driver.findElement(HomePage.lnkResources).getText());
			} else {
				wait.until(ExpectedConditions.presenceOfElementLocated(HomePage.txtLoanCC));
				Assert.assertEquals("LOAN CC", driver.findElement(HomePage.txtLoanCC).getText());
			}
		} catch (Exception e) {
			Assert.fail("Application Login Failed" + e.toString());
		}
	}

	public void LogOut() {
		try {
			driver.findElement(HomePage.btnLogOut).click();
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(HomePage.lnkLogOff));
			driver.findElement(HomePage.lnkLogOff).click();
			custom3Sleep();
			Assert.assertEquals("Log in", driver.findElement(LoginPage.btnLogin).getText());
		} catch (Exception e) {
			Assert.fail("Application LogOut Failed" + e.toString());
		}
	}

	public static void releaseLock() {
		try {
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			if (driver.findElement(SessionLockPage.btnSessionLock).isDisplayed())
				driver.findElement(SessionLockPage.btnSessionLock).click();
			customSleep();
		} catch (Exception e) {
			System.out.println("No Lock to release");
		}
	}

	@DataProvider(name = "Logins")
	public static Object[][] credentials() throws IOException {
		String userName1 = getData("TestDataSheet", "UserName", "TC04_LoginWithDiffUsers");
		String password1 = getData("TestDataSheet", "Password", "TC04_LoginWithDiffUsers");
		String userName2 = getData("TestDataSheet", "ManagerUserName", "TC04_LoginWithDiffUsers");
		String password2 = getData("TestDataSheet", "ManagerPassword", "TC04_LoginWithDiffUsers");
		String userName3 = getData("TestDataSheet", "AdminUserName", "TC04_LoginWithDiffUsers");
		String password3 = getData("TestDataSheet", "AdminPassword", "TC04_LoginWithDiffUsers");
		return new Object[][] { { userName1, password1 }, { userName2, password2 }, { userName3, password3 } };
	}

	@DataProvider(name = "DiffLoanAmounts")
	public static Object[][] DiffLoanAmounts() throws IOException {
		String loanAmount1 = getData("TestDataSheet", "LoanAmount", "TC06_BusinessRulesAndDecisioning");
		String loanType1 = getData("TestDataSheet", "LoanType", "TC06_BusinessRulesAndDecisioning");
		String decision1 = getData("TestDataSheet", "Decision1", "TC06_BusinessRulesAndDecisioning");
		String decision2 = getData("TestDataSheet", "Decision2", "TC06_BusinessRulesAndDecisioning");
		String decision3 = getData("TestDataSheet", "Decision3", "TC06_BusinessRulesAndDecisioning");

		String loanAmount2 = getData("TestDataSheet", "LoanAmount", "TC06_BusinessRulesAndDecisioning2");
		String loanType2 = getData("TestDataSheet", "LoanType", "TC06_BusinessRulesAndDecisioning2");
		String decision11 = getData("TestDataSheet", "Decision1", "TC06_BusinessRulesAndDecisioning2");
		String decision22 = getData("TestDataSheet", "Decision2", "TC06_BusinessRulesAndDecisioning2");
		String decision33 = getData("TestDataSheet", "Decision3", "TC06_BusinessRulesAndDecisioning2");
		return new Object[][] { { loanAmount1, loanType1, decision1, decision2, decision3 }, { loanAmount2, loanType2, decision11, decision22, decision33 } };
	}

	public void createACetegory(String repCatName, String repCatDesc, String repCatType) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			driver.findElement(ReportsPage.btnAddCategory).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(ReportsPage.lblCreateNewCat));
			driver.findElement(ReportsPage.txtCategoryName).sendKeys(repCatName);
			int i = 1;
			do {
				try {
					driver.findElement(ReportsPage.txtCategoryDesc).clear();
					Thread.sleep(200);
					driver.findElement(ReportsPage.txtCategoryDesc).sendKeys(repCatDesc);
					i++;
					Thread.sleep(200);
				} catch (Exception e) {
				}
			} while (i <= 2);
			Thread.sleep(1000);
			int j = 1;
			do {
				try {
					driver.findElement(ReportsPage.selCategoryType).click();
					if (repCatType.equals("Private"))
						driver.findElement(ReportsPage.optCatTypePrivate).click();
					j++;
					Thread.sleep(200);
				} catch (Exception e) {
				}
			} while (j <= 2);
			try {
				driver.findElement(ReportsPage.btnSubmit).click();
			} catch (Exception e) {
				driver.findElement(ReportsPage.btnSubmit).click();
			}
			customSleep();
			String repCatDescActual = driver.findElement(By.linkText(repCatDesc)).getText();
			Assert.assertEquals(repCatDesc, repCatDescActual);
		} catch (Exception e) {
			Assert.fail("Report Category Creation Failed " + e.toString());
		}
	}

	public void createAReport(String caseType, String reportType, String reportTitle, String repColumn, String repColumnValue, String repCatName, String repCatDesc) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			driver.findElement(ReportsPage.btnAddReport).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(ReportsPage.lblCreateNewReport));
			Select sel2 = new Select(driver.findElement(ReportsPage.selCaseType));
			sel2.selectByVisibleText(caseType);
			Thread.sleep(500);
			int k = 1;
			do {
				Select sel3 = new Select(driver.findElement(ReportsPage.selReportType));
				Thread.sleep(500);
				if (reportType.equals("List"))
					sel3.selectByValue(ReportsPage.listRepTypeValue);
				else if (reportType.equals("Summary"))
					sel3.selectByValue(ReportsPage.summaryRepTypeValue);
				k++;
			} while (k == 2);

			driver.findElement(ReportsPage.btnSubmit).click();
			customSleep();
			driver.switchTo().defaultContent();
			driver.switchTo().frame("PegaGadget1Ifr");
			wait.until(ExpectedConditions.presenceOfElementLocated(ReportsPage.lblDataExplorer));
			driver.findElement(ReportsPage.txtReportTitle).clear();
			driver.findElement(ReportsPage.txtReportTitle).sendKeys(reportTitle);
			Actions action = new Actions(driver);
			WebElement target = driver.findElement(ReportsPage.lblDropColumn);
			WebElement source = driver.findElement(By.xpath("//label[contains(text(),'" + repColumn + "')]"));
			action.dragAndDrop(source, target).perform();
			customSleep();
			driver.findElement(ReportsPage.txtRepColValue).sendKeys(repColumnValue);
			driver.switchTo().defaultContent();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='" + repColumnValue + "']")));
			driver.findElement(By.xpath("//span[text()='" + repColumnValue + "']")).click();
			driver.switchTo().frame("PegaGadget1Ifr");
			driver.findElement(ReportsPage.btnApplyChanges).click();
			customSleep();
			try {
				driver.findElement(ReportsPage.btnDoneEditing).click();
			} catch (Exception e) {
				driver.findElement(ReportsPage.btnDoneEditing).click();
			}
			wait.until(ExpectedConditions.presenceOfElementLocated(ReportsPage.lblSaveReportAs));
			Select selRepCat = new Select(driver.findElement(ReportsPage.selCategory));
			try {
				selRepCat.selectByVisibleText(repCatDesc);
			} catch (Exception e) {
				selRepCat.selectByVisibleText(repCatDesc);
			}
			customSleep();
			try {
				driver.findElement(ReportsPage.btnSubmit).click();
			} catch (Exception e) {
				driver.findElement(ReportsPage.btnSubmit).click();
			}
			wait.until(ExpectedConditions.visibilityOfElementLocated(ReportsPage.btnCloseReport));
			try {
				action.moveToElement(driver.findElement(ReportsPage.btnCloseReport)).perform();
				driver.findElement(ReportsPage.btnCloseReport).click();
			} catch (Exception e) {
				action.moveToElement(driver.findElement(ReportsPage.btnCloseReport)).perform();
				driver.findElement(ReportsPage.btnCloseReport).click();
			}
			custom3Sleep();
			driver.switchTo().defaultContent();
			driver.switchTo().frame("PegaGadget0Ifr");
			custom3Sleep();
			driver.findElement(By.linkText(repCatDesc)).click();
			customSleep();
			Assert.assertEquals(reportTitle, driver.findElement(By.linkText(reportTitle)).getText());
		} catch (Exception e) {
			Assert.fail("Report Creation Failed " + e.toString());
		}
	}

	public void clientSideValidations() {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			driver.findElement(HomePage.lnkFirstIniDataEntry).click();
			custom3Sleep();
			releaseLock();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			try {
				if (driver.findElement(PendingInitialDataEntryPage.btnEdit).isDisplayed())
					driver.findElement(PendingInitialDataEntryPage.btnEdit).click();
				try {
					if (driver.findElement(PendingInitialDataEntryPage.btnEdit).isDisplayed())
						driver.findElement(PendingInitialDataEntryPage.btnEdit).click();
				} catch (Exception e) {
					System.out.println("Edit button is clicked");
				}
			} catch (Exception e) {
				System.out.println("Edit button not displayed");
			}
			customSleep();
			try {
				if (driver.findElement(PreInitialDataEntryPage.btnSubmit).isDisplayed()) {
					driver.findElement(PreInitialDataEntryPage.btnSubmit).click();
					wait.until(ExpectedConditions.presenceOfElementLocated(PendingInitialDataEntryPage.txtFirstName));
				}
				try {
					driver.findElement(PreInitialDataEntryPage.btnSubmit).click();
					wait.until(ExpectedConditions.presenceOfElementLocated(PendingInitialDataEntryPage.txtFirstName));
				} catch (Exception e) {
					System.out.println("Submit button is clicked");
				}
			} catch (Exception e) {
				System.out.println("Submit button not displayed");
			}
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			custom3Sleep();
			driver.findElement(PendingInitialDataEntryPage.btnContinue).click();
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
		} catch (Exception e) {
			Assert.fail("Client Side Validations Failed" + e.toString());
		}
	}

	public void serverSideValidations() {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			driver.findElement(HomePage.lnkFirstIniDataEntry).click();
			custom3Sleep();
			releaseLock();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			try {
				if (driver.findElement(PendingInitialDataEntryPage.btnEdit).isDisplayed())
					driver.findElement(PendingInitialDataEntryPage.btnEdit).click();
				try {
					if (driver.findElement(PendingInitialDataEntryPage.btnEdit).isDisplayed())
						driver.findElement(PendingInitialDataEntryPage.btnEdit).click();
				} catch (Exception e) {
					System.out.println("Edit button is clicked");
				}
			} catch (Exception e) {
				System.out.println("Edit button not displayed");
			}
			customSleep();
			try {
				if (driver.findElement(PreInitialDataEntryPage.btnSubmit).isDisplayed()) {
					driver.findElement(PreInitialDataEntryPage.btnSubmit).click();
					wait.until(ExpectedConditions.presenceOfElementLocated(PendingInitialDataEntryPage.txtFirstName));
				}
				try {
					driver.findElement(PreInitialDataEntryPage.btnSubmit).click();
					wait.until(ExpectedConditions.presenceOfElementLocated(PendingInitialDataEntryPage.txtFirstName));
				} catch (Exception e) {
					System.out.println("Submit button is clicked");
				}
			} catch (Exception e) {
				System.out.println("Submit button not displayed");
			}
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			custom3Sleep();
			String firstName = getData("TestDataSheet", "FirstName", "TC09_WorkFlowSSV");
			String lastName = getData("TestDataSheet", "LastName", "TC09_WorkFlowSSV");
			String gender = getData("TestDataSheet", "Gender", "TC09_WorkFlowSSV");
			String mobile = getData("TestDataSheet", "Mobile", "TC09_WorkFlowSSV");
			String email = getData("TestDataSheet", "Email", "TC09_WorkFlowSSV");
			String nik = getData("TestDataSheet", "NIK", "TC09_WorkFlowSSV");
			String country = getData("TestDataSheet", "Country", "TC09_WorkFlowSSV");
			String pin = getData("TestDataSheet", "PIN", "TC09_WorkFlowSSV");

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
			custom3Sleep();
			String erroMessage = driver.findElement(PendingInitialDataEntryPage.lblPlsEnterValidNIK).getText();
			Assert.assertEquals("Please enter a valid NIK", erroMessage);
		} catch (Exception e) {
			Assert.fail("Server Side Validations Failed" + e.toString());
		}
	}

	public void createNewAssignment() {
		try {
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
		} catch (Exception e) {
			Assert.fail("New Assignment Creation Failed" + e.toString());
		}
	}

	public void SLA() {
		try {
			String slaAction = getData("TestDataSheet", "SLAAction", "TC10_validateSLA");
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
				String text = driver.findElement(PreInitialDataEntryPage.tabApproval).getText();
				String color = driver.findElement(PreInitialDataEntryPage.tabApproval2).getCssValue("background-color");
				Assert.assertTrue(color.contains("0, 98, 230") && text.equals("Approval"));
			}
		} catch (Exception e) {
			Assert.fail("Approval state did NOT change to Blue Color" + e.toString());
		}
	}

}