package cucumber;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/ressources/features", plugin = { "pretty", "html:target/html-cucumber-report" })
public class IndexIT {
    
    // Integration test to fulfill the index.feature file.
    
}