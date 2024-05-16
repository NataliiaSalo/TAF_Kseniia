package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.User;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


//Created for performing CRUD operations
//payload - user data, request body; endpoints - methods
public class UserEndPoints {
	@Step("Create user")
	public static Response createUser(User payload) {
		Response response = given() //pre-requisite what we ara going to send
				.contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).when() // actually send request
				.post(Routes.post_url);
		return response;
	}

	@Step("Read user")
	public static Response readUser(String userName) {
		Response response = given().pathParam("username", userName).when().get(Routes.get_url);
		return response;
	}

	@Step("Update user")
	public static Response updateUser(String userName, User payload) {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).pathParams("username",
				userName).body(payload).when().put(Routes.update_url);
		return response;
	}

	@Step("Delete user")
	public static Response deleteUser(String userName) {
		Response response = given().pathParams("username", userName).when().delete(Routes.delete_url);
		return response;
	}
}
