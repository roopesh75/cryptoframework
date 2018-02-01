package ui.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;

import ui.BrowserElementImpl;
import ui.support.Config;

public class Tools {
	private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(BrowserElementImpl.class);

	public static synchronized void runUGM() {
		try {
			String result = new HTTPClient().get(Config.getToolsURL() + "/ugm").toLowerCase().indexOf("success") >= 0
					? "UGM Ran Successfully"
					: "UGM Failed";
			// logger.info("ThreadID: "+Thread.currentThread().getId()+"
			// "+Thread.currentThread().getName()+" "+(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Deprecated
	public static String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy hh:");
		Date date = new Date();
		return dateFormat.format(date);

	}

	public static String getDateTime(String format) {
		return new SimpleDateFormat(format).format(new Date());

	}

	@Deprecated
	public static String getDateTime1() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:");
		Date date = new Date();

		return dateFormat.format(date);

	}

	public static String getSystemTimeZone(boolean modify) {
		String timeZone = DateTime.now().toString().substring(DateTime.now().toString().length() - 6,
				DateTime.now().toString().length());
		if (modify) {
			if (Integer.parseInt(timeZone.split(":")[0].substring(1)) < 10) {
				return timeZone.split(":")[0].substring(0, 1) + timeZone.split(":")[0].substring(2, 3) + ":"
						+ timeZone.split(":")[1];
			}
		}
		return timeZone;
	}

	public static String getCurrentDate(String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static String getMilliSecondsForDate(String format, String date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		try {
			return simpleDateFormat.parse(date).getTime() + "";
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "0";
	}

}
