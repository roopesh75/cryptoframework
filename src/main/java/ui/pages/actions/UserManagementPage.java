package ui.pages.actions;

import org.openqa.selenium.support.PageFactory;

import ui.BrowserDriver;
import ui.pages.actions.sections.AdminNav_CommonSection;
import ui.pages.actions.sections.UserManagementPageSection_Content;
import ui.pages.actions.sections.UserManagementPageSection_UserAccountHistory;
import ui.pages.actions.sections.UserManagementPageSection_UserNavigation;
import ui.pages.repo.UserManagementPageRepo;

public class UserManagementPage extends UserManagementPageRepo {
	BrowserDriver driver;
	UserManagementPageSection_Content content_userManagementPage;
	UserManagementPageSection_UserNavigation userNavigation_userManagementPage;
	UserManagementPageSection_UserAccountHistory userAccountHistory_userManagementPage;
	AdminNav_CommonSection adminNav_CommonSection;
	
	public UserManagementPage(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);
		content_userManagementPage = new UserManagementPageSection_Content(driver);
		userNavigation_userManagementPage = new UserManagementPageSection_UserNavigation(driver);
		userAccountHistory_userManagementPage = new UserManagementPageSection_UserAccountHistory(driver);
		adminNav_CommonSection = new AdminNav_CommonSection(driver);
		PageFactory.initElements(driver, this);
	}

	public TrainingPage openTrainingPage() {
		return adminNav_CommonSection.openTrainingPage();
	}

	public UserManagementPage openHistory() {
		return userNavigation_userManagementPage.openHistory();
	}

	public AddNewUserPage openEditUser() {
		return userNavigation_userManagementPage.openEditUser();
	}
	public UsersPage returnFromUserManagement() {
		return userNavigation_userManagementPage.returnFromUserManagement();
	}

	public String getUserId() {
		return content_userManagementPage.getUserId();
	}

	public String getFirstName() {
		return content_userManagementPage.getFirstName();
	}

	public String getLastName() {
		return content_userManagementPage.getLastName();
	}

	public String getCreatedOn() {
		return userAccountHistory_userManagementPage.getCreatedOn();
	}

	public String getCreatedBy() {
		return userAccountHistory_userManagementPage.getCreatedBy();
	}
	public String getSecurityRole() {
		return content_userManagementPage.getSecurityRole();
	}
	public String getPropertyEdited(int tableId) {
		return userAccountHistory_userManagementPage.getPropertyEdited(tableId);
	}
	public String getOldValue(int tableId) {
		return userAccountHistory_userManagementPage.getOldValue(tableId);
	}
	public String getNewValue(int tableId) {
		return userAccountHistory_userManagementPage.getNewValue(tableId);
	}
	public String getHomeOrganization() {
		return content_userManagementPage.getHomeOrganization();
	}
	public LoginPage logOut() {
		return adminNav_CommonSection.logOut();
	}

	public String getUsersInSecurityRole() {
		recordScreenIframeSwitch();
		return userSecurityRoleTable().getText();
	}
	public UserManagementPage openSecurityRoles(){
		return userNavigation_userManagementPage.openSecurityRoles();
	}
	public String getHomePhone() {
		return content_userManagementPage.getHomePhone();
	}
	public UserManagementPage openGeneralInformation() {
		return userNavigation_userManagementPage.openGeneralInformation();
	}
}
