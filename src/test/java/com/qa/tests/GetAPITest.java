package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetAPITest extends TestBase {
	
	TestBase testBase;
	String serviceUrl;
	String apiUrl;
	String url;
	RestClient restClient;
	CloseableHttpResponse httpResponse;
	
	
	
	@BeforeMethod
	public void setUp() 
	{
		testBase =  new TestBase();
		 serviceUrl = prop.getProperty("URL");
		 apiUrl= prop.getProperty("serviceURL");
		
		 url= serviceUrl+apiUrl;
		
		
		
	}
	
	@Test(priority=1)
	public void getAPITestWithoutHeaders() throws ClientProtocolException, IOException
	{
	    restClient = new RestClient();
	    httpResponse = restClient.get(url);
		
		 
		 //a. Toget status code
		 int statusCode= httpResponse.getStatusLine().getStatusCode();
		 System.out.println("Status code--->"+statusCode);
		 
		 Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200,"Status code is 200");
		 
		 //To get Json respone in string format
		 String responseString = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
		 
		 //JSON object
		 JSONObject responseJson = new JSONObject(responseString);
		 System.out.println("ResponseJSON from API----->"+responseJson);
		 
		 //vale of per_page in JSON response
		 //Single value assertion
		 String perPageVal = TestUtil.getValueByJPath(responseJson, "/per_page");
		 System.out.println("Per page value--->"+perPageVal);
		 Assert.assertEquals(Integer.parseInt(perPageVal),6);
		 
		 
		 String perTotalVal = TestUtil.getValueByJPath(responseJson, "/total");
		 System.out.println("Per total  value--->"+perTotalVal);
		 Assert.assertEquals(Integer.parseInt(perTotalVal),12);
		 
		 //total_pages
		 
		 String totalpages = TestUtil.getValueByJPath(responseJson, "/total_pages");
		 System.out.println(" total pages  value--->"+totalpages);
		 Assert.assertEquals(Integer.parseInt(totalpages),2);
		 
		 //get the value from JSON Array
		 String lastName = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
		 String id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
		 String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
		 String firstName = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
		 
		 System.out.println(lastName);
		 System.out.println(firstName);
		 System.out.println(id);
		 System.out.println(avatar);
		 
		 
		 
		 //to get AllHeaders
		 Header[] headerArray = httpResponse.getAllHeaders();
		 
		 HashMap<String, String> allHeaders = new HashMap<String, String>();
		 for(Header header : headerArray)
		 {
			 allHeaders.put(header.getName(),header.getValue());
		 }
		 
		 System.out.println("All Headers----->"+allHeaders);
		 
		
	}

	@Test(priority=2)
	public void getAPITestWithHeaders() throws ClientProtocolException, IOException
	{
	    restClient = new RestClient();
	    
	    HashMap<String,String> headerMap = new HashMap<String, String>();
	    headerMap.put("Content-Type", "application/json");
	    
	    httpResponse = restClient.get(url,headerMap);
		
		 
		 //a. Toget status code
		 int statusCode= httpResponse.getStatusLine().getStatusCode();
		 System.out.println("Status code--->"+statusCode);
		 
		 Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200,"Status code is 200");
		 
		 //To get Json respone in string format
		 String responseString = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
		 
		 //JSON object
		 JSONObject responseJson = new JSONObject(responseString);
		 System.out.println("ResponseJSON from API----->"+responseJson);
		 
		 //vale of per_page in JSON response
		 //Single value assertion
		 String perPageVal = TestUtil.getValueByJPath(responseJson, "/per_page");
		 System.out.println("Per page value--->"+perPageVal);
		 Assert.assertEquals(Integer.parseInt(perPageVal),6);
		 
		 
		 String perTotalVal = TestUtil.getValueByJPath(responseJson, "/total");
		 System.out.println("Per total  value--->"+perTotalVal);
		 Assert.assertEquals(Integer.parseInt(perTotalVal),12);
		 
		 //total_pages
		 
		 String totalpages = TestUtil.getValueByJPath(responseJson, "/total_pages");
		 System.out.println(" total pages  value--->"+totalpages);
		 Assert.assertEquals(Integer.parseInt(totalpages),2);
		 
		 //get the value from JSON Array
		 String lastName = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
		 String id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
		 String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
		 String firstName = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
		 
		 System.out.println(lastName);
		 System.out.println(firstName);
		 System.out.println(id);
		 System.out.println(avatar);
		 
		 
		 
		 //to get AllHeaders
		 Header[] headerArray = httpResponse.getAllHeaders();
		 
		 HashMap<String, String> allHeaders = new HashMap<String, String>();
		 for(Header header : headerArray)
		 {
			 allHeaders.put(header.getName(),header.getValue());
		 }
		 
		 System.out.println("All Headers----->"+allHeaders);
		 
		
	}

	
	
}
