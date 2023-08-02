package stepDefinitions.base;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
//import stepDefinitions.ApiPlace_Steps;
//import stepDefinitions.GraphQL_Test_Steps;
import java.io.IOException;
import java.sql.Timestamp;
import static driver.DriverFactory.cleanupDriver;
import static driver.DriverFactory.getDriver;

public class Hooks {

    @Before("~@Api")
    public void setup() {
        getDriver();
    }
//    @Before("@DeletePlace")
//    public void beforeScenario() throws IOException
//    {		//execute this code only when place id is null
//        //write a code that will give you place id
//
////        StepDefination m =new StepDefination();
//        ApiPlace_Steps m = new ApiPlace_Steps();
//        if(ApiPlace_Steps.place_id ==null)
//        {
//
//            m.add_Place_Payload_with("Shetty", "French", "Asia");
//            m.user_calls_with_http_request("AddPlaceAPI", "POST");
//            m.verify_place_Id_created_maps_to_using("Shetty", "getPlaceAPI");
//        }
//
//
//
//    }
    @AfterStep
    public void captureExceptionImage(Scenario scenario) {
        if (scenario.isFailed()) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            String timeMilliseconds = Long.toString(timestamp.getTime());

            byte[] screenshot = ((TakesScreenshot) getDriver())
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", timeMilliseconds);
        }
    }

    @After("~@Api")
    public void tearDown() {
        cleanupDriver();
    }
}
