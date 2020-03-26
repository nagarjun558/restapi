package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;



public class PostAPITest  extends TestBase{
	
	TestBase testBase;
	String serviceUrl;
	String apiUrl;
	String url;
	RestClient restClient;
	CloseableHttpResponse httpResponse;
	
	
	 
	@BeforeMethod
	public void setUp() 
	{
		testBase= new TestBase();
		serviceUrl= prop.getProperty("URL");
		apiUrl    = prop.getProperty("serviceURL");
		
		url= serviceUrl+apiUrl;
		
	}
	
	@Test
	public void postAPITest() throws JsonGenerationException, JsonMappingException, IOException
	{
		restClient = new RestClient();
		
		HashMap<String,String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		
		//Jackson API
		
		ObjectMapper mapper=  new ObjectMapper();
		Users users =  new Users("morpheus","leader");
		
		//Object to JSON format //Marshalling
		mapper.writeValue(new File("C:\\Users\\Nagarjun\\workspace\\restapi\\src\\main\\java\\com\\qa\\data\\users.json"),users);

		//object to JSon in String
		String usersJsonString = mapper.writeValueAsString(users);
		System.out.println(usersJsonString);
		
		httpResponse = restClient.post(url, usersJsonString, headerMap);
		
		//validate from API
		//to get Status
		int statusCode=httpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code--->"+statusCode);
		Assert.assertEquals(statusCode,RESPONSE_STATUS_CODE_201);
		
		//2.JSON String
		String responseString = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
		
		JSONObject resposnseJson =new JSONObject(responseString);
		System.out.println("Response form JSON --->"+resposnseJson);
		
		//JSON to Java Object Un Marshalling
		Users userResponseObj = mapper.readValue(responseString,Users.class); //actual users object
		System.out.println(userResponseObj);
		
		Assert.assertFalse(users.getName().equals(userResponseObj.getName()));
		Assert.assertFalse(users.getJob().equals(userResponseObj.getJob()));
		
		
		
	}
	
		
		
		
	

}
