package com.shifz.facg;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Main {

	public static void main(String[] args) throws Exception{
		
		System.out.println("Starting FACG...");
		
		FACG mFacg = FACG.getInstance();
		
		String asXmlStrings = mFacg.getXmlString();
		String asXmlEnums = mFacg.getXmlEnum();
		String asJavaEnums = mFacg.getJavaEnum();
		
		saveFile("string.xml",asXmlStrings);
		saveFile("attrs.xml",asXmlEnums);
		saveFile("FaIcon.java",asJavaEnums);
		
		System.out.println("FACG Finished");
	}

	private static void saveFile(String fileName, String data) throws Exception {
	
		File f = new File(fileName);
		if(!f.exists()){
			f.createNewFile();
		}
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		bw.write(data);
		bw.flush();
		bw.close();
		
		System.out.println(fileName+" saved!");
	}
}
