package ui.pages.repo;

import ui.BrowserElement;
import ui.UiBase;

public class SecurityRolePageRepo extends UiBase {
	protected BrowserElement addNewSecurityLnk() {
		return findByLinkText("Add New Security Role");
	}
	protected BrowserElement printTableBorderTxt() {
		return findByClass("PRINTTableBorder");
	}
	protected BrowserElement continueSecurityRoleLnk() {
		return findByLinkText("Continue");
	}
	protected BrowserElement securityRolesTbl() {
		return findById("tblReport");
	}
	protected BrowserElement returnlnk() {
		return findByLinkText("Return to User Menu");
	}
	//
	//

}
