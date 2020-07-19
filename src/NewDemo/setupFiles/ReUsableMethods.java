import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReUsableMethods {

	public static JsonPath rawToJSON(String capturedResponse) {
		JsonPath js = new JsonPath(capturedResponse);
		return js;
	}
}
