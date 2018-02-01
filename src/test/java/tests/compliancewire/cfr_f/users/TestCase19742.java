package tests.compliancewire.cfr_f.users;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;
import initializer.DynamicDataProvider;
import ui.utils.Tools;

/**
 * Created by vevinmoza on 3/18/16.
 */

public class TestCase19742 extends BaseTest {
	private static final Logger logger = Logger.getLogger(TestCase19742.class);

	@Test(alwaysRun = true, description = "Able to assign a "
					+ "security role to multiple users using user search option", groups = {
			"cfr_f", "cfr_f.users" })
	public void Users_Security_Role_Assign_Override_security_role() throws Exception {
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
		Assert(userManagementPage.getUserId(), getFromStorage("adminUser"), "Precondition: Admin User");
		userManagementPage.returnFromUserManagement().openSecurityRoles().chooseSecurityRole(getData("TC19747.SECURITY_ROLE"));
		Assert(securitySettingsPage.getSelectedRole(),getData("TC19747.SECURITY_ROLE"), "Precondition: Security Role Information");
		securitySettingsPage.returnFromSecuritySettings().returnFromSecurityRole();
		
		addToStorage("randomTag1", getSecurityRoleSeq()+getRandomEntityID().substring(0, 7));
		usersPage.openAddUser()
				.addANewUser(getFromStorage("randomTag1") + "usr1", getFromStorage("randomTag1") + "usr1",
						getFromStorage("randomTag1") + "usr1")
				.returnFromUserManagement().openAddUser()
				.addANewUser(getFromStorage("randomTag1") + "usr2", getFromStorage("randomTag1") + "usr2",
						getFromStorage("randomTag1") + "usr2", getData("TestCase19742.ORGANIZATION"))
				.returnFromUserManagement().openSecurityRoles().chooseSecurityRole(getData("TC19747.SECURITY_ROLE"));
		Assert(securitySettingsPage.getSelectedRole(),(getData("TC19747.SECURITY_ROLE")),
				"Security role should be selected");
		securitySettingsPage.assignSecurityRoleToUsr(getFromStorage("randomTag1"));
		Assert(securitySettingsPage.hasAssignSecurityRoleBtn(),true,
				"The “Assign Security Role” link will be displayed only after user does user search.");
		Assert(securitySettingsPage.isAssignSecurityRoleCheckBoxEnabled(getFromStorage("randomTag1") + "usr2"),true,
				"Security role can be assigned to all the users who do not have a role in the selected organization.");
		Assert(!securitySettingsPage.isAssignSecurityRoleCheckBoxEnabled(getFromStorage("randomTag1") + "usr1"),
				"Security Role cannot be assigned to users who have a role in the selected organization.");
		securitySettingsPage.applySecurityRole().viewUsersList();
		addToStorage("securityRoleAssignmentTime", Tools.getDateTime("MM/dd/yyyy hh:mm:ss a"));
		securitySettingsPage.openLogsPage().openEventLogReport();
		Assert(defineEventLogReportPage.isDefineEventLogReportPage(),true, "The event log report screen is displayed. ");
		defineEventLogReportPage.addUsr(getFromStorage("adminUser")).selectEvent("72").openRunThisReport().sortByDateTime().sortByDateTime();

		AssertTime("MM/dd/yyyy hh:mm:ss a",eventLogReportPage.getEventLogTopRowTime(),getFromStorage("securityRoleAssignmentTime"),
				"The Event log will display the 'Add security Role to user' record with the date/timestamp based on PC time zone. PC SYSTEM TIME: "+ getFromStorage("securityRoleAssignmentTime"));
		
			
		defineEventLogReportPage.openUsersPage();
		usersPage.openSecurityRoles().chooseSecurityRole(getData("TC19747.SECURITY_ROLE"))
				.assignSecurityRoleToUsr(getFromStorage("randomTag1"), "true");
		securitySettingsPage.selectUserToOverride(getFromStorage("randomTag1") + "usr1");
		Assert(securitySettingsPage.isAssignSecurityRoleCheckBoxEnabled(getFromStorage("randomTag1") + "usr2"),true
				&& securitySettingsPage.isAssignSecurityRoleCheckBoxEnabled(getFromStorage("randomTag1") + "usr1"),
				"User will be able to use checkbox");
		securitySettingsPage.applySecurityRole();
		securitySettingsPage.viewUsersList();
		Assert(userManagementPage.getPRINTTableBorder().contains(getFromStorage("randomTag1") + "usr1")
				&& userManagementPage.getPRINTTableBorder().contains(getFromStorage("randomTag1") + "usr2"),
				"Security roles of the users in the organization will be over-ridded by the selected security role using user search option.");

	}

}
