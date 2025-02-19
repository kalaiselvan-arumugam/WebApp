package com.webapp.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DateTimeHelper {

	private static final Logger logger = LogManager.getLogger(DateTimeHelper.class);

	public String getCurrentDateAndTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:MM:ss");
		dateFormat.setTimeZone(TimeZone.getTimeZone(ApplicationConfiguration.getTag(Constants.TIME_ZONE)));
		return dateFormat.format(new Date());
	}

	public String getCurrentDate(String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		dateFormat.setTimeZone(TimeZone.getTimeZone(ApplicationConfiguration.getTag(Constants.TIME_ZONE)));
		return dateFormat.format(new Date());
	}

	public static Timestamp convertStringToTimestamp(String dateString, String inputDateformat) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(inputDateformat);
		dateFormat.setTimeZone(TimeZone.getTimeZone(ApplicationConfiguration.getTag(Constants.TIME_ZONE)));
		try {
			Date parsedDate = dateFormat.parse(dateString);
			return new Timestamp(parsedDate.getTime());
		} catch (ParseException ex) {
			logger.error("Exception while parsing timestamp : {}", StackTrace.getMessage(ex));
			return null;
		}
	}

	public Timestamp formatAndParseTimestamp(Timestamp timestamp) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		dateFormat.setTimeZone(TimeZone.getTimeZone(ApplicationConfiguration.getTag(Constants.TIME_ZONE)));
		String formattedDateStr = dateFormat.format(timestamp);
		try {
			Date parsedDate = dateFormat.parse(formattedDateStr);
			Timestamp formattedTimestamp = new Timestamp(parsedDate.getTime());
			formattedTimestamp.setNanos(0);
			return formattedTimestamp;
		} catch (ParseException ex) {
			logger.error("Exception while formating date : {}", StackTrace.getMessage(ex));
			return timestamp;
		}
	}

	public java.sql.Date convertToSqlDate(String dateStr, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		dateFormat.setTimeZone(TimeZone.getTimeZone(ApplicationConfiguration.getTag(Constants.TIME_ZONE)));
		try {
			return (java.sql.Date) dateFormat.parse(dateStr);
		} catch (Exception ex) {
			logger.error("Exception while converting java date to sql date : {}", StackTrace.getMessage(ex));
			return null;
		}
	}

}