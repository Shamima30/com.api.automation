
package com.terstRun;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetFunction {
	Response responseObj;
	SoftAssert sf = new SoftAssert();

	@BeforeTest
	public void setup() {
		responseObj = RestAssured.get("https://httpbin.org/get");

	}

	@Test(priority = 0)
	public void statusCode() {

		sf.assertEquals(responseObj.statusCode(), 200);

	}

	@Test(priority = 1)
	public void responseTime() {

		sf.assertTrue(responseObj.time() < 2000);
	}

	@Test(priority = 2)
	public void dataTypeFormatCheck() {

		sf.assertTrue(responseObj.contentType().contains("json"));
	}

	@Test(priority = 3)
	public void bodyNotNull() {

		sf.assertTrue(!responseObj.body().asString().equals(null));
	}

	@Test(priority = 4)
	public void attributeNameurl() {

		sf.assertTrue(responseObj.contentType().contains("url"));
	}

	@Test(priority = 5)
	public void attributeValueURL() {
		JsonPath jpobj = responseObj.jsonPath();
		//System.out.println("URL ==" + jpobj.get("url"));
		sf.assertEquals(jpobj.get("url"), "https://httpbin.org/get",
				"Actual = " + jpobj.get("url") + " and expected = " + "https://httpbin.org/get");
		

	}

	@AfterTest
	public void tearDown() {
		sf.assertAll();
	}

}
