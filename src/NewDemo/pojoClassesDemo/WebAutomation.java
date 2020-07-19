package pojoClassesDemo;


//Need to create separate classes for webautomation/api/mobile because they have jsons inside each of them
//Also they have multiple items inside each of them
public class WebAutomation {
	
	private String courseTitle;
	private String price;
	
	public String getCourseTitle() {
		return courseTitle;
	}
	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	

}
