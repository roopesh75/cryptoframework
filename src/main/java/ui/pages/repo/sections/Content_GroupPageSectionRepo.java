package ui.pages.repo.sections;

import ui.BrowserElement;
import ui.UiBase;

public class Content_GroupPageSectionRepo extends UiBase {

	protected BrowserElement userCount() {
		return findByXpath("//tr[td='Current User Count (Total):']/td[2]");
	}
}
