package tests.compliancewire.cfr_f.users;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;
import initializer.DynamicDataProvider;

/**
 * Created by vevinmoza on 3/18/16.
 */

public class TestCase19735 extends BaseTest {
	private static final Logger logger = Logger.getLogger(TestCase19735.class);

	@Test(alwaysRun = true, dataProviderClass = DynamicDataProvider.class, dataProvider = "createAdminUser", description = "Users_Add User_Existing UserID", groups = {
			"cfr_f", "cfr_f.users", "desktop" })
	public void Users_Add_User_Existing_UserID(String userName) throws Exception {
		loginPage.signIn(userName, getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openUsersPage();
		usersPage.searchUser(userName).openSecurityRoles();
		Assert(userManagementPage.getUserId(), userName, "Precondition: Admin User");
		userManagementPage.returnFromUserManagement();
		usersPage.openAddUser();
		Assert(addNewUserPage.isAddUsersPage(),true, "Add user screen  should be displayed");
		addNewUserPage.addANewUser(userName);
		AssertDesktop(
				addNewUserPage.getAlertBoxText(),("entered already exists"),
				"User is able to enter the values in First Name, Last Name and User ID textboxes."
						+ " A Pop up will be displayed indicating that the User ID entered already exists in the company and the new user will not be created.");
		addNewUserPage.switchToAlart().accept();
	}

}
