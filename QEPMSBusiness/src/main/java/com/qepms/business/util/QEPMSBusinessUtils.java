package com.qepms.business.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import com.qepms.common.util.Format;

public class QEPMSBusinessUtils {

	public static void copyProperties(Object dest, Object orig) {
		try {
			PropertyUtils.copyProperties(dest, orig);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void copyProperties(List<Object> destList,
			List<Object> origList) {
		int size = origList.size();
		try {
			for (int i = 0; i < size; i++) {
				PropertyUtils.copyProperties(destList.get(i), origList.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static String convertedDate(Date date,String format)
	{
		String dateFormat =null;
		if(format.trim().equals(Format.ddMMyyyy.name()))
		{
			dateFormat="dd-MM-YYYY";
		}
		
		
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		String stringDate = sdf.format(date );
		return stringDate;
		
	}
	
}
