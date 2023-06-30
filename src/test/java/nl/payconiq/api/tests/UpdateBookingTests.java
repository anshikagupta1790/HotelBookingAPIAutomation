package nl.payconiq.api.tests;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import nl.payconiq.api.endpoints.Booking;

public class UpdateBookingTests extends SetUp {
	
	@Test(priority = 1, description = "create new booking with success response")
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
	
	
	
	@Test(priority = 2, description = "update the previously created booking with new data and success response")
	public void updateBookingTest() {
		
		//Prepare updateBooking Payload
		bookingRequest.setFirstname(faker.name().firstName());
		bookingRequest.setLastname(faker.name().lastName());
		bookingRequest.setTotalprice(faker.number().numberBetween(2000, 3000));
		
		String payload = gson.toJson(bookingRequest);
		
		Response response = Booking.updateBooking(payload, token, bookingID, basicAuth);
		
		response.then().log().body()
				.statusCode(200).assertThat()
				.body("firstname", equalTo(bookingRequest.getFirstname()))
				.body("lastname", equalTo(bookingRequest.getLastname()))
				.body("totalprice", equalTo(bookingRequest.getTotalprice()));
	}
	
	
	@Test(priority = 3, description = "update existing booking with invalid token")
	public void updateBookingWithoutAuthTest() {
		
		//Prepare the updateBooking payload
		bookingRequest.setFirstname(faker.name().firstName());
		bookingRequest.setLastname(faker.name().lastName());
		bookingRequest.setTotalprice(faker.number().numberBetween(2000, 3000));
		
		String payload = gson.toJson(bookingRequest);
		
		Response response = Booking.updateBooking(payload, null, bookingID, basicAuth);
		
		response.then().log().body()
				.statusCode(200).assertThat()
				.body("firstname", equalTo(bookingRequest.getFirstname()))
				.body("lastname", equalTo(bookingRequest.getLastname()))
				.body("totalprice", equalTo(bookingRequest.getTotalprice()));
	}
	
	@Test(priority = 4, description = "New created booking deleted with success")
	public void deleteBookingWithSuccess() {
		
		Response response = Booking.deleteBooking(token, bookingID, basicAuth);

		response.then()
				.statusCode(201)
				.body(equalTo("Created"));
	}

}
