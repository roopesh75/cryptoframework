package ui.pages.repo.overlays;

import ui.BrowserElement;
import ui.UiBase;

public class GroupManagementPage_AddUsersDirectlyOverlayRepo extends UiBase {
	protected BrowserElement userIdTxtBox() {
		return findByName("UserID");
	}
	protected BrowserElement addUsersLnk() {
		return findByLinkText("Add Users");
	}
	//
	protected BrowserElement searchLnk() {
		return findByLinkText("Search");
	}
	protected BrowserElement userNameChkBox(String userName) {
		return findByXpath("//table[@class='PRINTTableBorder']//td[text()='"+userName+"']/preceding::input[1]");
	}
}
