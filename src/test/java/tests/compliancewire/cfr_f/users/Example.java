package tests.compliancewire.cfr_f.users;

import initializer.BaseTest;

public class Example  extends BaseTest {

	public static void main(String[] args) {

		/*new SimpleDateFormat("MM/dd/yyyy").format(new java.util.Date());
		System.out.println(new SimpleDateFormat("MM/dd/yyyy").format(new java.util.Date()));*/
	//String s="Signature affd720193Usr, affd720193Usr (affd720193Usr) | 01/22/2018 03:31:10 PM UTC -05:00 | I Confirm"; 
		//String s="Signature 	Genz, Pinaa dr (heavybear790) - 1/22/2018 03:30:05 PM UTC-5:00 - Policy Change";
	//System.out.println(s.);

	/*
	System.out.println(Tools.getDateTime("MM/dd/yyyy hh:mm:ss a").split(" ")[0]);	
	
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
		/*double d=(float)0/50;
		System.out.println(d);
		int i= (d%1==0.0f)?(int)d:(int)(d+1);
		int j=1;
		System.out.println(""+j+"");*/
		String s="01/29/2018 04:19:24 PM UTC -05:00";
		System.out.println(s.substring(0, 22));
		//System.out.println(s.substring(13, 47));
		
		
	}

}