import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import static io.restassured.RestAssured.given;

public class JiraTest {

	public static void main(String[] args) {
		RestAssured.baseURI = "http://localhost:8080";
		
		//SessionFilter class is a direct method to store the session
		//Instead of creating a creating a json object like how its done in previous code
		SessionFilter session = new SessionFilter();
		
		//Login API
		String response = given().header("Content-Type","application/json")
		.body("{ \r\n" + 
				"	\"username\": \"vishveshsavant\",\r\n" + 
				"	\"password\": \"vishvesh1234\" \r\n" + 
				"}")
		.filter(session)
		.when().post("/rest/auth/1/session")
		.then().assertThat().statusCode(200)
		.extract().response().asString();
		
		//Add Comment API
		given().pathParam("issueID","10100").log().all().header("Content-Type","application/json")
		.body("{\r\n" + 
				"    \"body\": \"Hey this is a automated comment test !\",\r\n" + 
				"    \"visibility\": {\r\n" + 
				"        \"type\": \"role\",\r\n" + 
				"        \"value\": \"Administrators\"\r\n" + 
				"    }\r\n" + 
				"}")
		.filter(session)
		.when().post("/rest/api/2/issue/{issueID}/comment")
		.then().assertThat().statusCode(201);
	}

}
