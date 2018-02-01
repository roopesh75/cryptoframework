package tests.compliancewire.basic.logs;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;
import initializer.DynamicDataProvider;

public class TestCase20236 extends BaseTest {

	private static final Logger logger = Logger.getLogger(TestCase20236.class);

	@Test(alwaysRun = true, description = "ComplianceWire"
			+ " will display a log of system changes", groups = {
					"basic","basic.logs" })
	public void Logs_Major_Upgrades() throws Exception {
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
		Assert(userManagementPage.getUserId(), getFromStorage("adminUser"), "Precondition: Admin User -" + getFromStorage("adminUser"));
		usersPage.openLogsPage().openMajorUpgrades();
		Assert(logsManagementPage.getLatestUpgradeInfo(0).contains(getData("TC20236.DATE")) &&
				logsManagementPage.getLatestUpgradeInfo(0).contains("Major Upgrade") &&
				logsManagementPage.getLatestUpgradeInfo(0).contains("As part of our continuous improvement process"), "The top of the Change log will display an entry for"
				+ " the current or new Major Upgrade to ComplianceWire which will provide a general list of the changes. ");

	}

}
