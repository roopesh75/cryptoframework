package tests.compliancewire.basic.users;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;
import initializer.DynamicDataProvider;

public class TestCase20201 extends BaseTest {
	private static final Logger logger = Logger.getLogger(TestCase20201.class);

	@Test(alwaysRun = true, description = "Create a new user group (General) that others can view, edit, remove and use.", groups = {
			})
	public void Users_User_Group_Create_View_Edit_Remove() throws Exception {
		addToStorage("adminUser",
				getRandomEntityID().substring(0, 7) + getRandomEntityID().substring(0, 5) + "_AdminUsr");
		loginPage
				.signIn(getData("GENERIC.USER"), getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openUsersPage().openAddUser()
				.addANewUser(getFromStorage("adminUser") + "fn", getFromStorage("adminUser") + "ln",
						getFromStorage("adminUser"), getData("GENERIC.TOP_ORGANIZATION"))
				.openSecurityRoles().openAssignSecurityRole()
				.assignRole(getData("GENERIC.TOP_ORGANIZATION"), getData("GENERIC.ROLE_ADMIN")).logOut()
				.signIn(getFromStorage("adminUser"), getData("GENERIC.PASSWORD"),
						getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openUsersPage().searchUser(getFromStorage("adminUser")).openSecurityRoles();
		addToStorage("randomTag1", getRandomEntityID().substring(0, 7));
		Assert(userManagementPage.openSecurityRoles().getSecurityRole().contains("Organization Administrator"),
				"Precondition: Admin User -" + getFromStorage("adminUser"));
		userManagementPage.returnFromUserManagement().openAddUser().addANewUser(getFromStorage("randomTag1") + "usr2",
				getFromStorage("randomTag1") + "usr2", getFromStorage("randomTag1") + "usr2",
				getData("TestCase19751.ORGANIZATION"));
		Assert(userManagementPage.getFirstName().equalsIgnoreCase(getFromStorage("randomTag1") + "usr2"),
				"Users general information will be displayed");
		addToStorage("editUserInfo", getRandomEntityID().substring(0, 7));
		userManagementPage.openEditUser().editUser(getFromStorage("editUserInfo") + "user2",
				getFromStorage("editUserInfo") + "user2");
		Assert(userManagementPage.getFirstName().equalsIgnoreCase(getFromStorage("editUserInfo") + "user2")
				&& userManagementPage.getLastName().equalsIgnoreCase(getFromStorage("editUserInfo") + "user2"),
				"General Information screen of the user will display with the updated user information");

	}
}
