package tests.compliancewire.basic.users;

/**
 * @Roopesh
 * 1.date time stamp is not captured as part of expected result  7 
 * 2.Test step 5 should be split to 2 separate steps "Verify that Password is displayed in encrypted form for e-signature"
 * 3.Create web services to check and uncheck password policies 
 */
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;
import initializer.DynamicDataProvider;

public class TestCase20234 extends BaseTest {
	private static final Logger logger = Logger.getLogger(TestCase20234.class);

	@Test(alwaysRun = true,
			description = "User can create Completion "
			+ "Report(s) to display both enabled and disabled users.", groups = {})
	public void Users_Completion_Report_enabled_disabled_users() throws Exception {
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
		Assert(userManagementPage.getUserId(), getFromStorage("adminUser"), "Precondition: Admin User");

		userManagementPage.returnFromUserManagement().searchUser(getData("TC20234.USER_ENABLED"));
		Assert(userManagementPage.getUserId().contains(getData("TC20234.USER_ENABLED")), "Enabled Users");
		userManagementPage.returnFromUserManagement().searchUser(getData("TC20234.USER_DISABLED"));
		Assert(userManagementPage.getUserId().contains(getData("TC20234.USER_DISABLED")), "Disabled Users");
		userManagementPage.returnFromUserManagement().openTrainingPage().openCompletionReportByTraining().
		runCustomCompletionReport_Enabled_Disabled("Show both Enabled and Disabled Users","User Id","is",
				getData("TC20234.USER_ENABLED"),getData("TC20234.USER_DISABLED"));
		
		Assert(trainingManagementPage.getPRINTTableBorder().contains(getData("TC20234.USER_ENABLED")) && trainingManagementPage.getPRINTTableBorder().contains(getData("TC20234.USER_ENABLED")), "Completions recorded for the above Enabled & Disabled users");
		
		trainingManagementPage.openUsersPage().openUserCompletionReport();
		Assert(defineCompletionReportPage.isDefineCompletionReportPage(), "Define Completions Report screen will be displayed.");
		
		defineCompletionReportPage.runCustomCompletionReport_Enabled_Disabled("Show both Enabled and Disabled Users","User Id","is",
				getData("TC20234.USER_ENABLED"),getData("TC20234.USER_DISABLED"));
		
		Assert(userManagementPage.getReportTile().contains("Completions Report by User") && 
				userManagementPage.getPRINTTableBorder().contains(getData("TC20234.USER_ENABLED"))
				&& userManagementPage.getPRINTTableBorder().contains(getData("TC20234.USER_DISABLED")), "Admin User defines , runCompletions Report for enabled & disabled users."
						+ " Completions for enabled & disabled users  recorded in completions report.");
			
		

	}
}
