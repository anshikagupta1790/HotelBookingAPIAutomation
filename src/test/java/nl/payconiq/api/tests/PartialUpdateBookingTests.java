package nl.payconiq.api.tests;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import nl.payconiq.api.endpoints.Booking;
import nl.payconiq.api.model.PartialUpdateBookingRequest;

public class PartialUpdateBookingTests extends SetUp {

	@Test(priority = 1, description = "Test create new booking with success")
	public void createBookingTestWithSuccess() {

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
	
	
	@Test(priority = 2, description = "Partially update the previously created booking with success response")
	public void partialuUpdateBookingTest() {
		
		//Prepare the partialBookinUpdate payload
		PartialUpdateBookingRequest partialUpdateRequest = new PartialUpdateBookingRequest();
		partialUpdateRequest.setFirstname(faker.name().firstName());
		partialUpdateRequest.setLastname(faker.name().lastName());
		
		String payload = gson.toJson(partialUpdateRequest);
		
		Response response = Booking.partialUpdateBooking(payload, token, bookingID, basicAuth);
		
		response.then().log().body()
				.statusCode(200).assertThat()
				.body("firstname", equalTo(partialUpdateRequest.getFirstname()))
				.body("lastname", equalTo(partialUpdateRequest.getLastname()))
				.body("totalprice", equalTo(bookingRequest.getTotalprice()));
	}
	
	@Test(priority = 3, description = "Partially update the previously created booking with invalid authentication and failure response code.")
	public void partialuUpdateBookingWithBadAuthenticationTest() {
		
		//Prepare partialBookingUpdate payload
		PartialUpdateBookingRequest partialUpdateRequest = new PartialUpdateBookingRequest();
		partialUpdateRequest.setFirstname(faker.name().firstName());
		partialUpdateRequest.setLastname(faker.name().lastName());
		
		String payload = gson.toJson(partialUpdateRequest);
		
		Response response = Booking.partialUpdateBooking(payload, token, bookingID, "invalid");
		
		response.then()
				.log().body()
				.statusCode(403)
				.body(equalTo("Forbidden"));
	}
	
	
	@Test(priority = 4, description = "Delete the previously created booking")
	public void deleteBookingWithSuccess() {
		
		Response response = Booking.deleteBooking(token, bookingID, basicAuth);

		response.then()
				.log().body()
				.assertThat()
				.statusCode(201)
				.body(equalTo("Created"));
	}
}
