package br.ufmg.dcc.pm.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	public static Date addDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days); // minus number would decrement the days
		return cal.getTime();
	}

	public static Date getCurrentDate() { 
		// get current date time with Calendar()
		Calendar cal = Calendar.getInstance();
		return cal.getTime(); 
	}
	
	public static String formatData(Date date, String format){
		DateFormat dateFormat = new SimpleDateFormat(format); 
		return dateFormat.format(date);
	}
}