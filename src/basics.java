
	import io.restassured.RestAssured;
	import io.restassured.http.ContentType;

	import static io.restassured.RestAssured.given;
	import static org.hamcrest.Matchers.equalTo;

	import org.testng.annotations.Test;

	public class basics {
		
		@Test
		public void Test() {
			
			//BaseURL
			RestAssured.baseURI = "https://maps.googleapis.com";
			
			given().
				param("location","-33.8670522,151.1957362").
				param("radius","500").
				param("key","AIzaSyC_2sFmn2Vt8Emgh1URauH_6IFLrYarFk0").
				
			when().
				get("/maps/api/place/nearbysearch/json").
				
			then().assertThat().statusCode(200).and().
				contentType(ContentType.JSON).and().
				body("results[0].name",equalTo("Sydney")).and().
				body("results[0].place_id",equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and().
				header("server","scaffolding on HTTPServer2");
		}
	}

