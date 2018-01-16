package tests.compliancewire.basic.login;

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

public class TestCase20200 extends BaseTest {
	private static final Logger logger = Logger.getLogger(TestCase20200.class);

	@Test(alwaysRun = true, dataProviderClass = DynamicDataProvider.class, dataProvider = "createAdminUser", description = "User is required to enter three distinct identification components (User ID, Password, and Company Code) to access the system",  groups = {
			"basic", "basic.login"})
	public void User_required_to_enter_three_distinct_identification_components_UserID_Password_CompanyCode_to_access_system(String userName) throws Exception {
		addToStorage("userInfo", getRandomEntityID().substring(0, 7));
		loginPage.signIn(userName, getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openUsersPage().openAddUser()
				.addANewUser(getFromStorage("userInfo") + "usr2", getFromStorage("userInfo") + "usr2",
						getFromStorage("userInfo") + "usr2", getData("TestCase19751.ORGANIZATION"))
				.returnFromUserManagement().searchUser(getFromStorage("userInfo") + "usr2");
		Assert(userManagementPage.getFirstName().equalsIgnoreCase(getFromStorage("userInfo") + "usr2")
				&& userManagementPage.getLastName().equalsIgnoreCase(getFromStorage("userInfo") + "usr2")
				&& userManagementPage.getHomeOrganization().equalsIgnoreCase("Auto Polaris 2.0"),
				"PreCondition 1:Valid User in a company");
		userManagementPage.openSecurityRoles();
		Assert(userManagementPage.getSecurityRole().equalsIgnoreCase("Learner"),
				"PreCondition 2:Valid User in a company");
		userManagementPage.logOut();
		loginPage.enterUserIDPwdInfo(getFromStorage("userInfo") + "usr2", "Pass1234");
		Assert(loginPage.getPwdBoxAttribute().equalsIgnoreCase("password"),
				"On entering Password for login, password will be displayed in encrypted form. ");
		loginPage.acceptLogin(getData("GENERIC.AUTOMATION.COMPANYCODE"));
		Assert(homePage.isHomePage(),
				" The application will open and bring up the ‘Knowledge Center -- Compliance wire ‘screen'");

	}
}
