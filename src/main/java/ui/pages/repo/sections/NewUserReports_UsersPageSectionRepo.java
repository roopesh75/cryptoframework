package ui.pages.repo.sections;

import ui.BrowserElement;
import ui.UiBase;

public class NewUserReports_UsersPageSectionRepo extends UiBase {
	protected BrowserElement completionReportLnk() {
		return findByLinkText("Completions Report by User");
	}
}
