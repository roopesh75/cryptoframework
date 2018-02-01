package ui.pages.repo;

import org.openqa.selenium.By;

import ui.BrowserElement;
import ui.BrowserElementImpl;
import ui.UiBase;

public class OptionsManagementPageRepo extends UiBase {
	protected BrowserElement resetForgottenPasswordLnk() {
		return findByLinkText("Reset Forgotten Password option");
	}

	protected BrowserElement resetForgotPasswordRadioBtn(int index) {
		return new BrowserElementImpl(getDriver().findElements(By.name("RESETFORGOTTENPWD")).get(index),
				getDriver().getWebDriver());
	}

	protected BrowserElement forceUsersChkBox() {
		return findByName("PasswordSecQue");
	}

	protected BrowserElement saveLnk() {
		return findByLinkText("Save");
	}
	protected BrowserElement midlevelOrganizationLnk(String orgName) {
		return findByLinkText(orgName);
	}
	protected BrowserElement selectedOrganizationLnk() {
		return findByXpath("//*[contains(text(),'Selected Organization')]/following::td[1]");
	}
	
	protected BrowserElement returnToOptionsLnk() {
		return findByLinkText("Return to Options");
	}
}
