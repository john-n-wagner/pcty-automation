package apiTesting;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class GetEmployeeListTest {

	
	//validating we can return the employee list
	@Test
	public void getEmployeeList() {
		Response response = given().auth().preemptive().basic("TestUser80", "DAA-v$E7WhF&")
				.get("https://wmxrwq14uc.execute-api.us-east-1.amazonaws.com/Prod/api/employees").then().extract()
				.response();

		Assert.assertEquals(200, response.getStatusCode());
		
		System.out.println(response.asPrettyString());

	}

}
