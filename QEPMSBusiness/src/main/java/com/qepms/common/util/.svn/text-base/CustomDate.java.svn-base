package com.qepms.common.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomDate {
	
	public static Date getCustomDate()
	{
		return new Date();
	}
	
	public static Date getCustomDateTime() throws ParseException
	{
		
				 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				 Date date = new Date();
			     String dateString=	 formatter.format(date);
			     return formatter.parse(dateString);

	}

}
