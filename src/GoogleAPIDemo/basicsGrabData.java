import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class basicsGrabData {
	
	@Test
	public void test() {
		RestAssured.baseURI = "http://216.10.245.166";
		
		//Task 1: Grab the response
		Response res =given().
			
			queryParam("key","qaclick123").
			body("{\r\n" + 
				"\"location\":{\r\n" + 
				"\"lat\" : -38.383494,\r\n" + 
				"\"lng\" : 33.427362\r\n" + 
				"},\r\n" + 
				"\"accuracy\":50,\r\n" + 
				"\"name\":\"Frontline house\",\r\n" + 
				"\"phone_number\":\"(+91) 983 893 3937\",\r\n" + 
				"\"address\" : \"29, side layout, cohen 09\",\r\n" + 
				"\"types\": [\"shoe park\",\"shop\"],\r\n" + 
				"\"website\" : \"http://google.com\",\r\n" + 
				"\"language\" : \"French-IN\"\r\n" + 
				"}\r\n" + 
		"").
			
		when().
			post("/maps/api/place/add/json").
		
		then().assertThat().statusCode(200).and().
			contentType(ContentType.JSON).and().
			body("status",equalTo("OK")).
		
		extract().response();
		
		//Task 2: Delete the place ID from response
		String responseString = res.asString();			//Raw data from api response to string
		System.out.println(responseString);
		JsonPath js = new JsonPath(responseString);     //Converts to JSON
		String placeId = js.get("place_id");
		System.out.println(placeId);
	}
}
