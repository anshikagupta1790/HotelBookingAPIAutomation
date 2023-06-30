package nl.payconiq.api.tests;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import nl.payconiq.api.endpoints.Booking;

public class DeleteBookingTests extends SetUp {

	@Test(priority = 1, description = "Test new booking creation with success response")
	public void createBookingWithSuccess() {

		String payload = gson.toJson(bookingRequest);
		
		Response response = Booking.createBooking(payload);

		response.then().log().body()
				.statusCode(200).assertThat()
				.body("booking.firstname", equalTo(bookingRequest.getFirstname()))
				.body("booking.lastname", equalTo(bookingRequest.getLastname()))
				.body("booking.totalprice", equalTo(bookingRequest.getTotalprice()))
				.body("booking.additionalneeds", equalTo(bookingRequest.getAdditionalneeds()));
		
		bookingID = response.then()
							.statusCode(200)
							.assertThat()
							.extract()
							.path("bookingid");
	}
	
	
	@Test(priority = 2, description = "Test deletion of previously created booking with success")
	public void deleteBookingWithSuccess() {
		
		Response response = Booking.deleteBooking(token, bookingID, basicAuth);

		response.then()
				.log().body()
				.assertThat()
				.statusCode(201)
				.body(equalTo("Created"));
	}
	
	
	@Test(priority = 3, description = "Try to delete booking with bad authentication and received failure response")
	public void deleteBookingWithFailure() {
		
		Response response = Booking.deleteBooking("invalid token", bookingID, basicAuth);

		response.then()
				.assertThat()
				.log().body()
				.statusCode(405);
	}
}
