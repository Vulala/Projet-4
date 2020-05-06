package cucumber.steps;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.safetynet.safetynetalerts.SafetyNetAlertsApplication;

import io.cucumber.spring.CucumberContextConfiguration;

@Ignore
@CucumberContextConfiguration // Allow the other class to use this class as setup
@SpringBootTest(classes = SafetyNetAlertsApplication.class)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class CucumberStepsContextConfiguration {
    
}