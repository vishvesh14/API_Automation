import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

public class DynamicJsonParameterization_DataProvider {
	
	@Test(dataProvider = "BookData")
	public void AddBook() {
		
		//PostData
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String capturedResponse = given().header("Content-Type","application/json")
		.body(payLoad.addBookData("test1","test2"))
		.when().post("/Library/Addbook.php")
		.then().assertThat().statusCode(200)
		.extract().response().asString();
		JsonPath js = ReUsableMethods.rawToJSON(capturedResponse);
		String showCapturedResponse = js.get("ID");
	}
	
	@DataProvider(name = "BookData")
	public Object[][] getData() {
		return new Object[][] {{"isbn1","aisle1"},{"isbn2","aisle2"},{"isbn3","aisle3"}};
 
	}

}
