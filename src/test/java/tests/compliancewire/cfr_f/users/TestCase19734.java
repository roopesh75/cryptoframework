package tests.compliancewire.cfr_f.users;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;
import initializer.DynamicDataProvider;
import ui.utils.RegularExpression;

/**
 * Created by vevinmoza on 3/18/16.
 */

public class TestCase19734 extends BaseTest {
	private static final Logger logger = Logger.getLogger(TestCase19734.class);

	@Test(alwaysRun = true, 
			description = "User Account History will provide a history of all changes made"
					+ " to the user account.The system prevents users from changing any data "
					+ "logged to a user’s account history", groups = {
			"cfr_f", "cfr_f.users", "regression", "test_sample" })
	public void Users_User_Histroy_View_User_History() throws Exception {

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
		userManagementPage.returnFromUserManagement();
		Assert(usersPage.isUsersPage(), true, "Users tab should be displayed");
		usersPage.openAddUser();
		Assert(addNewUserPage.isAddUsersPage(), true, "Add user page should be displayed");
		addNewUserPage.addANewUser(getRandomEntityID().substring(0, 8));
		AssertMatches(userManagementPage.getFirstName(), (RegularExpression.ALPHA_NUMERIC),
				"Required fields data should be saved and displayed in General Information screen.");

		AssertMatches(userManagementPage.getLastName(), (RegularExpression.ALPHA_NUMERIC),
				"Required fields data should be saved and displayed in General Information screen.");

		AssertMatches(userManagementPage.getUserId(), (RegularExpression.ALPHA_NUMERIC),
				"Required fields data should be saved and displayed in General Information screen.");

		userManagementPage.openGeneralInformation().openHistory();
		AssertMatches(userManagementPage.getCreatedOn(), (RegularExpression.DATE_TIME),
				"The initial creation information will include date, time and who created.");

		Assert(userManagementPage.getCreatedBy(), (getFromStorage("adminUser")),
				"The initial creation information will include date, time and who created.");

		addToStorage("userFirstName", getRandomEntityID().substring(0, 6));
		userManagementPage.openGeneralInformation().openEditUser().editUser(getFromStorage("userFirstName"));

		Assert(userManagementPage.getFirstName(), (getFromStorage("userFirstName")),
				"General Information screen is displayed with the changes made.");

		userManagementPage.openHistory();
		Assert(userManagementPage.getPropertyEdited(0), ("FirstName"),
				"User Account History will display the property edited, old value,"
						+ " new value, user making the change and the date/time of the change.");

		Assert(userManagementPage.getOldValue(0), ("Alice"),
				"User Account History will display the property edited, old value,"
						+ " new value, user making the change and the date/time of the change.");

		Assert(userManagementPage.getNewValue(0), (getFromStorage("userFirstName")),
				"User Account History will display the property edited, old value,"
						+ " new value, user making the change and the date/time of the change.");

		addToStorage("userFirstName_1", getRandomEntityID().substring(0, 6));
		userManagementPage.openGeneralInformation().openEditUser().editUser(getFromStorage("userFirstName_1"))
				.openHistory();

		Assert(userManagementPage.getPropertyEdited(1), ("FirstName"),
				"User Account History will display an entry for each property change.");

		Assert(userManagementPage.getOldValue(1), (getFromStorage("userFirstName")),
				"User Account History will display an entry for each property change.");

		Assert(userManagementPage.getNewValue(1), (getFromStorage("userFirstName_1")),
				"User Account History will display an entry for each property change.");

		userManagementPage.openHistory();
		Assert(true, "The user will not be able to change any of the historical data recorded for this user.");

	}

}
