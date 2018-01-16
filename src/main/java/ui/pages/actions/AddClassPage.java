package ui.pages.actions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.support.ui.Select;

import ui.BrowserDriver;
import ui.pages.repo.AddClassPageRepo;

public class AddClassPage extends AddClassPageRepo {
	BrowserDriver driver;

	public AddClassPage(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);
	}

	// 16|-05:00|1|1|Second Sunday March 02:00:00|First Sunday November 02:00:00
	public ClassesPage addClass() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		recordScreenIframeSwitch();
		startDate().sendKeys(date1);
		endDate().sendKeys(date1);
		new Select(timeZonedrp())
				.selectByValue("16|-05:00|1|1|Second Sunday March 02:00:00|First Sunday November 02:00:00");
		new Select(endTime()).selectByValue("03");
		saveClass().click();
		return new ClassesPage(driver);
	}
}
