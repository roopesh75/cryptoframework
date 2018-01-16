package ui.pages.actions;

import ui.BrowserDriver;
import ui.pages.actions.sections.AdminNav_CommonSection;
import ui.pages.repo.EventLogReportPageRepo;

public class EventLogReportPage extends EventLogReportPageRepo {

	BrowserDriver driver;
	AdminNav_CommonSection adminNav_CommonSection;

	public EventLogReportPage(BrowserDriver driver) {
		this.driver = driver;
		adminNav_CommonSection = new AdminNav_CommonSection(driver);
		setDriver(driver);
	}

	public LoginPage logOut() {
		adminNav_CommonSection.logOut();
		return new LoginPage(driver);
	}

	public UsersPage openUsersPage() {
		//adminNav_CommonSection.openUsersPage();
		return new UsersPage(driver);
	}

	public boolean isEventLogReportPage() {
		return dateTimeSort().isDisplayed();

	}

	public EventLogReportPage sortByDateTime() {
		recordScreenIframeSwitch();
		dateTimeSort().click();
		return this;
	}

	public String getEventLogTopRow() {
		recordScreenIframeSwitch();
		String eventLogText = eventLogTopRow().getAttribute("innerText");

		return eventLogText;
	}

	public String getEventLogTopRowTime() {
		recordScreenIframeSwitch();
		return topRowDateTimeLbl().getText().trim();
	}
}
