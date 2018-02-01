package ui.pages.repo.sections;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import ui.BrowserElement;
import ui.UiBase;

public class Content_UserManagementPageSectionRepo extends UiBase {
	protected BrowserElement firstName() {
		return findByXpath("//tr[td='First Name:']/td[2]");
	}

	protected BrowserElement lastName() {
		return findByXpath("//tr[td='Last Name:']/td[2]");
	}

	protected WebElement CWBoxLbl() {
		return getDriver().findElements(By.className("CWBoxBody")).get(1);
	}

	protected BrowserElement groupNameLbl() {
		return findByXpath("//tr[td='User Group Name:']/td[2]");
	}

	protected BrowserElement userId() {
		return findByXpath("//tr[td='User Id:']/td[2]");
	}

	protected BrowserElement securityRole() {
		return findByXpath("//td[@class='EduText']/following::td[2]");
	}
	protected BrowserElement organizationEntity() {
		return findByXpath("//td[@class='EduText']/following::td[1]");
	}

	protected BrowserElement homeOrganization() {
		return findByXpath("//*[@title='Home Organization']/following::td[1]");
	}

	protected BrowserElement homePhone() {
		return findByXpath("//tr[td='Home Phone:']/td[2]");
	}
	protected BrowserElement organizationLbl() {
		return findByXpath("//*[@id='tblOrg']/tbody/tr[2]/td[1]");
	}
	//
}
