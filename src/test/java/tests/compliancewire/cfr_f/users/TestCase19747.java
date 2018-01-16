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

	@Test(alwaysRun = true, dataProviderClass = DynamicDataProvider.class, dataProvider = "createAdminUser", description = "Users_Security roles_assigning security role", groups = {
			"cfr_f", "cfr_f.users"})
	public void Users_Security_roles_assigning_security_role(String userName) throws Exception {

		loginPage.signIn(userName, getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openUsersPage();
		usersPage.searchUser(userName).openSecurityRoles();
		Assert(true, "Precondition: Admin User");

		userManagementPage.returnFromUserManagement().openSecurityRoles()
				.chooseSecurityRole(getData("TC19747.SECURITY_ROLE"));
		Assert(true, "Precondition: Security Role Information");
		securitySettingsPage.returnFromSecuritySettings().returnFromSecurityRole().openSecurityRoles()
				.chooseSecurityRole(getData("TC19747.SECURITY_ROLE"));

		Assert(securitySettingsPage.isSecuritySettingsPage(), "Security role will be selected");
		Assert(!securitySettingsPage.assignSecurityRoleToUsr(userName, "true")
				.getOverideableStateSecurityRole(userName),
				"System will not allow the user to change their own security role"
						+ " while trying to assign a security role to multiple users.");

	}

}
