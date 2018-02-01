package tests.compliancewire.basic.users;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;

public class TestCase20214 extends BaseTest {
	private static final Logger logger = Logger.getLogger(TestCase20214.class);

	@Test(alwaysRun = true, description = "Assign a security role to a user in an organization", groups = {})
	public void Users_Assign_a_security_role() throws Exception {
		addToStorage("adminUser",
				getRandomEntityID().substring(0, 7) + getRandomEntityID().substring(0, 5) + "_AdminUsr");
		addToStorage("learnerUser", getRandomEntityID().substring(0, 7));
		loginPage
				.signIn(getData("GENERIC.USER"), getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openUsersPage().openAddUser()
				.addANewUser(getFromStorage("adminUser") + "fn", getFromStorage("adminUser") + "ln",
						getFromStorage("adminUser"), getData("GENERIC.TOP_ORGANIZATION"))
				.openSecurityRoles().openAssignSecurityRole()
				.assignRole(getData("GENERIC.TOP_ORGANIZATION"), getData("GENERIC.ROLE_ADMIN")).logOut()
				.signIn(getFromStorage("adminUser"), getData("GENERIC.PASSWORD"),
						getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openUsersPage();
		usersPage.searchUser(getFromStorage("adminUser")).openSecurityRoles();
		Assert(userManagementPage.getUserId(), getFromStorage("adminUser"), "Precondition: Admin User");
		userManagementPage.returnFromUserManagement().openAddUser().addANewUser(getFromStorage("learnerUser") + "fn",
				getFromStorage("learnerUser") + "ln", getFromStorage("learnerUser") + "usr", getData("TC19736.ORG1"))
				.openSecurityRoles();
		Assert(userManagementPage.getOrganizationEntity().contains(getData("TC19736.ORG1"))
			&& userManagementPage.getSecurityRole().equalsIgnoreCase("Learner"),
				"Precondition:User with a security role in any middle level organization");
		Assert(userManagementPage.returnFromUserManagement().openOptionsPage().openOrganizationNodeManagement()
				.openMidLevelOrganization(getData("TC19736.ORG1")).getSelectedOrganization()
				.contains(getData("TC19736.ORG1")), "Precondition: Selected midlevel Organization");
		optionsManagementPage.returnToOptions().openUsersPage().openSecurityRoles()
				.chooseSecurityRole(getData("TC20214.SECURITY_ROLE"));
		Assert(securitySettingsPage.getSelectedRole(), getData("TC20214.SECURITY_ROLE"),
				"Precondition:  Security role in any organization other than the organization of the above user");
		securitySettingsPage.returnFromSecuritySettings().returnFromSecurityRole()
				.searchUser(getFromStorage("learnerUser") + "usr").openSecurityRoles().openAssignSecurityRole()
				.assignRole(getData("TestCase19742.ORGANIZATION"), getData("TC20214.SECURITY_ROLE"));
		Assert(userManagementPage.getOrganizationEntity().contains(getData("TestCase19742.ORGANIZATION"))
				&& userManagementPage.getSecurityRole().contains(getData("TC20214.SECURITY_ROLE")),
				"The selected security role will be assigned to the user in the selected organization");
	}

}
