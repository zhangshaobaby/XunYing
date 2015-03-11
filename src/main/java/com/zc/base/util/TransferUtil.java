package com.zc.base.util;

public class TransferUtil {

	public static String [] objectArrayToStringArray(Object []objs){
		
		String [] strs=new String[objs.length];
		for (int i=0;i<objs.length;i++) {
			strs[i]=(String) objs[i];
		}
		return strs;
	}
}
