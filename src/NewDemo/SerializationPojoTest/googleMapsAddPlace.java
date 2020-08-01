package SerializationPojoTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class googleMapsAddPlace {

	public static void main(String[] args) {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		 String res = given().queryParam("key", "qaclick123")
		.body()
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response().asString();
		 
		 System.out.println(res);
	}

}
