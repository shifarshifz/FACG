package com.shifz.facg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Class used to download HTML data from the given url
 * @author Shifar Shifz
 *
 */
public class HtmlGrabber {
	
	private String url;
	private static final String LOCAL_HTML_FILE_PATH=  "fa_cheatsheet_html.txt";
	
	public HtmlGrabber(String url){
		this.url = url;
	}
	
	public String getHtml(boolean isFromOnline) throws Exception{
		
		StringBuilder htmlBuilder = new StringBuilder();
		String htmlLine = null;
		
		if(isFromOnline){
			
			long startTime = System.currentTimeMillis();
			System.out.println("Html grabbing started on "+startTime);
			
			URL obUrl = new URL(url);
			BufferedReader br = new BufferedReader(new InputStreamReader(obUrl.openStream()));
			
			while((htmlLine = br.readLine())!=null){
				htmlBuilder.append(htmlLine);
			}
			
			br.close();
			
			int finishedInSec = (int) (System.currentTimeMillis() - startTime) / 1000;
			
			System.out.println("Html grabbing finished after "+finishedInSec+" seconds");
			
		}else{
			
			//Collect from local
			File f = new File(LOCAL_HTML_FILE_PATH);
			BufferedReader br = new BufferedReader(new FileReader(f));
			while((htmlLine = br.readLine())!=null){
				htmlBuilder.append(htmlLine);
			}
			
			br.close();
			
		}
		
		return htmlBuilder.toString();
	}
	
}
