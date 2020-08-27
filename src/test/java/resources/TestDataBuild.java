package resources;

import java.util.ArrayList;
import java.util.List;

import SerializationPojoTest.Location;
import SerializationPojoTest.addPlace;

public class TestDataBuild {
	
	public addPlace addPlacePayLoad(String name, String address, String language) {
		addPlace p = new addPlace();   //created object for class Addplace
		p.setAccuracy(50);
		//p.setName("Frontline house");
		//p.setAddress("29, side layout, cohen 09");
		p.setName(name);
		p.setAddress(address);
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("http://google.com");
		//p.setLanguage("French-IN");
		p.setLanguage(language);
		
		//since Types are List
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		p.setTypes(myList);
		
		//Location is into another class, so need to create a object for Location class
		Location l = new Location();
		l.setLat(-38.383494);   //no quotes since data type is double
		l.setLng(33.427362);
		p.setLocation(l);
		return p;  //returns the object of this class
	}

}
