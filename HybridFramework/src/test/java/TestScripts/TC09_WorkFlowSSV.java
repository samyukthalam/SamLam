package TestScripts;

import com.relevantcodes.extentreports.LogStatus;

import LibraryFiles.ReUsableLibrary;

public class TC09_WorkFlowSSV extends ReUsableLibrary{

	public void SSV() {
		try {
			launchApplication();
			Login("User", "TC09_WorkFlowSSV");
			serverSideValidations();
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Server Side Validations Failed");
		}
		
	}

}
