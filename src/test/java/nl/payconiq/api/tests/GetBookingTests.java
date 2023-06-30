package nl.payconiq.api.tests;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.emptyArray;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import nl.payconiq.api.endpoints.Booking;

public class GetBookingTests extends SetUp {

	@Test(priority = 1, description = "Test create new booking with success response")
	public void createBookingWithSuccess() {

		String payload = gson.toJson(bookingRequest);

		Response response = Booking.createBooking(payload);

		response.then().log().body().statusCode(200).assertThat()
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

	@Test(priority = 2, description = "Test get previously created booking by bookingId")
	public void getBookingByIdSuccess() {
		Response response = Booking.getBookingById(bookingID);

		response.then().log().body().statusCode(200).assertThat()
				.body("firstname", equalTo(bookingRequest.getFirstname()))
				.body("lastname", equalTo(bookingRequest.getLastname()))
				.body("totalprice", equalTo(bookingRequest.getTotalprice()))
				.body("additionalneeds", equalTo(bookingRequest.getAdditionalneeds()));
	}

	@Test(priority = 3, description = "Test to get booking with wrong bookingId and received failure response")
	public void getBookingByIdWithBadBokingID() {
		
		Response response = Booking.getBookingById(9999999);

		response.then()
				.log()
				.body()
				.statusCode(404)
				.body(equalTo("Not Found"));
		
	}

	@Test(priority = 4, description = "Test to get all booking without any filter with success response")
	public void getAllBookingsWithoutFilter() {
		Response response = Booking.getAllBookingIds();

		response.then()
				.statusCode(200)
				.body("$", not(emptyArray()))
				.body("$", hasItem(anyOf(hasEntry("bookingid", bookingID))));

	}

	@Test(priority = 5, description = "Test to get all bookings with filters with success response")
	public void getAllBookingsWithFilter() {
		Response response = Booking.getAllBookingIdsByFilter(bookingRequest.getFirstname(), bookingRequest.getLastname());

		response.then()
				.statusCode(200)
				.log().body()
				.body("$", not(emptyArray()))
				.body("$", hasItem(anyOf(hasEntry("bookingid", bookingID))));

	}

	@Test(priority = 6, description = "Delete the created booking with success")
	public void deleteBookingWithSuccess() {

		Response response = Booking.deleteBooking(token, bookingID, basicAuth);

		response.then()
				.log().body()
				.assertThat()
				.statusCode(201);
	}

}
