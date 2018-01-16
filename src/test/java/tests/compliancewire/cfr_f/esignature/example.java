package tests.compliancewire.cfr_f.esignature;

import ui.utils.Tools;

public class example {

	public static void main(String[] args) {

		String S = "Genz, Pinaa dr (whitetiger780) - 1/9/2018 01:04:57 PM - Policy Change";
		String[] parts = S.split("-");
		System.out.println(parts[0].trim()); // 004
		System.out.println(parts[1].trim());
		String aS=Tools.getDateTime();
		System.out.println(aS);
		/*
		 * // Create object of SimpleDateFormat class and decide the format DateFormat
		 * dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm ");
		 * 
		 * //get current date time with Date() Date date = new Date();
		 * 
		 * // Now format the date String date1= dateFormat.format(date);
		 * 
		 * // Print the Date System.out.println(date1);
		 * 
		 * TimeZone tz = Calendar.getInstance().getTimeZone();
		 * 
		 * System.out.println("TimeZone: "+tz.getDisplayName());
		 * System.out.println("ID: "+tz.getID());
		 * 
		 * ZonedDateTime zoneNow =
		 * ZonedDateTime.now(TimeZone.getTimeZone("America/New_york").toZoneId() );
		 * System.out.println(zoneNow);
		 * 
		 * 
		 * Date time = Calendar.getInstance().getTime(); SimpleDateFormat outputFmt =
		 * new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 * outputFmt.setTimeZone(TimeZone.getTimeZone("UTC")); String s=
		 * outputFmt.format(time); System.out.println(s);
		 */

	}

}
