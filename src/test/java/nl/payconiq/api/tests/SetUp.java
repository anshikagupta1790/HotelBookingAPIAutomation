package nl.payconiq.api.tests;

import org.testng.annotations.BeforeClass;

import com.github.javafaker.Faker;
import com.google.gson.Gson;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import nl.payconiq.api.endpoints.Auth;
import nl.payconiq.api.model.BookingDates;
import nl.payconiq.api.model.CreateBookingRequest;
import nl.payconiq.api.model.CreateTokenRequest;
import nl.payconiq.api.utils.PropertiesReader;

public class SetUp {
	
	Faker faker;
	CreateBookingRequest bookingRequest;
	CreateTokenRequest authRequest;
	BookingDates bookingDates;
	Gson gson;
	String token;
	int bookingID;
	PropertiesReader propertyReader;
	String base_url;
	PropertiesReader prop;
	String basicAuth;
	
	@BeforeClass(description = "Initialization method to set the base_url and request body for test cases")
	public void setUp() {
		
		faker = new Faker();
		bookingRequest = new CreateBookingRequest();
		bookingDates = new BookingDates();
		authRequest = new CreateTokenRequest();
		gson = new Gson();
		propertyReader = new PropertiesReader();
		prop = new PropertiesReader();
		
		//For text/plain type of API response
		RestAssured.registerParser("text/plain", Parser.TEXT);
		
		//Set API Base URI
		base_url = prop.getProperty("base_url");
		RestAssured.baseURI = base_url;
		
		
		//Get basic authentication from properties
		basicAuth = "Basic "+ prop.getProperty("basic.auth");
		
		//Prepare createAuthToken payload JSON body
		authRequest.setUsername(prop.getProperty("auth.api.username"));
		authRequest.setPassword(prop.getProperty("auth.api.password"));
		
		
		// Extract Authentication token
		token = Auth.createToken(gson.toJson(authRequest))
					.then()
					.assertThat()
					.statusCode(200)
					.extract()
					.path("token");
		
		
		// Prepare createBooking payload JSON body
		bookingRequest.setFirstname(faker.name().firstName());
		bookingRequest.setLastname(faker.name().lastName());
		bookingRequest.setTotalprice(faker.number().numberBetween(100, 1000));
		bookingRequest.setDepositpaid(faker.bool().bool());

		bookingDates = new BookingDates();
		bookingDates.setCheckin("2017-01-01");
		bookingDates.setCheckout("2018-01-01");

		bookingRequest.setBookingdates(bookingDates);
		bookingRequest.setAdditionalneeds(faker.food().dish());
	}

}
