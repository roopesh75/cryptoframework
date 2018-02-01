package tests.compliancewire.basic.users;


import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;
import initializer.DynamicDataProvider;

public class TestCase20202 extends BaseTest {
	private static final Logger logger = Logger.getLogger(TestCase20202.class);

	@Test(alwaysRun = true,
			description = "Able to add a new user account.", groups = {"basic", "basic.users"})
	public void User_Add_new_user() throws Exception {
		addToStorage("randomTag1", getRandomEntityID().substring(0, 7));
		addToStorage("adminUser",
				getRandomEntityID().substring(0, 7) + getRandomEntityID().substring(0, 5) + "_AdminUsr");
		loginPage
				.signIn(getData("GENERIC.USER"), getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openUsersPage().openAddUser()
				.addANewUser(getFromStorage("adminUser") + "fn", getFromStorage("adminUser") + "ln",
						getFromStorage("adminUser"), getData("GENERIC.TOP_ORGANIZATION"))
				.openSecurityRoles().openAssignSecurityRole()
				.assignRole(getData("GENERIC.TOP_ORGANIZATION"), getData("GENERIC.ROLE_ADMIN")).logOut()
				.signIn(getFromStorage("adminUser"), getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openUsersPage().searchUser(getFromStorage("adminUser")).openSecurityRoles();
		Assert(userManagementPage.openSecurityRoles().getSecurityRole().contains("Organization Administrator"), "Precondition: Admin User -" + getFromStorage("adminUser"));
		userManagementPage.returnFromUserManagement().openAddUser()
				.enterFnameLnameUserid(getFromStorage("randomTag1") + "user2");

		Assert(addNewUserPage.getFirstName().contains("Alice") && addNewUserPage.getLastName().contains("Patricia")
				&& addNewUserPage.getUserId().contains(getFromStorage("randomTag1") + "user2"),
				"User will be able to enter First Name, Last Name and User ID in the text box");
		Assert(addNewUserPage.enterPwd().getPwdBoxAttribute().contains("password")
				&& addNewUserPage.getConfirmPwdBoxAttribute().contains("password"),
				"Password will be displayed in encrypted form");
		addNewUserPage.enterHomePhone().saveUser();
		Assert(userManagementPage.getFirstName().equalsIgnoreCase("Alice")
				&& userManagementPage.getLastName().equalsIgnoreCase("Patricia")
				&& userManagementPage.getUserId().equalsIgnoreCase(getFromStorage("randomTag1") + "user2")
				&& userManagementPage.getHomePhone().equalsIgnoreCase("7326941000"),
				"The new user account will be created and the General Information screen will display the new user information.");

	}
}
