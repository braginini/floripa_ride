package com.floriparide.mobfloripaparser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Mikhail Bragin
 */
public class TimeTest {

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
		Date date = sdf.parse("05:33");
		java.util.Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, 5);
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		String time = df.format(calendar.getTime());
		calendar.add(Calendar.MINUTE, 10);
		time = df.format(calendar.getTime());
		calendar.add(Calendar.MINUTE, 30)       ;
		time = df.format(calendar.getTime());

	}
}
