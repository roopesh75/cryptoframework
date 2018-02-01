package tests.compliancewire.basic.logs;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;
import initializer.DynamicDataProvider;

public class TestCase20235 extends BaseTest {

	private static final Logger logger = Logger.getLogger(TestCase20235.class);
	@Test(alwaysRun = true, description = "Define a Event "
			+ "Log Report by making use of search criteria.", groups = { "basic","basic.logs" })
	public void Logs_Event_Log_Report_with_search_criteria() throws Exception {
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
		Assert(userManagementPage.getUserId().contains(getFromStorage("adminUser")), "Precondition: Admin User -" + getFromStorage("adminUser"));

		usersPage.openLogsPage().openEventLogReport().selectAllEvents().openRunThisReport();
		Assert(eventLogReportPage.areEventLogRowsPresent(), "Precondition: Events recorded by the system");
		

		logsManagementPage.returnToLogsMenu().openEventLogReport();
		Assert(defineEventLogReportPage.isTemporaryReportChecked().contains("true"), "Admin user will be able to retain Temporary report as selected.");
		defineEventLogReportPage.conditionForReportRow1("User Id", "begins with", "a");
		Assert(defineEventLogReportPage.getOperatorRow1().contains("begins with"), "Admin user will be able to select the required options. ");
		defineEventLogReportPage.fromDate("1/18/2017").toDate("1/11/2018").selectAllEvents().openRunThisReport();
		Assert(eventLogReportPage.areUserIdsStartingWith("a"),
				"Event Log Report will be generated and will display list of Events as per the selected search condition.");

	}
}
