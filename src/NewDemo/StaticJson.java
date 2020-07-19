import static io.restassured.RestAssured.given;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;


//Use this method when the JSON input is static and in a external file
public class StaticJson {

	@Test
	public void AddBook() throws IOException{
		
		//PostData
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String capturedResponse = given().header("Content-Type","application/json")
		.body(GenerateStringFromResource("C:\\Users\\Vishvesh Savant\\Desktop\\API Automation Docs\\TestFiles\\AddBookDetails.json"))
		.when().post("/Library/Addbook.php")
		.then().assertThat().statusCode(200)
		.extract().response().asString();
		JsonPath js = ReUsableMethods.rawToJSON(capturedResponse);
		System.out.println(capturedResponse);
		String showCapturedResponse = js.get("ID");
	}
	
	public static String GenerateStringFromResource(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path))); //Reads all the bytes from the external file
		// and convert all the bytes into string 
	}	
}
