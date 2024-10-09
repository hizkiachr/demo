package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

//29. perlu runwith
@RunWith(Cucumber.class)
@CucumberOptions(
    //featurenya pada path Features
    features = "C:\\Users\\fathi\\Documents\\Automation\\Test\\demo\\src\\test\\resource\\features\\Login", 
    //lalu di glue dengan package stepDefinitions
    glue = "stepDefinitions"
)

//30. lalu kita coba run lewat Runner
public class TestRunner {
}
