
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import pojoClassesDemo.Api;
import pojoClassesDemo.GetCourse;
import pojoClassesDemo.WebAutomation;

public class OAuth {
	
	public static void main(String[] args) throws InterruptedException {
		
		String[] expectedCourseTitles = {"Selenium Webdriver Java","Cypress","Protractor"};
		
		//Direct URL is taken because now the auth cannot be automated, since the 2020 google update
		//String url ="https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&code=4%2FvAHBQUZU6o4WJ719NrGBzSELBFVBI9XbxvOtYpmYpeV47bFVExkaxWaF_XR14PHtTZf7ILSEeamywJKwo_BYs9M&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&session_state=0c32992f0d47e93d273922018ade42d1072b9d1f..a35c&prompt=none#";
		//Below URL is unique all the time, copy and paste the GetCode endpooint url in browser and sign in to google and once logged in, copy the URL from the browser
		String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F2QEua7ZNO8wXYl-9NlfqgroXUBlXyH6UAOF7MQUou86cft5cYSb1M-CfM10sVZ1QG_R9UKKm7EoeXqskIdeAllM&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none#";
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
	
					//gc is a oject created for GetCourse class
					//so that what ever response is got, restAssured treats it as a JSON, This can be avoided if in the response the content type is JSON, currently it is HTML so its used.
					GetCourse gc = given().queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON)
					.when().get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);
			        System.out.println(gc.getLinkedIn());
			        System.out.println(gc.getInstructor());
			        gc.getCourses().getApi().get(1).getCourseTitle();     //Course title at index 1
			        
//Get the price of Course Title
			        //But since finding data by index are not ideal, need to iterate and find the required data
			        //create a variable with data type as List because API has been declared as list in Courses class
			       List<Api> apiCourses = gc.getCourses().getApi();
			       for (int i=0;i<apiCourses.size();i++) {
			    	   if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
			    		   System.out.println(apiCourses.get(i).getPrice());		    		   
			    	   }
			       }
			       
//Get the course titles of Web Automation
			       List<WebAutomation> webCourses = gc.getCourses().getWebAutomation();
			       for (int i=0;i<webCourses.size();i++) {		    	   
			    	   		//Below code will print all the course titles
			    		   System.out.println(webCourses.get(i).getCourseTitle());
			       }
			       
//In real scenario, you need to compare the expected and actual results
			       //Initialize a Array wih the expected course title.
			       //Use a Array List as the actual results count will vary, ie. web automation course titles can be more than 3
			       ArrayList<String> actualResult = new ArrayList<String>(); //return type is String
			       List<WebAutomation> webCourses1 = gc.getCourses().getWebAutomation();
			       for(int i=0; i<webCourses1.size();i++) {
			    	   //Now you need to store the course title values so that it can be compared with expected result.
			    	   //So use add method from the array list
			    	   actualResult.add(webCourses1.get(i).getCourseTitle());
			       }
			       //Now since exp course title is a Array and actual result is a Array List, exp course title needs to be converted to array list.
			       List<String> expectedResult = Arrays.asList(expectedCourseTitles);
			       Assert.assertTrue(actualResult.equals(expectedResult));
	}

}
