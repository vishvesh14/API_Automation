package SerializationPojoTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;


//This method is not very much optimised, This will be done when later in frameworks
public class googleMapsAddPlace {

	public static void main(String[] args) {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		addPlace p = new addPlace();   //created object for class Addplace
		p.setAccuracy(50);
		p.setName("Frontline house");
		p.setAddress("29, side layout, cohen 09");
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("http://google.com");
		p.setLanguage("French-IN");
		
		//since Types are List
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		p.setTypes(myList);
		
		//Location is into another class, so need to create a object for Location class
		Location l = new Location();
		l.setLat(-38.383494);   //no quotes since data type is double
		l.setLng(33.427362);
		p.setLocation(l);
		
		String res = given().log().all().queryParam("key", "qaclick123")
		.body(p)
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response().asString();
		 
		 System.out.println(res);
	}

}
