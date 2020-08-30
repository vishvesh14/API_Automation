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
import junit.framework.Assert;
import resources.EndpointResources;
import resources.TestDataBuild;
import resources.Utils;

public class placeValidationStepDef extends Utils{
	
	RequestSpecification res;
	ResponseSpecification resp;
	Response response;
	static String place_id; //Static so that place_id can be used in different scenarios, otherwise after every scenario variables values will be reset to null
	TestDataBuild data =new TestDataBuild();

	@Given("Add Place Payload with {string} {string} {string}")
	public void add_Place_Payload_with(String name, String address, String language) throws IOException {
		//Given is split
		res = given().spec(RequestSpecification())
		.body(data.addPlacePayLoad(name, address, language));
	}
	
	@When("user calls {string} using the {string} http request")
	public void user_calls_using_the_http_request(String resource, String httpMethod) {
		EndpointResources resourceAPI = EndpointResources.valueOf(resource);
		//System.out.println(resourceAPI.getResource());
		resp = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build(); //Response Spec Builder
		if(httpMethod.equalsIgnoreCase("POST")) {
			response = res.when().post(resourceAPI.getResource());
			}
		else if(httpMethod.equalsIgnoreCase("GET"))
			response = res.when().get(resourceAPI.getResource());
		}
			//.then().spec(resp).extract().response();
	
	@Then("The API call is succesful with status code {int}")
	public void the_API_call_is_succesful_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(),200);
	}
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String ExpectedValue) {
		assertEquals(getJsonPath(response,keyValue),ExpectedValue);		
	}
	
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
		place_id = getJsonPath(response,"place_id");
		System.out.println(place_id);
		res = given().spec(RequestSpecification()).queryParam("place_id",place_id);
		user_calls_using_the_http_request(resource,"GET");
		String actualName = getJsonPath(response,"name");
		assertEquals(actualName,expectedName);
	}
	
	@Given("DeletePlace payload")
	public void deleteplace_payload() throws IOException {
		res = given().spec(RequestSpecification()).body(data.deletePlaceId(place_id));
	    
	}

}
