Feature: Validating Add Place API's

@AddPlace @Regression
Scenario Outline: Verify if place is successfully added using AddPlace API
Given Add Place Payload with "<name>" "<address>" "<language>"
When user calls "AddPlaceAPI" using the "POST" http request
Then The API call is succesful with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify place_id created maps to "<name>" using "GetPlaceAPI"

Examples:
		|name 			| address 			|language |
		|Backyard house | 91, Main Street	|Spanish  |
#		|Front Street   | 60, BackSide 	    |Hindi	  |	

@DeletePlace @Regression
Scenario: Verify if Delete Place functionality is working
Given DeletePlace payload
When user calls "DeletePlaceAPI" using the "POST" http request
Then The API call is succesful with status code 200
And "status" in response body is "OK"
