import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


//All the body payload is in this code itself.
public class basicsOne {
	
	public static void main(String[] args){
		
		//https://rahulshettyacademy.com/maps/api/place/add/json?key=qaclick123
		//BaseUrl
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String response = given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body("{\r\n" + 
				"  \"location\": {\r\n" + 
				"    \"lat\": -38.383494,\r\n" + 
				"    \"lng\": 33.427362\r\n" + 
				"  },\r\n" + 
				"  \"accuracy\": 50,\r\n" + 
				"  \"name\": \"Frontline house\",\r\n" + 
				"  \"phone_number\": \"(+91) 983 893 3937\",\r\n" + 
				"  \"address\": \"29, side layout, cohen 09\",\r\n" + 
				"  \"types\": [\r\n" + 
				"    \"shoe park\",\r\n" + 
				"    \"shop\"\r\n" + 
				"  ],\r\n" + 
				"  \"website\": \"http://rahulshettyacademy.com\",\r\n" + 
				"  \"language\": \"French-IN\"\r\n" + 
				"}\r\n" + 
				"")
		.when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo ("APP"))
		.header("Server", "Apache/2.4.18 (Ubuntu)")
		.extract().response().asString();
		
		JsonPath js = new JsonPath(response);
		String captured_placeId = js.getString("place_id");
		System.out.println(captured_placeId);
	} 
}
