package com.qa.util;

import org.json.JSONArray;
import org.json.JSONObject;

public class ParsingUtil {
	
	public String getValueByJsonPah(JSONObject jsonResponse, String jpath){
		
		Object obj=jsonResponse;
		for(String str:jpath.split("/"))
		{
			if(!str.isEmpty()){
				
				if(!(str.contains("[")||str.contains("]"))){
					obj=((JSONObject) obj).get(str);
					
				}else if(str.contains("[")||str.contains("]")){
					
					obj= ((JSONArray) ((JSONObject) obj).get(str.split("\\[")[0])).get(Integer.parseInt(str.split("\\[")[1].replace("]", "")));
					
					
				}
			}
			
		}
		return obj.toString();
	}

}
