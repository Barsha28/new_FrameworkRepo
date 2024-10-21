package com.comcast.crm.generic.fileutility;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonUtility {
public String getDataFromJsonFile(String key) throws IOException, ParseException {
	FileReader fr=new FileReader("./configAppData/appCommonData.json");
	JSONParser parser=new JSONParser();
	Object obj=parser.parse(fr);
	JSONObject map=(JSONObject) obj;//for getting it in the form of key and value  we will downcast
	String data=map.get(key).toString();
	return data;
}
}
