package stepDefination;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import SerializationPojoTest.Location;
import SerializationPojoTest.addPlace;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.EndpointResources;
import resources.TestDataBuild;
import resources.Utils;

public class placeValidationStepDef extends Utils{
	
	RequestSpecification res;
	ResponseSpecification resp;
	Response response;
	TestDataBuild data =new TestDataBuild();

	@Given("Add Place Payload with {string} {string} {string}")
	public void add_Place_Payload_with(String name, String address, String language) throws IOException {
		//Given is split
		res = given().spec(RequestSpecification())
		.body(data.addPlacePayLoad(name, address, language));
	}
	
	@When("user calls {string} using the POST http request")
	public void user_calls_using_the_POST_http_request(String resource) {
		EndpointResources resourceAPI = EndpointResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		resp = new ResponseSpecBuilder().expectStatusCode(200).build(); //Response Spec Builder
		response = res.when().post(resourceAPI.getResource())
				.then().spec(resp).extract().response();
	}
	
	@Then("The API call is succesful with status code {int}")
	public void the_API_call_is_succesful_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(),200);
	}
	
	@Then("{string} in response body is {int}")
	public void in_response_body_is(String keyValue, Integer ExpectedValue) {
		String responseString = response.asString();
		System.out.println(responseString);
		JsonPath js = new JsonPath(responseString);
		assertEquals(js.get(keyValue).toString(),ExpectedValue);
		System.out.println(keyValue);
		System.out.println(ExpectedValue);
		
	}
	
	@And("{string} in response body is {string}")
	public void in_response_body_is(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
}
