package tests.compliancewire.basic.logs;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;

public class TestCase20211 extends BaseTest {

	private static final Logger logger = Logger.getLogger(TestCase20211.class);

	@Test(alwaysRun = true, description = "Define a Event " + "Log Report by making use of search criteria.", groups = {
			 })
	public void Logs_Event_Log_Report_with_search_criteria() throws Exception {
		addToStorage("reportName1", getRandomEntityID().substring(0, 7) + "_rpt");
		addToStorage("adminUser",
				getRandomEntityID().substring(0, 7) + getRandomEntityID().substring(0, 5) + "_AdminUsr");
		loginPage
				.signIn(getData("GENERIC.USER"), getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openUsersPage().openAddUser()
				.addANewUser(getFromStorage("adminUser") + "fn", getFromStorage("adminUser") + "ln",
						getFromStorage("adminUser"), getData("GENERIC.TOP_ORGANIZATION"))
				.openSecurityRoles().openAssignSecurityRole()
				.assignRole(getData("GENERIC.TOP_ORGANIZATION"), getData("GENERIC.ROLE_ADMIN")).logOut()
				.signIn(getFromStorage("adminUser"), getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE")).openToolsMenu()
				.openUsersPage().searchUser(getFromStorage("adminUser")).openSecurityRoles();
		Assert(userManagementPage.getUserId().contains(getFromStorage("adminUser")), "Precondition: Admin User -" + getFromStorage("adminUser"));
		Assert(usersPage.openLogsPage().openEventLogReport().conditionForReportRow1("User Id", "contains", "usr")
				.selectEvent("1").runSavedReport(getFromStorage("reportName1")).getReport()
				.contains(getFromStorage("reportName1")),
				"Logs tab will displayed. Users, dates and events to include in report will be selected.A new Saved Event log report will be generated");
		eventLogReportPage.displayReport();

	}
}
