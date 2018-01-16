package ui.pages.repo.sections;

import ui.BrowserElement;
import ui.UiBase;

public class NewEventLogReports_LogsPageSectionRepo extends UiBase {

    protected BrowserElement eventLogReportlnk() {
        return findByLinkText("Event Log Report");
    }
}
