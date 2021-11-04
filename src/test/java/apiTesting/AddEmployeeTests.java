package apiTesting;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class AddEmployeeTests {

	private static String requestBodySuccess = "{\n" + "  \"firstName\": \"foo\",\n" + "  \"lastName\": \"bar\",\n"
			+ "  \"dependants\": \"2\" \n}";

	private static String requestBodyFailure = "{\n" + "  \"firstName\": \"\",\n" + "  \"lastName\": \"\",\n"
			+ "  \"dependants\": \"-1\" \n}";

	
	//ensuring we can hit adding employee successfully
	@Test
	public void addEmployeeSuccess() {

		RestAssured.baseURI = "https://wmxrwq14uc.execute-api.us-east-1.amazonaws.com/Prod/api";

		Response response = given().auth().preemptive().basic("TestUser80", "DAA-v$E7WhF&")
				.header("Content-type", "application/json").and().body(requestBodySuccess).when().post("/employees")
				.then().extract().response();

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		Assert.assertEquals("foo", response.jsonPath().getString("firstName"));
		Assert.assertEquals("bar", response.jsonPath().getString("lastName"));
		Assert.assertEquals("2", response.jsonPath().getString("dependants"));

	}

	
	//ensuring we get valid feedback when we fail to actually add an employee
	@Test
	public void addEmployeeFailure() {

		RestAssured.baseURI = "https://wmxrwq14uc.execute-api.us-east-1.amazonaws.com/Prod/api";

		Response response = given().auth().preemptive().basic("TestUser80", "DAA-v$E7WhF&")
				.header("Content-type", "application/json").and().body(requestBodyFailure).when().post("/employees")
				.then().extract().response();

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 400);

	}

}
