package pojoClassesDemo;

public class GetCourse {
	
	private String instructor;
	private String url;
	private String services;
	private String expertise;
	private Courses courses;     //Return type is a class and not a string
	private String linkedIn;

	
	//Since Courses had multiple JSON's inside it, need to create a separate class
	//create setters/getters using alt+shift+s
	
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
	public String getExpertise() {
		return expertise;
	}
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	public pojoClassesDemo.Courses getCourses() {
		return courses;
	}
	public void setCourses(pojoClassesDemo.Courses courses) {
		courses = courses;
	}
	public String getLinkedIn() {
		return linkedIn;
	}
	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}

}
