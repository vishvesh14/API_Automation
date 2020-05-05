import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;


/*In this example we will do the following:
 --- Add a place using the ADD endpoint
 --- Update the place data using the UPDATE endpoint
 --- Verify the newly added data using GET endpoint
 */
public class integratingMultipleAPIsWithCommonResponse {

	public static void main(String[] args) {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String capturedResponse = given().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(payLoad.getPostData())
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope",equalTo("APP")).header("Server","Apache/2.4.18 (Ubuntu)")
		.extract().response().asString();
		//System.out.println(capturedResponse);
		JsonPath js = new JsonPath(capturedResponse);
		String capturedPlaceId = js.getString("place_id");
		//System.out.println(capturedPlaceId);
		
		
		//Update Address
		String newUpdatedAddress = "70 Summer walk, SriLanka";
		
		given().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body("{\r\n" + 
				"    \"place_id\": \""+capturedPlaceId+"\",\r\n" + 
				"    \"address\":\""+newUpdatedAddress+"\",\r\n" + 
				"    \"key\":\"qaclick123\"\r\n" + 
				"}")
		.when().put("/maps/api/place/update/json")
		.then().assertThat().statusCode(200).header("Server","Apache/2.4.18 (Ubuntu)")
		.body("msg",equalTo("Address successfully updated"));
		
		
		//Validate the updated address using GET place id endpoint
		String newCapturedAddress = given().queryParam("key","qaclick123").queryParam("place_id",capturedPlaceId)
		.when().get("/maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		JsonPath js1 = new JsonPath(newCapturedAddress);
		String retrievedAddress = js1.getString("address");
		System.out.println(retrievedAddress);
		Assert.assertEquals(retrievedAddress,newUpdatedAddress);
	}

}
