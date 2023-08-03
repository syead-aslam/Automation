package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import utils.Api_Utils;
import utils.Global_Vars;
import utils.TestDataBuild;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.testng.Assert.assertEquals;

public class GraphQL_Test_Steps extends Api_Utils {
    RequestSpecification res;
    ResponseSpecification resspec;
    Response response;
    TestDataBuild data =new TestDataBuild();

    @Given("Get Character Details with {int}  {int}")
    public void get_character_details_with(Integer character_id, Integer Episode_id) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        res = given().spec(getGraphRequest())
                .body(data.getGraphPayload(character_id,Episode_id));

    }
    @When("user do graphQL get calls")
    public void user_do_graph_ql_get_calls() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        //resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        System.out.println(Global_Vars.GRAPH_URL);
        response = res.when().post(Global_Vars.GRAPH_URL);

    }
    @Then("verify name in response {string}")
    public void verify_name_in_response(String name) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        //String Result = response.asString();
        JsonPath js = new JsonPath(response.asString());
        System.out.println(response.asString());
        String CharacterName = js.getString("data.character.name");
        Assert.assertEquals(CharacterName,name);
        String EpisodeName = js.getString("data.episode.name");
        System.out.println(EpisodeName);
        Assert.assertEquals(EpisodeName,"Sacred games");
        String Air_date = js.getString("data.episode.air_date");
        System.out.println(Air_date);
        Integer count = js.getInt("data.characters.info.count");
        System.out.println(count);
        Assert.assertEquals(count,4);

    }

    @Given("Character Name with {string}")
    public void characterNameWith(String charName) throws IOException
    {
        res = given().spec(getGraphRequest()).body(data.mutationGraphPayload(charName));
    }

    @When("user do a graph post call")
    public void userDoAGraphPostCall() throws IOException
    {
        System.out.println(Global_Vars.GRAPH_URL);
        response = res.when().post(Global_Vars.GRAPH_URL);
    }

    @Then("verify status code is {int}")
    public void verifyStatusCodeIs(int arg0) throws IOException
    {
        JsonPath js = new JsonPath(response.asString());
        System.out.println(response.asString());
        assertEquals(response.getStatusCode(),200);
    }
}
