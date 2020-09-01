package stepDefination;

import java.io.IOException;

import io.cucumber.java.Before;

//Hooks class helps in setting pre and post conditions for cucumber scenarios 
public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		//This method should run only when place id is null
		//write a code to get the place id
		//No new code required as its already availabe in the stepDef file
		
		placeValidationStepDef obj = new placeValidationStepDef();
		
		//class name is directly used to call the place id, as it is static variable
		//static variable is independent of class object and tied up to the class memory
		if(placeValidationStepDef.place_id == null) {
			obj.add_Place_Payload_with("John", "Longwalk Road", "English");
			obj.user_calls_using_the_http_request("AddPlaceAPI","POST");
			obj.verify_place_id_created_maps_to_using("John","GetPlaceAPI");
		}
	}

}
