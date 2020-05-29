import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

//When you have a dynamic JSON where the values are changing use this method.
public class DynamicJsonParameterization_DataProvider {
	
	@Test(dataProvider="BookData")
	public void AddBook(String isbn, String aisle){
		
		//PostData
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String capturedResponse = given().header("Content-Type","application/json")
		.body(payLoad.addBookData(isbn,aisle))
		.when().post("/Library/Addbook.php")
		.then().assertThat().statusCode(200)
		.extract().response().asString();
		JsonPath js = ReUsableMethods.rawToJSON(capturedResponse);
		System.out.println(capturedResponse);
		String showCapturedResponse = js.get("ID");

	}
	
	@DataProvider(name="BookData")
	public Object[][] getData() {
		//MultiDimentional Array
		return new Object[][] {{"isbntest1","7819"},{"isbntest2","8919"}};
 
	}

}
