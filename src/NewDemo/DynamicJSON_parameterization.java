import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

//When you have a dynamic JSON where the values are changing use this method.
public class DynamicJSON_parameterization {
	
	@Test
	public void AddBook() {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String capturedResponse = given().header("Content-Type","application/json")
		.body(payLoad.addBookData("testIsbn","2592"))
		.when().post("/Library/Addbook.php")
		.then().assertThat().statusCode(200)
		.extract().response().asString();
		JsonPath js = ReUsableMethods.rawToJSON(capturedResponse);
		String capture = js.get("ID");  //capture the ID of the book created
		System.out.println(capture);
		
		//If you run this test second time it will fail, because the book is already present.
		//So to avoid this everytime the test is run, first it should create and then delete it.
		//using Delete endpoint
		String deletedCapturedResponse = given().header("Content-Type","application/json")
		.body("{\r\n" + 
				"	\"ID\" : \""+capture+"\"\r\n" + 
				"} \r\n" + 
				"")
		.when().post("/Library/DeleteBook.php")
		.then().assertThat().statusCode(200)
		.body("msg",equalTo("book is successfully deleted"))
		.extract().response().asString();
		JsonPath js1 = ReUsableMethods.rawToJSON(deletedCapturedResponse);
		String captureMsg = js1.getString("msg");
		System.out.println(captureMsg);
	}

}
