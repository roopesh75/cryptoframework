package tests.compliancewire.basic.users;


import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;

public class TestCase20222 extends BaseTest {
	private static final Logger logger = Logger.getLogger(TestCase20222.class);

	@Test(alwaysRun = true,
			description = "Edit a security role.", groups = {})
	public void Users_Edit_a_security_role() throws Exception {
		addToStorage("role", getSecurityRoleSeq()+getRandomEntityID().substring(0,5)+"_role");
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
		
		userManagementPage.returnFromUserManagement().openSecurityRoles().addNewSecurityRole().createNewSecurityRole(getFromStorage("role"));
		Assert(securityRolePage.getPRINTTableBorder().contains(getFromStorage("role")),"Security role.");
		Assert(securityRolePage.isSecurityRolePage(), "Security Roles page will be displayed");
		securityRolePage.chooseSecurityRole(getFromStorage("role")).editSecurityRole();;
		Assert(securityRolePage.getPRINTTableBorder().contains(getFromStorage("role")),"Security role editable and Existing security role information will be edited according to the selections made. ");
		securityRolePage.chooseSecurityRole(getFromStorage("role"));
		securitySettingsPage.scrollDown();
		Assert(securitySettingsPage.getPersmissionStatus("Privilege21"), "The security roles view screen will display with the updated security role information.");

	}
}
