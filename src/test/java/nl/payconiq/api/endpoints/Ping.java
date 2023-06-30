package nl.payconiq.api.endpoints;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import io.qameta.allure.restassured.AllureRestAssured;

//CRUD(get, post, put, delete) operations on the Ping API module.
public class Ping {

	
	/**
	 * 
	 * @author Anshika Gupta
	 * @return {@link Response}
	 */
	public static Response getHealthCheck()
	{
		Response response = given()
				.filter(new AllureRestAssured())
				.when()
				.log().body()
				.get(Routes.healthCheck_url);
		
		return response;
	}
}
