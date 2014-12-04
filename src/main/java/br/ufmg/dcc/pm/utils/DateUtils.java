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
	
	public static Date getBegginOfDay(Date data){
		return setHourMinuteSecond(data,0,0,0);
	}
	
	public static Date getEndOfDay(Date data){ 
		 return setHourMinuteSecond(data,23,59,59);
	}
	
	public static Date setHourMinuteSecond(Date data, int hour, int minute, int second){
		Calendar cal = Calendar.getInstance();
		 cal.setTime(data);
		 cal.set(Calendar.HOUR_OF_DAY, hour);
		 cal.set(Calendar.MINUTE, minute);
		 cal.set(Calendar.SECOND, second);
		 cal.set(Calendar.MILLISECOND, 0);
		 return cal.getTime();
	}
}