package tests.compliancewire.basic.history;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;
import initializer.DynamicDataProvider;
import ui.utils.Tools;
//DELETED
public class TestCase20238 extends BaseTest {

	private static final Logger logger = Logger.getLogger(TestCase20238.class);

	@Test(alwaysRun = true,description = "History report can be Sorted and viewed online", groups = {
			})
	public void History_report_can_be_Sorted_and_viewed_online() throws Exception {
		addToStorage("userInfo", getRandomEntityID().substring(0, 7));
		addToStorage("adminUser",
				getRandomEntityID().substring(0, 7) + getRandomEntityID().substring(0, 5)+"_"+getClass().getSimpleName() + "_AdminUsr");
		loginPage
				.signIn(getData("GENERIC.USER"), getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openUsersPage().openAddUser()
				.addANewUser(getFromStorage("adminUser") + "fn", getFromStorage("adminUser") + "ln",
						getFromStorage("adminUser"), getData("GENERIC.TOP_ORGANIZATION"))
				.openSecurityRoles().openAssignSecurityRole()
				.assignRole(getData("GENERIC.TOP_ORGANIZATION"), getData("GENERIC.ROLE_ADMIN")).logOut()
				.signIn(getFromStorage("adminUser"),  getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
		.openToolsMenu().openUsersPage();
		usersPage.searchUser(getData("TC20238.USER")).openSecurityRoles();
		Assert(userManagementPage.getUserId(), getData("TC20238.USER"), "Precondition: User");
	
		userManagementPage.logOut().signIn(getData("TC20238.USER"), getData("TC20238.USER"), getData("GENERIC.AUTOMATION.COMPANYCODE")).openHistoryPage();	
		Assert(historyPage.getCompletionDates().size()+"","2", "At least 2 completions in the history screen of the above user");
		
		homePage.logOut().signIn(getData("TC20238.USER"), getData("TC20238.USER"), getData("GENERIC.AUTOMATION.COMPANYCODE")).openHistoryPage().openSortOptions().sortByCompletionDateOldest();	
		
		Assert(Long.parseLong(Tools.getMilliSecondsForDate("MM/dd/yyyy hh:mm:ss a",
				historyPage.getCompletionDates().get(0))) < Long.parseLong(
						Tools.getMilliSecondsForDate("MM/dd/yyyy hh:mm:ss a", historyPage.getCompletionDates().get(1))),
				true, "User able to login , user able to click history, user able to  click Sort option, user able to sort and see results");
	}
}
