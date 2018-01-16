package ui.pages.actions;

import org.openqa.selenium.support.PageFactory;

import ui.BrowserDriver;
import ui.pages.actions.sections.AdminNav_CommonSection;
import ui.pages.actions.sections.LogsPageSection_NewEventLogReports;
import ui.pages.repo.LogsPageRepo;

public class LogsPage extends LogsPageRepo {
	BrowserDriver driver;
	LogsPageSection_NewEventLogReports newEventLogReports_logsPageSection;
	AdminNav_CommonSection adminNav_CommonSection;

	public LogsPage(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);
		newEventLogReports_logsPageSection = new LogsPageSection_NewEventLogReports(driver);
		adminNav_CommonSection = new AdminNav_CommonSection(driver);
		PageFactory.initElements(driver, this);
	}

	public DefineEventLogReportPage openEventLogReport() {
		return newEventLogReports_logsPageSection.openEventLogReport();
	}

	public OptionsPage openOptionsPage() {
		adminNav_CommonSection.openOptionsPage();
		return new OptionsPage(driver);
	}
}
