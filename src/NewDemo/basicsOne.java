import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class basicsOne {
	
	public void TestOne() {
		
		//BaseUrl
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		given().
			param("name","Frontline house").
			param("accuracy","50").
			param("key","qaclick123").
			
		when().
			get("/maps/api/place/get/json").
			
		then().assertThat().statusCode(200).and()
		.contentType(ContentType.JSON);
	}

}
