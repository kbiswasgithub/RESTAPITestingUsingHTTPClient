package com.qa.test;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;
import com.qa.util.ParsingUtil;

public class APITest extends TestBase{

	TestBase testBase;
	String strURI;
	RestClient rstCl;
	CloseableHttpResponse httpResponsePost;
	
	@BeforeMethod
	public void setUP(){
		testBase=new TestBase();
		strURI=prop.getProperty("url")+prop.getProperty("serviceURL");
		
		
		
	}
	
	@Test(priority=0)
	public void SendGETReq() throws ClientProtocolException, IOException{
		System.out.println("I'm in get method");
		rstCl=new RestClient();
		rstCl.GET(strURI);
	}
	@Test(priority=1)
	public void SendPOSTReq() throws ClientProtocolException, IOException{
		System.out.println("I'm in POST method");
		rstCl=new RestClient();
		HashMap<String,String> objHeader=new HashMap<String,String>();
		objHeader.put("Content-Type", "application/json");
		
		//jackson API
		ObjectMapper objMapper=new ObjectMapper();
		Users users=new Users("RamKumarShyam", "IT", "1000");
		
		//object to jsonobject
		objMapper.writeValue(new File("C:\\Users\\Admin\\workspace\\APITesting\\src\\main\\java\\com\\qa\\data\\Users.json"), users);
		
		//object to json in string
		
		String str=objMapper.writeValueAsString(users);
		System.out.println("json as string->"+ str);
		
		httpResponsePost=rstCl.POST(strURI,str, objHeader);
		
		int statusCode=httpResponsePost.getStatusLine().getStatusCode();
		System.out.println("Status Code fo POST Call->"+ statusCode);
		
		String jsnResponse=EntityUtils.toString(httpResponsePost.getEntity(),"UTF-8");
		
		JSONObject json=new JSONObject(jsnResponse);
		System.out.println("Response Json->"+json );
		
		Header[] hdr=httpResponsePost.getAllHeaders();
		HashMap<String,String> objHas=new HashMap<String,String>();
		
		for(Header hdd:hdr){
			
			objHas.put(hdd.getName(), hdd.getValue());
		}
		
		System.out.println("Headers->"+objHas);
		
		Users uss=objMapper.readValue(jsnResponse, Users.class);
		
		//String str2=new ParsingUtil().getValueByJsonPah(json, "/RamKumar");
		
		//Json to java object
		
		
		
		System.out.println("POST Output->"+ uss);
		
		System.out.println("Name Compariosn->"+users.getName().equals(uss.getName()));
		
		System.out.println("job compariosn->" + users.getJob().equals(uss.getJob()));
		
	}
}
