package nl.payconiq.api.tests;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.response.Response;
import nl.payconiq.api.endpoints.Ping;

public class HealthCheckAPITests extends SetUp {

	@Test(priority = 1, description = "Test HealthCheck API endpoint with success response")
	public void healthCheckAPIWithSuccess() {
		
		Response response = Ping.getHealthCheck();
		
		response.then()
				.assertThat()
				.statusCode(201)
				.log().body()
				.body(equalTo("Created"));
	}
}
