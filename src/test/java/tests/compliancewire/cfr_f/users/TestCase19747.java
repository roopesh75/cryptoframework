package tests.compliancewire.cfr_f.users;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;
import initializer.DynamicDataProvider;

/**
 * Created by vevinmoza on 3/18/16.
 */

public class TestCase19747 extends BaseTest {
	private static final Logger logger = Logger.getLogger(TestCase19735.class);

	@Test(alwaysRun = true, description =
			"System does not allow a user to change own security role while assigning a security role to multiple users", groups = {
			"cfr_f", "cfr_f.users"})
	public void Users_Security_roles_assigning_security_role() throws Exception {

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
				.openToolsMenu().openUsersPage();
		usersPage.searchUser(getFromStorage("adminUser")).openSecurityRoles();
		Assert(userManagementPage.openSecurityRoles().getSecurityRole().contains("Organization Administrator"), "Precondition: Admin User -" + getFromStorage("adminUser"));

		userManagementPage.returnFromUserManagement().openSecurityRoles()
				.chooseSecurityRole(getData("TC19747.SECURITY_ROLE"));
		Assert(securitySettingsPage.getSelectedRole().contains(getData("TC19747.SECURITY_ROLE")), "Precondition: Security Role Information");
		securitySettingsPage.returnFromSecuritySettings().returnFromSecurityRole().openSecurityRoles()
				.chooseSecurityRole(getData("TC19747.SECURITY_ROLE"));

		Assert(securitySettingsPage.isSecuritySettingsPage(), "Security role will be selected");
		Assert(!securitySettingsPage.assignSecurityRoleToUsr(getFromStorage("adminUser"), "true")
				.getOverideableStateSecurityRole(getFromStorage("adminUser")),
				"System will not allow the user to change their own security role"
						+ " while trying to assign a security role to multiple users.");

	}

}
