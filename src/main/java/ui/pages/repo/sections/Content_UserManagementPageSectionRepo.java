package ui.pages.repo.sections;

import ui.BrowserElement;
import ui.UiBase;

public class Content_UserManagementPageSectionRepo extends UiBase {
	protected BrowserElement firstName() {
		return findByXpath("//tr[td='First Name:']/td[2]");
	}

	protected BrowserElement lastName() {
		return findByXpath("//tr[td='Last Name:']/td[2]");
	}

	protected BrowserElement userId() {
		return findByXpath("//tr[td='User Id:']/td[2]");
	}
	protected BrowserElement securityRole() {
		return findByXpath("//td[@class='EduText']/following::td[2]");
	}
	protected BrowserElement homeOrganization() {
		return findByXpath("//*[@title='Home Organization']/following::td[1]");
	}
	protected BrowserElement homePhone() {
		return findByXpath("//tr[td='Home Phone:']/td[2]");
	}
}
