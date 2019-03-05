package TestScripts;

import java.io.IOException;

import com.relevantcodes.extentreports.LogStatus;

import LibraryFiles.ReUsableLibrary;
import junit.framework.Assert;

public class TC04_LoginWithDiffUsers extends ReUsableLibrary {

	public void LoginWithDiffUsers(String userName, String password) throws IOException {
		try {
			launchApplication();
			LoginDP(userName, password);
			LogOut();
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Logging in With Different Users Failed");
			Assert.fail("Logging in With Different Users Failed" + e.toString());
		}
	}

}
