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

public class TestCase20202 extends BaseTest {
	private static final Logger logger = Logger.getLogger(TestCase20202.class);

	@Test(alwaysRun = true, dataProviderClass = DynamicDataProvider.class, dataProvider = "createAdminUser", description = "Able to add a new user account.", groups = {"basic", "basic.users"})
	public void Able_to_add_a_new_useraccount(String userName) throws Exception {
		addToStorage("randomTag1", getRandomEntityID().substring(0, 7));

		loginPage.signIn(userName, getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openUsersPage().searchUser(userName).openSecurityRoles();
		Assert(true, "Precondition: Admin User");
		userManagementPage.returnFromUserManagement().openAddUser()
				.enterFnameLnameUserid(getFromStorage("randomTag1") + "user2");

		Assert(addNewUserPage.getFirstName().contains("Alice") && addNewUserPage.getLastName().contains("Patricia")
				&& addNewUserPage.getUserId().contains(getFromStorage("randomTag1") + "user2"),
				"User will be able to enter First Name, Last Name and User ID in the text box");
		Assert(addNewUserPage.enterPwd().getPwdBoxAttribute().contains("password")
				&& addNewUserPage.getConfirmPwdBoxAttribute().contains("password"),
				"Password will be displayed in encrypted form");
		addNewUserPage.enterHomePhone().saveUser();
		Assert(userManagementPage.getFirstName().equalsIgnoreCase("Alice")
				&& userManagementPage.getLastName().equalsIgnoreCase("Patricia")
				&& userManagementPage.getUserId().equalsIgnoreCase(getFromStorage("randomTag1") + "user2")
				&& userManagementPage.getHomePhone().equalsIgnoreCase("7326941000"),
				"The new user account will be created and the General Information screen will display the new user information.");

	}
}
