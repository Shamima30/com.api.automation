package com.terstRun;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostFunction {
	
	
	
	Response responseObj;
	   SoftAssert sf = new SoftAssert();
		
		@BeforeTest
		public void setup() {
			JsonObject jo = new JsonObject();
			jo.addProperty("Name", "Shamima");
			jo.addProperty("Salary", 5000);
			RequestSpecification rs = RestAssured.given();
			rs.body(jo.toString());
			rs.header("Content-Type","application/json");
			 responseObj = rs.post("https://httpbin.org/post");
			
			
			
		}
		
		@Test(priority = 0)
		public void test1() {
			
			sf.assertEquals(responseObj.statusCode(), 200);
				
		} 
		@Test(priority = 1, description = "check rersponse time<2000",groups = "Unit testing")
		public void test2() {
			
			sf.assertTrue(responseObj.time()<2000);
				} 
		@Test(priority = 2, description = "Data format check as json",groups = "Unit testing")
		public void test3() {
			
			sf.assertTrue(responseObj.contentType().contains("application/json"));
				} 
		@Test(priority =3, description = "response body should not be empty",groups = "Unit testing")
		public void test4() {
			
			sf.assertTrue( ! responseObj.body().asString().equals(null));
				} 
		@Test(priority = 4, description = "Attribute name should be there",groups = "Unit testing")
		public void test5() {
			
			sf.assertTrue(responseObj.body().asString().contains("Name"));
				} 
		@Test(priority = 5, description = "Attribute Salary should be there",groups = "Unit testing")
		public void test6() {
			
			sf.assertTrue(responseObj.body().asString().contains("Salary"));
				} 	
		
		
		@Test(priority =6, description = "Attribute Name Value check",groups = "Unit testing")
		public void test7() {
			JsonPath jp =responseObj.jsonPath();
			
			sf.assertEquals(jp.get("json.Name"), "Shamima");
				} 	
		@Test(priority =7, description = "Attribute Salary Value check",groups = "Unit testing")
		public void test8() {
			JsonPath jp =responseObj.jsonPath();
			
			sf.assertEquals(jp.get("json.Salary"), 5000);
			
				} 	
		
		
		
		
		
		
		@AfterTest
		public void tearDown() {
			sf.assertAll();
		}
		
	
	
	

}
