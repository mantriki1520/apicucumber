import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src\\test\\resources\\FeatureFiles\\"
        , glue = {"org.stepdefinitions"},
        tags = "@VCT-420",
        plugin = {"pretty", "html:test-output", "json:target/cucumber/cucumber.json", "html:target/cucumber-html-report.html", "rerun:target/failedrerun.txt"}
)


public class TestRunner {


}
