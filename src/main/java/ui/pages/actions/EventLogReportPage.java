package ui.pages.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import ui.BrowserDriver;
import ui.BrowserElement;
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
		// adminNav_CommonSection.openUsersPage();
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

	public boolean areEventLogRowsPresent() {
		recordScreenIframeSwitch();
		return eventLogRows().size() > 1;
	}

	public boolean areUserIdsStartingWith(String... parameters) {
		recordScreenIframeSwitch();
		// System.out.println(eventLogRows().stream().map(e ->
		// e.findElements(By.tagName("td")).get(3).getText().toLowerCase().startsWith(parameters[0])).allMatch(e
		// -> e==true));
		return eventLogRows().stream()
				.map(e -> e.findElements(By.tagName("td")).get(3).getText().toLowerCase().startsWith(parameters[0]))
				.reduce((x, y) -> x && y).get().booleanValue();
	}

	public String getSelectedfrmReport() {
		recordScreenIframeSwitch();
		return new Select(reportDrpdwn()).getFirstSelectedOption().getText();
	}

	public void displayReport() {
		recordScreenIframeSwitch();
		float recordset = Float.parseFloat(rowsReturned().getText().split(" ")[5]);
		if (recordset > 50) {
			double d = recordset / 50;
			int i = (d % 1 == 0.0f) ? (int) d : (int) (d + 1);
			for (int j = 1; j <= i; j++) {
				new Select(selectGotoPageDrpdwn()).selectByValue(Integer.toString(j));
				scrollToView((BrowserElement) returnLogsMenu().get(1));
				returnLogsMenu().get(1).getText();
				scrollToView(rowsReturned());
				rowsReturned().getText();

			}
		}

	}

	public String getReport() {
		recordScreenIframeSwitch();
		return new Select(reportDrpdwn()).getFirstSelectedOption().getText();
	}

}
