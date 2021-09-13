package ZAPI;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class getAssetDetails {
	//https://t01-zapi.mirriad.com
	public static void main(String[] args) {
		RestAssured.baseURI = "https://d01-zapi.mirriad.com";
		String CapResponse = given().log().all().relaxedHTTPSValidation()
				.pathParam("projectID", "e0f87e87-3d34-4f5a-afd1-e1597bb4a3a6")
				.header("Mirriad-Internal-MPP-Username","vishvesh.sawant")
				.header("Mirriad-Hub-Name","chub-d1")
		
		.when().get("/v1/projects/{projectID}")
		
		.then().log().all().assertThat().statusCode(200)
		//.body("status", equalTo("MEDIA_PREPARATION"))
		
		.body("status", equalTo("CREATED"))
		//.body("hub",equalTo("chub-t1-001"))
		
		.body("hub",equalTo("chub-d1"))
		.header("Content-Type", equalTo("application/json"))
		
		.extract().response().asString();
		System.out.printf("CapturedResponse: ",CapResponse);
		
		JsonPath js = new JsonPath(CapResponse);
		String getId = js.getString("hub");
		System.out.println(getId);
		}
}
