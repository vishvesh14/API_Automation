import io.restassured.path.json.JsonPath;


//Sample Data is in payload.java file
public class complexJSONParse {
	
	public static void main (String[] args) {
		
		JsonPath js2 = new JsonPath(payLoad.courseData());
		
		//Print No of courses returned by API
		int totalCourses = js2.getInt("courses.size()");
		System.out.println(totalCourses);
		
		//Print Purchase Amount
		int purchaseAmount = js2.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);
		
		//Print Title of the first course
		String firstCourseTitle = js2.getString("courses[0].title");
		System.out.println(firstCourseTitle);
		
		//Print All course titles and their respective Prices
		for(int i=0;i<totalCourses;i++){
			String courses = js2.get("courses["+i+"].title");
			System.out.println(courses);
			int price = js2.get("courses["+i+"].price");
			System.out.println(price);
		}
		
		//Print price if title is RPA
		for(int i = 0; i<totalCourses; i++){
			String course1 = js2.get("courses["+i+"].title");
			if (course1.equalsIgnoreCase("RPA")) {
				int price = js2.getInt("courses["+i+"].price");
				System.out.println("price");
			}
		}
		
		
	}

}
