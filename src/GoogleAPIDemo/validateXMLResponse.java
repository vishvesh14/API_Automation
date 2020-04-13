import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import files.resources;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import files.payLoad;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class validateXMLResponse {
	
	Properties prop = new Properties();
	
	@BeforeTest
	public void getData() throws IOException {
		FileInputStream fis = new FileInputStream("D:\\Softwares\\TestCode_Workspace\\DemoProject\\API_Automation\\src\\files\\env.properties");
		prop.load(fis);
	}
	
	@Test
	public void test() {
			RestAssured.baseURI = prop.getProperty("HOST");
		
		
			//Task 1: Grab the response
			Response res =given().
				
				queryParam("key",prop.getProperty("KEY")).
				body(payLoad.getPostBodyData()).
				
			when().
				post(resources.placePostData()).
			
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
	
			//Use the Place ID in the delete request
			given().
				queryParam("key","qaclick123").
				body("{\r\n" + 
						"\"place_id\":\""+placeId+"\"\r\n" + 
						"}").
			
			when().
				post("/maps/api/place/add/json").
				
			then().assertThat().statusCode(200).and().
			contentType(ContentType.JSON);
	}
}
