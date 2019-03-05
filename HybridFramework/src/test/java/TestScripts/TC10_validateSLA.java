package TestScripts;

import java.util.concurrent.TimeUnit;

import com.relevantcodes.extentreports.LogStatus;

import LibraryFiles.ReUsableLibrary;
import ObjectRepository.PreInitialDataEntryPage;

public class TC10_validateSLA extends ReUsableLibrary {

	public void validateSLA() {
		try {
			launchApplication();
			Login("Manager", "TC10_validateSLA");
			createNewAssignment();
			try {
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(PreInitialDataEntryPage.btnCancel).click();
			} catch (Exception e) {
				driver.findElement(PreInitialDataEntryPage.btnCancel).click();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			}
			SLA();
			logger.log(LogStatus.INFO, "SLA validation Completed Successfully");
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "SLA validation Failed");
		}

	}

}
