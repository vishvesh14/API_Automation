Feature: Validating Add Place API's

Scenario Outline: Verify if place is successfully added using AddPlace API
Given Add Place Payload with "<name>" "<address>" "<language>"
When user calls "AddPlaceAPI" using the POST http request
Then The API call is succesful with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"

Examples:
		|name 			| address 			|language |
		|Backyard house | 90, Main Street	|Spanish  |
		|Front Street   | 60, BackSide 	    |Hindi	  |	