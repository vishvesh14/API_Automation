import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class SumValidation {
	
	@Test
	public void SumCourses() {
		
		JsonPath js = new JsonPath(payLoad.courseData());
		int totalCourses = js.getInt("courses.size()");
		
		for (int i = 0; i < totalCourses; i++) {
			int price = js.getInt("totalCourses["+i+"].price");
			int copies = js.getInt("totalCourses["+i+"].copies");
			int amount = price*copies;
		}
	}

}
