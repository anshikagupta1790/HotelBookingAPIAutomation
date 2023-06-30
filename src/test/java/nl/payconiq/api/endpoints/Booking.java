package nl.payconiq.api.endpoints;

import static io.restassured.RestAssured.given;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//CRUD(get, put, post, delete ) operations on the Booking module API endpoints
public class Booking {
	
	/**
	 * This method calls the CreateBooking API endpoint and collect the response
	 * @author Anshika Gupta
	 * @param payload JSON body of CreateBookingRequest
	 * @return {@link Response}
	 */
	public static Response createBooking(String payload) {
		
		Response response = given()
							.filter(new AllureRestAssured())
							.contentType(ContentType.JSON)
							.body(payload)
							.log().body()
							.when()
							.post(Routes.booking_url);
		
		return response;
	}
	
	
	/**
	 * This method calls the GetBooking API endpoint and collect the response.
	 * @author Anshika Gupta
	 * @param id existing bookingId
	 * @return {@link Response}
	 */
	public static Response getBookingById(int id) {
		
		Response response = given()
							.filter(new AllureRestAssured())
							.pathParam("id", id)
							.when()
							.log().body()
							.get(Routes.bookingId_url);
		
		return response;
	}
	
	
	/**
	 * This method calls the GetBookingIds API endpoint and collect the response.
	 * @author Anshika Gupta
	 * @param firstname firstname of user with existing booking
	 * @param lastname lastname of user with existing booking
	 * @return {@link Response}
	 */
	public static Response getAllBookingIdsByFilter(String firstname, String lastname) {
		Response response = given()
							.filter(new AllureRestAssured())
							.queryParam("firstname", firstname)
							.queryParam("lastname", lastname)
							.when()
							.log().body()
							.get(Routes.booking_url);
		
		return response;
				
	}
	
	/**
	 * This method calls the GetBookingIds API endpoint and collect the response.
	 * @author Anshika Gupta
	 * @return {@link Response}
	 */
	public static Response getAllBookingIds() {
		Response response = given()
							.filter(new AllureRestAssured())
							.when()
							.log().body()
							.get(Routes.booking_url);
		
		return response;
				
	}
	
	/**
	 * This method calls the UpdateBooking API endpoint and collect the response.
	 * @author Anshika Gupta
	 * @param payload JSON payload of existing booking with updated values
	 * @param token Authentication token
	 * @param id existing bookingId
	 * @return {@link Response}
	 */
	public static Response updateBooking(String payload, String token, int id, String basicAuth) {
		Response response = given()
							.filter(new AllureRestAssured())
							.contentType(ContentType.JSON)
							.cookie("token="+ token)
							.header("Authorization", basicAuth)
							.pathParam("id", id)
							.body(payload)
							.when().log().body()
							.put(Routes.bookingId_url);
		
		return response;
				
	}
	
	/**
	 * This method calls the PartialUpdateBooking API endpoint and collect the response.
	 * @author Anshika Gupta
	 * @param payload JSON payload with partial data for updating in existing booking
	 * @param token Authentication token
	 * @param id existing bookingId
	 * @return {@link Response}
	 */
	public static Response partialUpdateBooking(String payload , String token, int id, String basicAuth) {
		
		
		Response response = given()
							.filter(new AllureRestAssured())
							.contentType(ContentType.JSON)
							.cookie("token="+ token)
							.header("Authorization", basicAuth)
							.pathParam("id", id)
							.body(payload)
							.when().log().body()
							.patch(Routes.bookingId_url);
		
		return response;
				
	}
	
	/**
	 * This method calls the DeleteBooking API endpoint and collect the response.
	 * @author Anshika Gupta
	 * @param token Authentication token
	 * @param id existing bookingId
	 * @return {@link Response}
	 */
	public static Response deleteBooking(String token, int id, String basicAuth) {
		
		Response response = given()
							.filter(new AllureRestAssured())
							.contentType(ContentType.JSON)
							.cookie("token="+ token)
							.header("Authorization", basicAuth)
							.pathParam("id", id)
							.when()
							.log().body()
							.delete(Routes.bookingId_url);
		
		return response;
	}
}
