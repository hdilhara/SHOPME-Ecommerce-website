package com.hdilhara.shopme;

import java.io.File;

import org.apache.commons.io.FileUtils;

public class TestMe {
	public static void main(String[] args)  throws Exception{
		
//		File  file=new File("C:\\Users\\DILHARA\\Videos\\Captures\\tr.txt");
		String str="fdgfdgf";
		FileUtils.writeByteArrayToFile(new File(".\\txte.txt"), str.getBytes());
		
	}
}
