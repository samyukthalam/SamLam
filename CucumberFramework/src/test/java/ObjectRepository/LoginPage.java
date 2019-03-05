package ObjectRepository;

import org.openqa.selenium.By;

public class LoginPage {
	public static final By txtUserName = By.id("txtUserID");
	public static final By txtPassword = By.id("txtPassword");
	public static final By btnLogin = By.className("loginButtonText");
	public static final By lblInfoEnteredIsIncorrect=By.id("error");
}
