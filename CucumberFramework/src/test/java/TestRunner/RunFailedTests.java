package TestRunner;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import com.github.mkolisnyk.cucumber.reporting.CucumberResultsOverview;
import com.github.mkolisnyk.cucumber.reporting.CucumberUsageReporting;
import LibraryFiles.CreateLogger;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(strict = false, features = { "@target/rerun.txt" }, glue = { "StepDefinition" }, plugin = { "pretty", "html:target/Failed-Rerun-Reports", "json:target/Failed-Rerun-Reports/Cucumber.json", "junit:target/Failed-Rerun-Reports/Cucumber.xml", "usage:target/Failed-Rerun-Reports/Cucumber-usage.json" })
public class RunFailedTests {
	@BeforeClass
	public static void b4Class() {
		CreateLogger.tCreateLogger();
		CreateLogger.LOGGER.info("Failed Test Execution Log File Created");
		CreateLogger.LOGGER.info("Execution Of Failed Tests Started");
	}

	@AfterClass
	public static void afterClass() throws Exception {
		// Custom Test Report Generation
		CucumberResultsOverview results = new CucumberResultsOverview();
		results.setOutputDirectory("target/Failed-Rerun-Reports");
		results.setOutputName("cucumber-results");
		results.setSourceFile("target/Failed-Rerun-Reports/Cucumber.json");
		results.executeFeaturesOverviewReport();
		CucumberUsageReporting report = new CucumberUsageReporting();
		report.setOutputDirectory("target/Failed-Rerun-Reports");
		report.setJsonUsageFile("target/Failed-Rerun-Reports/Cucumber-usage.json");
		report.executeReport();
		CreateLogger.LOGGER.info("Execution Of Failed Tests Completed");
	}
}
