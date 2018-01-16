package ui.pages.actions.sections;

import ui.BrowserDriver;
import ui.pages.actions.DefineEventLogReportPage;
import ui.pages.repo.sections.NewEventLogReports_LogsPageSectionRepo;

public class LogsPageSection_NewEventLogReports extends NewEventLogReports_LogsPageSectionRepo {
	BrowserDriver driver;

	public LogsPageSection_NewEventLogReports(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);

	}

	public DefineEventLogReportPage openEventLogReport() {
		recordScreenIframeSwitch();
		// staticWait(1000);
		eventLogReportlnk().click();

		return new DefineEventLogReportPage(driver);
	}
}
