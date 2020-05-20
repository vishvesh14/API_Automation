import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.path.json.JsonPath;

public class SumValidation {
	
	@Test
	public void SumCourses() {
		
		JsonPath js3 = new JsonPath(payLoad.courseData());
		int totalCourses = js3.getInt("courses.size()");
		int purchaseAmount = js3.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);
		int amount,finalAmount = 0;
		for (int i=0;i<totalCourses;i++) {
			int prices = js3.getInt("courses["+i+"].price");
			System.out.println(prices);
			int copies = js3.getInt("courses["+i+"].copies");
			amount = prices*copies;
			System.out.println("Total price is "+amount+"");
			finalAmount = finalAmount + amount;
		}
		System.out.println("Total price is "+finalAmount+" for all copies");
		Assert.assertEquals(purchaseAmount,finalAmount);	
	}
}
