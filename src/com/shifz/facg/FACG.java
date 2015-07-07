package com.shifz.facg;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Singleton class to generate FontAwesomeCheatcodes
 * FACG = Font Awesome Cheatsheet Generator
 * @author Shifar Shifz
 *
 */
public class FACG {
	
	private static final String CHEATSEET_URL = "http://fortawesome.github.io/Font-Awesome/cheatsheet/",
			STRING_RESOURCE_FORMAT = "\t\t<string name=\"%s\">%s</string>\n",
			XML_RESOURCE_FORMAT = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n\t<resources>\n %s </resources>",
			MAIN_DELIMETER = "<div class=\"col-md-4 col-sm-6 col-lg-3\">",
			NODES_DELIMETER = "<i class=\"fa fa-fw\">",
			KEY_VALUE_DELIMETER = "</i>",
			FINAL_VALUE_DELIMETER = "<span class=\"muted\">",
			FINAL_VALUE_DELIMETER_2 = " <span class=\"text-muted\">";
			;
	
	
	//Static instance
	private static FACG instance;
	
	//Securing exterior object creation
	private FACG(){};
	
	//Getting instance of FACG
	public static FACG getInstance(){
		if(instance==null){
			instance = new FACG();
		}
		return instance;
	}

	//Generating cheatcodes
	public String getXmlString() throws Exception {
		
		String html = new HtmlGrabber(CHEATSEET_URL).getHtml(false);
		
		//Return cheat as key-value pair
		Map<String, String> cheatHash = getCheatHash(html);
		
		//To store xml formatted code
		StringBuilder cheatCodesXml =  new StringBuilder();
		
		//Creating each string resource
		for(Entry<String, String> entry: cheatHash.entrySet()){
			cheatCodesXml.append(String.format(STRING_RESOURCE_FORMAT, entry.getKey(),entry.getValue()));
		}
	
		//Creating final xml format
		String finalXml = String.format(XML_RESOURCE_FORMAT, cheatCodesXml.toString());
		
		//returning xml
		return finalXml;
	}

	private Map<String, String> getCheatHash(String html) {
		
		Map<String,String> cheatHash = new LinkedHashMap<>();
		
		html = html.replaceAll("\\s{2,}", " ");
		
		//Generating key-value pair from html
		
		String[] nodes = html.split(MAIN_DELIMETER);
		System.out.println( (nodes.length-1) + " icons found");
		
		
		for(int i=1;i<nodes.length;i++){
			String o12 = nodes[i].split(NODES_DELIMETER)[1];
			String value = o12.split(KEY_VALUE_DELIMETER)[0]+";";
			String key = o12.split(KEY_VALUE_DELIMETER)[1];
			String finalDel = key.contains(FINAL_VALUE_DELIMETER_2) ? FINAL_VALUE_DELIMETER_2 : FINAL_VALUE_DELIMETER;
			key = key.split(finalDel)[0];
			key = key.replace(" ","");
			cheatHash.put(key, value);
		}
	
		
		return cheatHash;
	}
	

	
}
