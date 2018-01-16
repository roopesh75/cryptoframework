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

public class TestCase20201 extends BaseTest {
	private static final Logger logger = Logger.getLogger(TestCase20201.class);

	@Test(alwaysRun = true, dataProviderClass = DynamicDataProvider.class, dataProvider = "createAdminUser", 
			description = "Edit account information for an existing user", groups = {
			"basic", "basic.users" })
	public void Edit_account_information_for_an_existinguser(String userName) throws Exception {
		addToStorage("randomTag1", getRandomEntityID().substring(0, 7));

		loginPage.signIn(userName, getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openUsersPage().searchUser(userName).openSecurityRoles();
		Assert(true, "Precondition: Admin User");
		userManagementPage.returnFromUserManagement().openAddUser().addANewUser(getFromStorage("randomTag1") + "usr2",
				getFromStorage("randomTag1") + "usr2", getFromStorage("randomTag1") + "usr2",
				getData("TestCase19751.ORGANIZATION"));
		Assert(true, "Users general information will be displayed");
		addToStorage("editUserInfo", getRandomEntityID().substring(0, 7));
		userManagementPage.openEditUser().editUser(getFromStorage("editUserInfo") + "user2",
				getFromStorage("editUserInfo") + "user2");
		Assert(userManagementPage.getFirstName().equalsIgnoreCase(getFromStorage("editUserInfo") + "user2")
				&& userManagementPage.getLastName().equalsIgnoreCase(getFromStorage("editUserInfo") + "user2"),
				"General Information screen of the user will display with the updated user information");

	}
}
