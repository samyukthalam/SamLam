package TestRunner;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.github.mkolisnyk.cucumber.reporting.CucumberResultsOverview;
import com.github.mkolisnyk.cucumber.reporting.CucumberUsageReporting;

import LibraryFiles.CreateLogger;
import LibraryFiles.ReUsableLibrary;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "FeatureFiles/Login.feature" }, tags = {}, glue = { "StepDefinition" }, plugin = { "pretty", "html:target/cucumber-reports", "json:target/cucumber-reports/Cucumber.json", "junit:target/cucumber-reports/Cucumber.xml", "usage:target/cucumber-reports/Cucumber-usage.json", "rerun:target/rerun.txt" }, monochrome = true)
public class TestRunner extends ReUsableLibrary {

	@BeforeClass
	public static void b4Class() {
		CreateLogger.tCreateLogger();
		CreateLogger.LOGGER.info("Log File Created");
		CreateLogger.LOGGER.info("Suite Execution Started");
	}

	@AfterClass
	public static void afterClass() throws Exception {
		// Custom Test Report Generation
		CucumberResultsOverview results = new CucumberResultsOverview();
		results.setOutputDirectory("target/cucumber-reports");
		results.setOutputName("cucumber-results");
		results.setSourceFile("target/cucumber-reports/Cucumber.json");
		results.executeFeaturesOverviewReport();
		CucumberUsageReporting report = new CucumberUsageReporting();
		report.setOutputDirectory("target/cucumber-reports");
		report.setJsonUsageFile("target/cucumber-reports/Cucumber-usage.json");
		report.executeReport();
		CreateLogger.LOGGER.info("Suite Execution Completed");
	}

}

//tags = {"@SmokeTest, @RegressionTest"} => OR
//tags = {"@SmokeTest", "@RegressionTest"} => AND
//tags = {"@SmokeTest", "~@RegressionTest"} -> All tagged as smoketest but not regressiontest
//tags = {"~@SmokeTest", "~@RegressionTest"} -> All which are not  tagged at all 
//tags = { "@IE,@Firefox,@Chrome" }
