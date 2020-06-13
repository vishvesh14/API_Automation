import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.Assert;

public class JiraTest {

	public static void main(String[] args) {
		RestAssured.baseURI = "http://localhost:8080";
		
//SessionFilter class is a direct method to store the session
//Instead of creating a creating a json object like how its done in previous code
		SessionFilter session = new SessionFilter();
		
//Login API
		String response = given().relaxedHTTPSValidation().header("Content-Type","application/json") //relaxed Vaidation to skip https as sometimes restassured does not recognise https
		.body("{ \r\n" + 
				"	\"username\": \"vishveshsavant\",\r\n" + 
				"	\"password\": \"vishvesh1234\" \r\n" + 
				"}")
		.filter(session)
		.when().post("/rest/auth/1/session")
		.then().assertThat().statusCode(200)
		.extract().response().asString();
		
		
//Add Comment in Issue
		String expectedComment = "Hey How are you, this is comment number 16!";
		String addCommentResponse = given().pathParam("issueID","10200").header("Content-Type","application/json")
		.body("{\r\n" + 
				"    \"body\": \""+expectedComment+"\",\r\n" + 
				"    \"visibility\": {\r\n" + 
				"        \"type\": \"role\",\r\n" + 
				"        \"value\": \"Administrators\"\r\n" + 
				"    }\r\n" + 
				"}")
		.filter(session)
		.when().post("/rest/api/2/issue/{issueID}/comment")
		.then().assertThat().statusCode(201)
		.extract().response().asString();
		JsonPath js = new JsonPath(addCommentResponse);
		String commentID = js.get("id").toString();			//Retrieving the comment ID from the response
		System.out.println(commentID);
		
//Add Attachment in Issue
		given().header("X-Atlassian-Token","no-check").filter(session).pathParam("issueID","10200")
		.header("Content-Type","multipart/form-data")  //Need to use multipart/form-data, similar to how we use application/json
		//Need to create a File class with the location of the attachment
		.multiPart("file", new File ("G:\\Workplace\\API_Automation\\src\\NewDemo\\SetupFiles\\TestFileAttachment"))
		.when().post("/rest/api/2/issue/{issueID}/attachments")
		.then().assertThat().statusCode(200);
		
//Get Issue Details
		String outputResponse = given().filter(session).pathParam("issueID","10200")
		.queryParam("fields","comment")
		.when().get("/rest/api/2/issue/{issueID}")
		.then().assertThat().statusCode(200)
		.extract().response().asString();
		//System.out.println(outputResponse);
		
//Need to verify that comment which was submitted in the add comment api, is retrieved in the Get Issue details api
		JsonPath js1 = new JsonPath(outputResponse);
		int commentCount = js1.getInt("fields.comment.comments.size()");
		System.out.println(commentCount);
		for(int i =0; i<commentCount; i++) {
			String commentVerifyId = js1.get("fields.comment.comments["+i+"].id").toString();
			if(commentVerifyId.equalsIgnoreCase(commentID)) {
				String message = js1.get("fields.comment.comments["+i+"].body").toString();
				System.out.println(message);
				//Assert.assertEquals(message, expectedComment);
			}
		}
	}

}
