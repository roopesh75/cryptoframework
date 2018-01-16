package tests.compliancewire.cfr_f.users;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;
import initializer.DynamicDataProvider;
import ui.pages.actions.SecurityRolePage;
import ui.utils.Tools;

/**
 * Created by vevinmoza on 3/18/16.
 */

public class TestCase19737 extends BaseTest {
	private static final Logger logger = Logger.getLogger(TestCase19737.class);

	@Test(alwaysRun = true, dataProviderClass = DynamicDataProvider.class, dataProvider = "createAdminUser", description = "Users_SecurityRole_Grant_SecurityRole", groups = {
			"cfr_f", "cfr_f.users" })
	public void Users_SecurityRole_Grant_SecurityRole(String userName) throws Exception {

		loginPage
				.signIn(getData("TC19737.USER"), getData("GENERIC.PASSWORD").toLowerCase(), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openUsersPage();
		usersPage.searchUser(userName).openSecurityRoles();
		Assert(userManagementPage.getUserId(), userName, "Precondition: Admin User");
		userManagementPage.returnFromUserManagement().openSecurityRoles().chooseSecurityRole(getData("TC19747.SECURITY_ROLE"));
		Assert(securitySettingsPage.getSelectedRole(),getData("TC19747.SECURITY_ROLE"), "Precondition: Security Role Information");
		securitySettingsPage.scrollDown();
		Assert(securitySettingsPage.getSelectedRole(),getData("TC19747.SECURITY_ROLE"), "Precondition: Security Role Information Remaining");
		Assert(true, "Precondition: PC Time Zone of the test user is: UTC "+Tools.getSystemTimeZone());
		securitySettingsPage.returnFromSecuritySettings().returnFromSecurityRole().openSecurityRoles();
		Assert(securityRolePage.isSecurityRolePage(),true, "Security Roles page should be displayed");
		addToStorage("roleId", getRandomEntityID());
		securityRolePage.addNewSecurityRole();
		Assert(securitySettingsPage.getCountEnabledPermissions()+"","2",
				"User will not have access to select rights which the user's security role does not have.");
		securitySettingsPage.createNewSecurityRoleViewUserEditSecurityRole(getFromStorage("roleId"));
		Assert(securityRolePage.getSecurityRoles(),(getFromStorage("roleId")),
				"user will be able to select same or lesser rights granted to them in their own security role."
						+ " New Security role with select security rights will be created.");
		securityRolePage.logOut();
		loginPage.signIn(userName, getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openLogsPage().openEventLogReport().addUsr(getData("TC19737.USER")).selectEvent("68")
				.openRunThisReport().sortByDateTime().sortByDateTime();
		Assert(eventLogReportPage.getEventLogTopRow(),(getFromStorage("roleId")),
				"The event log report will be generated and will display Add security role event record with the date/timestamp based on PC time zone.");
		eventLogReportPage.logOut();
		loginPage
				.signIn(getData("TC19737.USER"), getData("GENERIC.PASSWORD").toLowerCase(), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openUsersPage().openSecurityRoles();
		Assert(securityRolePage.isSecurityRolePage(),true, "Security Roles page should be displayed");
		securityRolePage.chooseSecurityRole(getFromStorage("roleId"));
		securitySettingsPage.editSecurityRole();
		Assert(securityRolePage.getSecurityRoles(),(getFromStorage("roleId")),
				"Verify that the user is able to edit the security role by adding or removing a security bit that the user has same rights defined with. ");
		securitySettingsPage.logOut();
		loginPage.signIn(userName, getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openLogsPage().openEventLogReport().addUsr(getData("TC19737.USER")).selectEvent("68")
				.openRunThisReport().sortByDateTime().sortByDateTime();;
		Assert(eventLogReportPage.getEventLogTopRow(),(getFromStorage("roleId")),
				"The event log report will be generated and will display Edit security role event record with the date/timestamp based on PC time zone.");
		eventLogReportPage.logOut();
		loginPage
				.signIn(getData("TC19737.USER"), getData("GENERIC.PASSWORD").toLowerCase(), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openUsersPage().openSecurityRoles();
		Assert(securityRolePage.isSecurityRolePage(),true, "Security Roles page should be displayed");
		securityRolePage.chooseSecurityRole(getFromStorage("roleId"));

		securitySettingsPage.removeSecurityRole();
		Assert(!securityRolePage.getSecurityRoles().contains(getFromStorage("roleId")),
				"The selected Security role will be removed.");
		securitySettingsPage.logOut();
		loginPage.signIn(userName, getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openLogsPage().openEventLogReport().addUsr(getData("TC19737.USER")).selectEvent("68")
				.openRunThisReport().sortByDateTime().sortByDateTime();
		Assert(eventLogReportPage.getEventLogTopRow(),(getFromStorage("roleId")),
				"The event log report will be generated and "
						+ "will display Remove security role event record with the date/timestamp based on PC time zone.");
	}

}
