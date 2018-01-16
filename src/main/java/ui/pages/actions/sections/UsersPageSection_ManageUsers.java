package ui.pages.actions.sections;

import ui.BrowserDriver;
import ui.pages.actions.AddNewUserPage;
import ui.pages.actions.SecurityRolePage;
import ui.pages.actions.overlays.CreateGroup_UsersPageOverlay;
import ui.pages.repo.sections.ManageUsers_UsersPageSectionRepo;

public class UsersPageSection_ManageUsers extends ManageUsers_UsersPageSectionRepo {
	BrowserDriver driver;

	public UsersPageSection_ManageUsers(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);

	}

	public CreateGroup_UsersPageOverlay openCreateNewGroup() {
		recordScreenIframeSwitch();
		createNewGrplink().click();
		switchToDefaultFrame();
		return new CreateGroup_UsersPageOverlay(driver);
	}

	public AddNewUserPage openAddUser() {
		recordScreenIframeSwitch();
		addUser().click();
		switchToDefaultFrame();
		return new AddNewUserPage(driver);
	}

	public SecurityRolePage openSecurityRoles() {
	recordScreenIframeSwitch();
		securityRoles().click();
		switchToDefaultFrame();
		return new SecurityRolePage(driver);
	}

	public boolean isManageUsersSectionLoaded() {
		recordScreenIframeSwitch();
		boolean pageState = createNewGrplink().isDisplayed();
		switchToDefaultFrame();
		return pageState;
	}
}
