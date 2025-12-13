package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		         features = "src\\test\\resources\\features\\",
		         dryRun=false,
		         tags = "@smoke",
                 glue = "stepsDefnitions",
                 plugin = {"pretty", "html:Reports/HTMLReports.html"},
                 monochrome = true)

public class TestRunner {

}
