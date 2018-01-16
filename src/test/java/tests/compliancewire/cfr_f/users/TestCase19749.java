package tests.compliancewire.cfr_f.users;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;
import initializer.DynamicDataProvider;

public class TestCase19749 extends BaseTest {
	private static final Logger logger = Logger.getLogger(TestCase19749.class);

	@Test(alwaysRun = true, dataProviderClass = DynamicDataProvider.class, dataProvider = "createAdminUser", 
			description = "Users_Group memberships_Manage group memberships", groups = {
			"cfr_f", "cfr_f.users"})
	public void Users_Group_memberships_Manage_group_memberships(String userName) throws Exception {

		loginPage.signIn(userName, getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openUsersPage();
		usersPage.openSecurityRoles()
				.chooseSecurityRole(getData("TC19749.SECURITY_ROLE"));
		Assert(true, "Precondition: Security Role Information");	
		securitySettingsPage.returnFromSecuritySettings().returnFromSecurityRole().searchGroup(getData("TC19749.GROUP_UPPERLVL"));
		Assert(true, "Precondition: Group Belonging to Upper Level");
		userManagementPage.returnFromUserManagement().searchGroup(getData("TC19749.GROUP_SAMELVL"));
		Assert(true, "Precondition: Group Belonging to Same Level");
		userManagementPage.returnFromUserManagement().searchGroup(getData("TC19749.GROUP_LOWERLVL"));
		Assert(true, "Precondition: Group Belonging to Lower Level");
		userManagementPage.logOut().signIn(getData("TC19749.USER_PASS"), getData("TC19749.USER_PASS"), getData("GENERIC.AUTOMATION.COMPANYCODE")).
		openToolsMenu().openUsersPage();
		usersPage.searchUser(getData("TC19749.USER_PASS")).openSecurityRoles();
		Assert(true, "Precondition: User With the Security Role");
		userManagementPage.returnFromUserManagement().searchGroup(getData("TC19749.GROUP_SAMELVL"));
		Assert(true, "General Information screen of the user group screen will be displayed.");
		groupManagementPage.openAddUserDirectly(getData("TC19749.USER_PASS")).addExcludeUser();
		Assert(groupManagementPage.getUserAddedTxt(1).contains(getData("TC19749.USER_PASS")), "User will be able to Manage Group memberships for User groups homed at the organization in which their role is assigned.: Adding User");
		groupManagementPage.removeUser();
		Assert(!groupManagementPage.getUserAddedTxt(1).contains(getData("TC19749.USER_PASS")), "User will be able to Manage Group memberships for User groups homed at the organization in which their role is assigned.: Remove User");
		groupManagementPage.openExcludeUser(getData("TC19749.USER_PASS")).addExcludeUser();
		Assert(groupManagementPage.getUserAddedTxt(2).contains(getData("TC19749.USER_PASS")), "User will be able to Manage Group memberships for User groups homed at the organization in which their role is assigned.: Exclude User");
		userManagementPage.returnFromUserManagement().searchGroup(getData("TC19749.GROUP_LOWERLVL"));
		groupManagementPage.openAddUserDirectly(getData("TC19749.USER_PASS1")).addExcludeUser();
		Assert(groupManagementPage.getUserAddedTxt(1).contains(getData("TC19749.USER_PASS1")), "User will be able to Manage Group memberships for User groups homed at the organization in which their role is assigned.: Adding User");
		groupManagementPage.removeUser();
		Assert(!groupManagementPage.getUserAddedTxt(1).contains(getData("TC19749.USER_PASS1")), "User will be able to Manage Group memberships for User groups homed at the organization in which their role is assigned.: Remove User");
		groupManagementPage.openExcludeUser(getData("TC19749.USER_PASS1")).addExcludeUser();
		Assert(groupManagementPage.getUserAddedTxt(2).contains(getData("TC19749.USER_PASS1")), "User will be able to Manage Group memberships for User groups homed at the organization in which their role is assigned.: Exclude User");
		userManagementPage.returnFromUserManagement().searchGroup(getData("TC19749.GROUP_UPPERLVL"));
		Assert(usersPage.getSearchForGroupTxt().contains("No Groups found"), " user  NOT be able to Manage Group memberships for User groups homed above the organization in which their role is assigned as the user cannot access the group.");
	}

}
