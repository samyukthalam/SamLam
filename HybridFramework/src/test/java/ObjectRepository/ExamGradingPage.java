package ObjectRepository;

import org.openqa.selenium.By;

public class ExamGradingPage {
	public static final By selSubject = By.id("Subject");
	public static final By lnkExamThesis = By.xpath("//a[text()='Exam Thesis']");
	public static final By lblCaseID=By.xpath("//*[@id=\"RULE_KEY\"]/div/div/div/div[1]/span");
}
