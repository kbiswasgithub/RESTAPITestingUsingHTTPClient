package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.qa.util.ParsingUtil;

public class RestClient {
	
	//GET Method
	public void GET(String strURL) throws ClientProtocolException, IOException{
		
		CloseableHttpClient httpClient=HttpClients.createDefault();
		HttpGet httpGet=new HttpGet(strURL);
		CloseableHttpResponse httpResponse=httpClient.execute(httpGet);
		
		
		int statusCode=httpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code->"+ statusCode);
		
		String jsnResponse=EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
		
		JSONObject json=new JSONObject(jsnResponse);
		System.out.println("Response Json->"+json );
		
		Header[] hdr=httpResponse.getAllHeaders();
		HashMap<String,String> objHas=new HashMap<String,String>();
		
		for(Header hdd:hdr){
			
			objHas.put(hdd.getName(), hdd.getValue());
		}
		
		System.out.println("Headers->"+objHas);
		String str2=new ParsingUtil().getValueByJsonPah(json, "/data[0]/last_name");
		
		System.out.println("Output->"+ str2);
		
		
	}
	
	public CloseableHttpResponse POST(String strURL,String entityString, HashMap<String, String> objheaderMap) throws ClientProtocolException, IOException{
		
		CloseableHttpClient httpClientPost=HttpClients.createDefault();
		HttpPost httpPostObj=new HttpPost(strURL);
		httpPostObj.setEntity(new StringEntity(entityString));
		
		
		//Headers
		for(Map.Entry<String, String> entry: objheaderMap.entrySet()){
			httpPostObj.addHeader(entry.getKey(), entry.getValue());
			
		}
		
		CloseableHttpResponse httpPostResponse=httpClientPost.execute(httpPostObj);
		return httpPostResponse;
		
	}

}
