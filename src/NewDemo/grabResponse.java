import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

/*In this example we will do the following:
 --- Grab the PlaceId from the response
*/
public class grabResponse {

	public static void main(String[] args) {
		//BaseUrl
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String capturedResponse = given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body(payLoad.getPostData())
		.when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo ("APP"))
		.header("Server", "Apache/2.4.18 (Ubuntu)")
		.extract().response().asString();          //Extract the response and convert it into String and store into variable
		System.out.println(capturedResponse);
		
		//JsonPath takes string as input and then converts into JSON
		//To get data from the Json response, you need to parse the response using jsonpath
		JsonPath js = new JsonPath(capturedResponse);
		String capturedPlaceId = js.getString("place_id"); //use the jsonpath object to get the String variable from the response
		System.out.println(capturedPlaceId);

	}
}
