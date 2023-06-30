package nl.payconiq.api.tests;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import nl.payconiq.api.endpoints.Auth;


public class CreateTokenTests extends SetUp {

	@Test(priority = 1, description = "Test new token creation with success")
	public void creteTokenTestWithSuccess() {
		
		String payload = gson.toJson(authRequest);
		
		Response response=Auth.createToken(payload);
		
		response.then()
				.statusCode(200)
				.assertThat()
				.body("$", hasKey("token"));
	}
	
	
	@Test(priority = 2, description = "Test new token creation & wrong username with failure response")
	public void createTokenTestWithBadCred() {
		
		//Edit the username in the existing payload
		authRequest.setUsername("wrongusername");
		String payload = gson.toJson(authRequest);
		
		Response response = Auth.createToken(payload);
		
		response.then()
				.log().body()
				.statusCode(200)
				.assertThat()
				.body("$", hasKey("reason"))
				.body("reason", equalTo("Bad credentials"));
	}
	
}
