package com.supinfo.suplink.function;


public class GenerateId {
	
	public static String getId(String string)
	{
		string = MD5.getHash(string);
		String[] stringList = string.split("(?<=\\G....)");
		int index = (int)(Math.random() * (7-0)) + 0;
		
		return stringList[index];
		
	}
}
