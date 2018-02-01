package ui.pages.repo;

import ui.BrowserElement;
import ui.UiBase;

public class LogsPageRepo extends UiBase {

	protected BrowserElement majorUpgradeslnk() {
		return findByLinkText("Major Upgrades");
	}
}
