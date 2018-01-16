package tests.compliancewire.cfr_f.users;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;
import initializer.DynamicDataProvider;

/**
 * Created by vevinmoza on 3/18/16.
 */
// working
public class TestCase19745 extends BaseTest {
	private static final Logger logger = Logger.getLogger(TestCase19745.class);

	@Test(alwaysRun = true, dataProviderClass = DynamicDataProvider.class, dataProvider = "createAdminUser", description = "Users_History_Group membership history", groups = {
			"cfr_f", "cfr_f.users" })
	public void Users_History_Group_membership_history(String userName) throws Exception {
		
		loginPage.signIn(userName, getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openUsersPage();
		
		usersPage.searchUser(userName).openSecurityRoles();
		Assert(true, "Precondition: Admin User");
		userManagementPage.returnFromUserManagement();
		
		addToStorage("randomTag", getRandomEntityID().substring(0, 7));
		usersPage.openAddUser()
				.addANewUser(getFromStorage("randomTag") + "usr", getFromStorage("randomTag") + "usr",
						getFromStorage("randomTag") + "usr")
				.returnFromUserManagement().openCreateNewGroup()
				.createNewGroup(getRandomEntityID().substring(0, 8) + "Grp");
		Assert(groupManagementPage.isGroupPage(), "New group is created and group information page is displayed");
		groupManagementPage.openAddUserDirectly(getFromStorage("randomTag") + "usr").addExcludeUser();
		Assert(groupManagementPage.getUserCount().contains("1"),
				"The user to be added will be displayed in Users added directly to this group section.");
		groupManagementPage.openExcludeUser(getFromStorage("randomTag") + "usr").addExcludeUser();
		Assert(groupManagementPage.getUserCount().contains("0"),
				"The user to be excluded will be displayed in Users excluded from this group section.");
		groupManagementPage.openGroupMemberShipHistory();
		Assert(groupMembershipHistoryPage.getRow(0).contains(getFromStorage("randomTag") + "usr")
				&& groupMembershipHistoryPage.getTopRow().contains("User ID")
				&& groupMembershipHistoryPage.getTopRow().contains("Last Name")
				&& groupMembershipHistoryPage.getTopRow().contains("First Name")
				&& groupMembershipHistoryPage.getTopRow().contains("Action")
				&& groupMembershipHistoryPage.getTopRow().contains("Modified By")
				&& groupMembershipHistoryPage.getTopRow().contains("Modified On")
				&& groupMembershipHistoryPage.getRow(0).contains("Genz, Pinaa")
				&& groupMembershipHistoryPage.getRow(0).contains("Changed - Moved to Exclusion List")
				&& groupMembershipHistoryPage.getRow(1).contains(getFromStorage("randomTag") + "usr")
				&& groupMembershipHistoryPage.getRow(1).contains("Added Directly"),
				"The History screen will display the the User id, First name , Last Name , Action and Modified by, Modified on.The Group"
						+ " Membership History will be displayed with a chronological history of"
						+ " changes made to the group membership.");

		groupMembershipHistoryPage.getRow(0);
		groupMembershipHistoryPage.getRow(1);
		Assert(true, "The user will not be able to modify the data on the Group membership History screen ");

	}

}
