package com.shifz.facg;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Main {

	public static void main(String[] args) throws Exception{
		
		System.out.println("Starting FACG...");
		
		FACG mFacg = FACG.getInstance();
		String cheatCodes = mFacg.getXmlEnum();
	
		File f = new File("enums.xml");
		if(!f.exists()){
			System.out.println("Building enums.xml ...");
			f.createNewFile();
		}
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		bw.write(cheatCodes);
		bw.flush();
		bw.close();
		
		System.out.println("File saved as 'enums.java'");
	}
}
