package ui.pages.actions;

import org.openqa.selenium.support.PageFactory;

import ui.BrowserDriver;
import ui.pages.actions.overlays.CreateGroup_UsersPageOverlay;
import ui.pages.actions.sections.AdminNav_CommonSection;
import ui.pages.actions.sections.UsersPageSection_ManageUsers;
import ui.pages.actions.sections.UsersPageSection_NewUserReports;
import ui.pages.actions.sections.UsersPageSection_SearchUsers;
import ui.pages.repo.UsersPageRepo;

public class UsersPage extends UsersPageRepo {
	BrowserDriver driver;
	UsersPageSection_ManageUsers manageUsers_usersPage;
	UsersPageSection_NewUserReports newUserReports_usersPage;
	UsersPageSection_SearchUsers searchUsers_usersPage;
	AdminNav_CommonSection adminNav_CommonSection;

	public UsersPage(BrowserDriver driver) {
		this.driver = driver;
		manageUsers_usersPage = new UsersPageSection_ManageUsers(driver);
		newUserReports_usersPage = new UsersPageSection_NewUserReports(driver);
		searchUsers_usersPage = new UsersPageSection_SearchUsers(driver);
		adminNav_CommonSection= new AdminNav_CommonSection(driver);
		setDriver(driver);
		PageFactory.initElements(driver, this);
	}

	public CreateGroup_UsersPageOverlay openCreateNewGroup() {
		manageUsers_usersPage.openCreateNewGroup();
		return new CreateGroup_UsersPageOverlay(driver);
	}

	public GroupManagementPage createNewGroup(Object... parameters) {
		String groupName = (String) parameters[0];
		openCreateNewGroup().createNewGroup(groupName);
		return new GroupManagementPage(driver);
	}

	public AddNewUserPage openAddUser() {
		return manageUsers_usersPage.openAddUser();
	}
	public UserManagementPage searchUser(String userName) {
		return searchUsers_usersPage.searchUser(userName);
	}
	public SecurityRolePage openSecurityRoles() {
		return manageUsers_usersPage.openSecurityRoles();
	}

	public boolean isUsersPage() {
		return manageUsers_usersPage.isManageUsersSectionLoaded();
	}

	public TrainingPage openTrainingPage() {
		return adminNav_CommonSection.openTrainingPage();
	}

	public OptionsPage openOptionsPage() {
		adminNav_CommonSection.openOptionsPage();
		return new OptionsPage(driver);
	}

	public UserManagementPage searchGroup(String groupName) {
		return searchUsers_usersPage.searchGroup(groupName);
		
	}

	public String getSearchForGroupTxt() {
		 popUpIframeViaRecordScreenIframeSwitch();
		 return groupOverlayTxt().getText();
	}

	public AssignmentPage openAssignmentPage() {
		return adminNav_CommonSection.openAssignmentPage();
		
	}

	public HomePage openKnowledgeCenter() {
		return adminNav_CommonSection.openKnowledgeCenter();
		
	}
}
