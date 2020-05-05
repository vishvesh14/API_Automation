import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.RestAssured;


//In this Test the payload in the body is taken from the SetupFiles package to have a clean look.
public class basics_two {

	public static void main(String[] args) {
		//BaseUrl
				RestAssured.baseURI = "https://rahulshettyacademy.com";
				
				given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
				.body(payLoad.getPostData())
				.when().post("maps/api/place/add/json")
				.then().log().all().assertThat().statusCode(200).body("scope", equalTo ("APP"))
				.header("Server", "Apache/2.4.18 (Ubuntu)");
	}

}
