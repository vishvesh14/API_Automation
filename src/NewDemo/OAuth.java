
import static io.restassured.RestAssured.given;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import pojoClassesDemo.GetCourse;

public class OAuth {

	public static void main(String[] args) throws InterruptedException {
	//Direct URL is taken because now the auth cannot be automated, since the 2020 google update
		///String url ="https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&code=4%2FvAHBQUZU6o4WJ719NrGBzSELBFVBI9XbxvOtYpmYpeV47bFVExkaxWaF_XR14PHtTZf7ILSEeamywJKwo_BYs9M&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&session_state=0c32992f0d47e93d273922018ade42d1072b9d1f..a35c&prompt=none#";
		String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F2AEvFG5pdeJLMgYclP62ehLd46F2lHe32eXod2adPisl4ixsTV_IOXtI54-busAHbNOZS5qjKdgBnT0IOAHp2Oc&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none#";
		
		//Split is used to get only the required CODE from the entire URL
		String partialcode = url.split("code=")[1];
		String code = partialcode.split("&scope")[0];
		String print;
		System.out.println(code);
		
	// By Default RestAssured performs encoding for special characters	
	//EncodingEnabled is false because the code contains special characters which is not understood by restasuured until Explicity specified
		String response = given().urlEncodingEnabled(false)
					.queryParams("code",code)
					.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
					.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
					.queryParams("grant_type", "authorization_code")
					.queryParams("state", "verifyfjdss")
					.queryParams("session_state", "ff4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8")
					// .queryParam("scope", "email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email")
					.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
					.when().log().all()
					.post("https://www.googleapis.com/oauth2/v4/token").asString();
					// System.out.println(response);
					JsonPath jsonPath = new JsonPath(response);
					String accessToken = jsonPath.getString("access_token");
					System.out.println(accessToken);

					/*String r2=given().contentType("application/json").
					queryParams("access_token", accessToken).expect().defaultParser(Parser.JSON)
					.when().get("https://rahulshettyacademy.com/getCourse.php").asString();
					System.out.println(r2);*/
	
					//gc is a boject created for GetCourse class
					//so that what ever response is got, restAssured treats it as a JSON, This can be avoided if in the response the content type is JSON, currently it is HTML so its used.
					GetCourse gc = given().queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON)
					.when().get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);
			        System.out.println(gc.getLinkedIn());
			        System.out.println(gc.getInstructor());
	}

}
