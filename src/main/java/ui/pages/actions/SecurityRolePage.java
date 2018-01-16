package ui.pages.actions;

import ui.BrowserDriver;
import ui.pages.actions.sections.AdminNav_CommonSection;
import ui.pages.repo.SecurityRolePageRepo;

public class SecurityRolePage extends SecurityRolePageRepo {
	BrowserDriver driver;
	AdminNav_CommonSection adminNav_CommonSection;

	public SecurityRolePage(BrowserDriver driver) {
		this.driver = driver;
		adminNav_CommonSection = new AdminNav_CommonSection(driver);
		setDriver(driver);
	}

	public SecuritySettingsPage addNewSecurityRole() {
		recordScreenIframeSwitch();
		addNewSecurityLnk().click();
		continueSecurityRoleLnk().click();
		return new SecuritySettingsPage(driver);
	}

	public SecuritySettingsPage chooseSecurityRole(String securityRole) {
		recordScreenIframeSwitch();
		findByLinkText(securityRole).click();
		switchToDefaultFrame();
		return new SecuritySettingsPage(driver);
	}

	public LoginPage logOut() {
		adminNav_CommonSection.logOut();
		return new LoginPage(driver);
	}

	public String getSecurityRoles() {
		recordScreenIframeSwitch();
		return securityRolesTbl().getText();
	}

	//
	public UsersPage returnFromSecurityRole() {
		recordScreenIframeSwitch();
		returnlnk().click();
		return new UsersPage(driver);
	}

	public boolean isSecurityRolePage() {
		recordScreenIframeSwitch();
		boolean pageState = addNewSecurityLnk().isDisplayed();
		switchToDefaultFrame();
		return pageState;
	}
}
