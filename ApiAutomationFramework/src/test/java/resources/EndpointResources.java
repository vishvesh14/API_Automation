package resources;

//enum is a special class in java that consists of collection of constants or methods
//every value is separated with a comma and the end of collection is marked by a semi colon
public enum EndpointResources {
	
	AddPlaceAPI("/maps/api/place/add/json"),
	GetPlaceAPI("/maps/api/place/get/json"),
	DeletePlaceAPI("/maps/api/place/delete/json");
	
	private String resource;   //Initializing a global variable so that it can be returned in getResource()
	//Creating a constructor
	EndpointResources(String resource){
		this.resource = resource;  //"This.resource" points to the current variable of the class,
		//now local resource will hold the endpoint resource that is stored in resource variable of the constructor
	}
	
	//since you need to return the value that is stored in the above constructor variable i.e: resource
	public String getResource() {
		return resource;
	}
}
