package ui.pages.repo.sections;

import ui.BrowserElement;
import ui.UiBase;

public class UserNavigation_UserManagementPageSectionRepo extends UiBase {
	protected BrowserElement generalInformation() {
		return findByLinkText("General Information");
	}

	protected BrowserElement history() {
		return findByLinkText("History");
	}
	protected BrowserElement editUser() {
		return findByLinkText("Edit User");
	}

	protected BrowserElement returnlnk() {
		return findByLinkText("Return");
	}
	protected BrowserElement securityRolesLnk() {
		return findByLinkText("Security Roles");
	}
	protected BrowserElement addUsersDirectlyLnk() {
		return findByLinkText("Add Users directly");
	}
}
