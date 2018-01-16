package ui.pages.actions.sections;

import org.openqa.selenium.support.PageFactory;

import ui.BrowserDriver;
import ui.pages.actions.AddNewUserPage;
import ui.pages.actions.UserManagementPage;
import ui.pages.actions.UsersPage;
import ui.pages.repo.sections.UserNavigation_UserManagementPageSectionRepo;

public class UserManagementPageSection_UserNavigation extends UserNavigation_UserManagementPageSectionRepo {
	BrowserDriver driver;

	public UserManagementPageSection_UserNavigation(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);
		PageFactory.initElements(driver, this);
	}

	public UserManagementPage openGeneralInformation() {
		recordScreenIframeSwitch();
		generalInformation().click();
		return new UserManagementPage(driver);
	}

	public UserManagementPage openHistory() {
		recordScreenIframeSwitch();
		staticWait(1500);
		history().click();
		return new UserManagementPage(driver);
	}

	public AddNewUserPage openEditUser() {
		recordScreenIframeSwitch();
		editUser().click();
		return new AddNewUserPage(driver);
	}

	public UsersPage returnFromUserManagement() {
		recordScreenIframeSwitch();
		staticWait(1500);
		returnlnk().click();
		return new UsersPage(driver);
	}

	public UserManagementPage openSecurityRoles() {
		recordScreenIframeSwitch();
		securityRolesLnk().click();
		return new UserManagementPage(driver);
	}

	public UserManagementPage openAddUsersDirectly(String userName) {
		recordScreenIframeSwitch();
		addUsersDirectlyLnk().click();
		
		return new UserManagementPage(driver);
	}

}
