package TestScripts;

import java.io.IOException;

import LibraryFiles.ReUsableLibrary;

public class TC02_ManagerLogin extends ReUsableLibrary {

	public void ManagerLogin() throws IOException {
		launchApplication();
		Login("Manager","TC02_ManagerLogin");
	
	}

}
