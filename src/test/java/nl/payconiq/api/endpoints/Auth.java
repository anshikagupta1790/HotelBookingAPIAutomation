package nl.payconiq.api.endpoints;

import static io.restassured.RestAssured.given;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//CRUD(get, put, post, delete ) operations on the Auth module API endpoints
public class Auth {
	
	/**
	 * This method calls the createToken API endpoint and collect the response.
	 * @author Anshika Gupta
	 * @param  payload JSON body of CreateTokenRequest 
	 * @return {@link Response}
	 */
	public static Response createToken(String payload) {
		Response response=given()
		.filter(new AllureRestAssured())
		.contentType(ContentType.JSON)
		.body(payload)
		.when()
		.post(Routes.craeteToken_url);
		
		return response;
	}

}
