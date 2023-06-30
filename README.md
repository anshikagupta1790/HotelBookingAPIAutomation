# HotelBookingAPIAutomation

#### Project website link: http://restful-booker.herokuapp.com/apidoc/index.html#api-Auth <br>
-----------------------------------------------------------
## Technology: <br>
* Automation Framework: RestAssured <br>
* Build tool: maven <br>
* Bundled Tools: TestNG
* Language: JAVA <br>
* Report: SureFire maven plugin report <br>
* IDE: Eclipse <br>

----------------------------------------------------------

## Project Foler Structure: <br>

![image](https://github.com/anshikagupta1790/HotelBookingAPIAutomation/assets/66030634/05a43cdd-c45f-4b96-a4dc-6033229e9d6c)

* api.endpoints : This package contain helper java classes to perform CRUD(get, put, post, delete, patch) operations on API endpoints
* api.model : This package contain model pojo classes for the API endpoint requests
* api.utils : This package contain all the generic utility classes 
* api.tests : This package contain all the test cases related to HotelBookingAPIs
* src.test.resources : This package contain config file used in the project
* target.allure-reports : This package contain all the reports required for allure maven plugin to generate the report.
----------------------------------------------------------

## API Test Suits:<br>
1. TC01(CreateTokenTests.java)-
   - Test new token creation with success
   - Test new token with invalid username with fail response
2. TC02(CreateBookingTests)-
   - Test new booking creation with success
   - Test new booking creation with invalid request with fail response
   - Test the deletion of previously created booking
3. TC03(DeleteBookingTests.java)-
   - Test new booking creation with success
   - Test the deletion of previously created booking with success response
   - Test the deletion of previously created booking with invalid authentication with fail response
4. TC04(GetBookingTests.java)-
   - Test new booking creation with success
   - Test to get previously created booking using bookingId with success response
   - Test to get previously created booking using invalid bookingId with fail response
   - Test the deletion of previously created booking
5. TC05(PartialUpdateBookingTests.java)-
   - Test new booking creation with success
   - Test to partially update the previously created booking with success response
   - Test to partially update the previously created booking with invalid authentication with fail response
   - Test the deletion of previously created booking
6. TC06(UpdateBookingTests.java)-
    - Test new booking creation with success
    - Test to update the previously created booking with success response
    - Test to update the previously created booking with invalid authentication with fail response
    - Test the deletion of previously created booking
7. TC07(HealthCheckAPITests)-
    - Test healthcheck of API using ping request with success response

----------------------------------------------------------

## Prerequisite:
* Download and Install Java on the system
* Download and setup Eclipse IDE on the system
* Setup Maven
* Create a new Maven Project
* Add REST Assured and TestNG dependencies to the project
* Run the tests from command line
* Report Generation

----------------------------------------------------------

## Run the Automation Script:
1. Open cmd/bash terminal to the project folder(/HotelBookingAPIAutomation)
2. Type this command:
   
mvn clean test -Dusername=admin -Dpassword=password123 -Dbasic.authentication=YWRtaW46cGFzc3dvcmQxMjM= allure:serve

This command will perform the tests on all the Hotel Booking APIs and open allure reports in web browser.


----------------------------------------------------------
## Allure Report view from browser:

![image](https://github.com/anshikagupta1790/HotelBookingAPIAutomation/assets/66030634/4559dccf-b46a-48ed-9e83-4c1248f3ed76)

![image](https://github.com/anshikagupta1790/HotelBookingAPIAutomation/assets/66030634/340c0e1f-57da-4cf1-88f1-1f432763aadf)

![image](https://github.com/anshikagupta1790/HotelBookingAPIAutomation/assets/66030634/45e989b1-cba8-490e-bcef-470c00d1f85f)

![image](https://github.com/anshikagupta1790/HotelBookingAPIAutomation/assets/66030634/d4cffd81-8af2-4d1e-bbc2-a9be1b6c46d5)


