package SerializationPojoTest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;


//This method is not very much optimised, This will be done when later in frameworks
public class specBuilderTest {

	public static void main(String[] args) {
		
		//RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		addPlace p = new addPlace();   //created object for class Addplace
		p.setAccuracy(50);
		p.setName("Frontline house");
		p.setAddress("29, side layout, cohen 09");
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("http://google.com");
		p.setLanguage("French-IN");
		
		//since Types are List
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		p.setTypes(myList);
		
		
		//Location is into another class, so need to create a object for Location class
		Location l = new Location();
		l.setLat(-38.383494);   //no quotes since data type is double
		l.setLng(33.427362);
		p.setLocation(l);
		
		//Spec Builder Request and Response are used, so to avoid writing the repeated code
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key","qaclick123")
				.setContentType(ContentType.JSON)
				.build();
				
		//Response Spec Builder
		ResponseSpecification resp = new ResponseSpecBuilder().expectStatusCode(200).build();
		
		//Given is split 
		RequestSpecification res = given().spec(req)
		.body(p);
		
		//use the response object and trigger the POST endpoint
		Response response = res.when().post("/maps/api/place/add/json")
							.then().spec(resp).extract().response();
		
		String responseString = response.asString();
		 System.out.println(responseString);
	}

}