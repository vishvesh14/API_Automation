package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	public static RequestSpecification req; //Variable is set to static, so that everytime a new variable instance is not created during the entire testcase.
	public io.restassured.specification.RequestSpecification RequestSpecification() throws IOException{
		
		//req is set to null, so that multiple data sets are printed in the log file, instead of overwriting the previous dataset
		if(req==null) {
		
		//Need to add a object to printstream class to tell clearly where to log the output.
		//To create a file at runtime, New FileOutput Stream class
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		//Spec Builder Request and Response are used, so to avoid writing the repeated code
		req = new RequestSpecBuilder().setBaseUri(getGlobalValue("BaseURL"))
				.addQueryParam("key","qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log)) //adding logs at a global level, so that you dont need to put this code again and again for different test cases
				.addFilter(ResponseLoggingFilter.logResponseTo(log)) //Log the response as well
				.setContentType(ContentType.JSON)
				.build();
		return req;
		}
		return req;
		
	}
	
	public static String getGlobalValue(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("src/test/java/resources/Global.properties");
		prop.load(fis); //This loads the file to read
		return prop.getProperty(key);
	}
	
	public String getJsonPath(Response response, String key) {
		String responseString = response.asString();
		JsonPath js = new JsonPath(responseString);
		return js.get(key).toString();
	}
	

}
