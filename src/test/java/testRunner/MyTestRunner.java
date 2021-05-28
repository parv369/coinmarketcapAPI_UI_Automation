package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/features/"},
        glue = {"stepdefinitions", "hooks"},
        tags = "@coinmarketcap",
        plugin = {"pretty",
                "timeline: test-output-thread",
                "rerun: target/failedrerun.txt"
        }
)

public class MyTestRunner {

}
