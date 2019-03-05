package TestScripts;

import com.relevantcodes.extentreports.LogStatus;

import LibraryFiles.ReUsableLibrary;

public class TC08_WorkFlowCSV extends ReUsableLibrary {

	public void CSV() {
		try {
			launchApplication();
			Login("User", "TC08_WorkFlowCSV");
			clientSideValidations();
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Client Side Validations Failed");
		}
	}
}
