package tests.compliancewire.cfr_f.passwordpolicies;

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

public class TestCase19722 extends BaseTest {
	private static final Logger logger = Logger.getLogger(TestCase19722.class);

	@Test(priority = 5, alwaysRun = true, 
			description = "User is able to define password lengths", groups = {"cfr_f", "cfr_f.passwordpolicies" 
			 })
	public void Options_PasswordPolicies_ChangePasswordLength() throws Exception {
		addToStorage("adminUser",
				getRandomEntityID().substring(0, 7) + getRandomEntityID().substring(0, 5) + "_AdminUsr");
		loginPage
				.signIn(getData("GENERIC.USER"), getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openUsersPage().openAddUser()
				.addANewUser(getFromStorage("adminUser") + "fn", getFromStorage("adminUser") + "ln",
						getFromStorage("adminUser"), getData("GENERIC.TOP_ORGANIZATION"))
				.openSecurityRoles().openAssignSecurityRole()
				.assignRole(getData("GENERIC.TOP_ORGANIZATION"), getData("GENERIC.ROLE_ADMIN")).logOut()
				.signIn(getFromStorage("adminUser"), getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"));
		Assert(homePage.isHomePage(), "Admin user will be able to login successfully");
		homePage.openToolsMenu().openUsersPage();
		usersPage.searchUser(getFromStorage("adminUser")).openSecurityRoles();
		Assert(userManagementPage.openSecurityRoles().getSecurityRole().contains("Organization Administrator"), "Precondition: Admin User -" + getFromStorage("adminUser"));
		usersPage.openOptionsPage();
		Assert(optionsPage.isOptionsPage(), "Admin user will be navigated to Options tab");
		optionsPage.openPasswordPolicies();
		Assert(definePasswordPoliciesPage.isDefinePasswordPoliciesPage(),
				"Admin user will be navigated to Define Password Policies page");
		definePasswordPoliciesPage.openEditPasswordPolicies().setMinPwdLength("3").setMaxPwdLength("8").saveChanges();
		Assert(definePasswordPoliciesPage.getPasswordLengths(),("Passwords must be at least 3 Character(s) long"),
				"Minimum and maximum password lengths will be set.");
		Assert(definePasswordPoliciesPage.getPasswordLengths(),("Passwords cannot be longer than 8 Character(s)"),
				"Minimum and maximum password lengths will be set.");
		optionsPage.openSupport().openUserProfile().openChangePwdPage();
		Assert(changePasswordPage.isPwdPoliciesPage(),
				"Admin user will be navigated to Change Password page.");
		changePasswordPage.changePassword(getData("GENERIC.PASSWORD"), "B1", "B1");
		Assert(changePasswordPage.getAlertMessage().contains("Passwords must follow the rules below"),"Passwords that are shorter than the minimum lengths will return a warning message");
		changePasswordPage.changePassword(getData("GENERIC.PASSWORD"), "12345678B", "12345678B");
		Assert(changePasswordPage.getAlertMessage().contains("Exceeds Maximum Length"),"Passwords that are longer than the maximum lengths will return a warning message");
		changePasswordPage.changePassword(getData("GENERIC.PASSWORD"), "123B", "123B");
		Assert(changePasswordPage.getSuccessMessage().contains("Your Password has been changed"), "The user's password will be changed to a new password");
		changePasswordPage.continuePwd().logOut().signIn(getFromStorage("adminUser"), "123B", getData("GENERIC.AUTOMATION.COMPANYCODE"));
		Assert(homePage.isHomePage(), "User will logout and Login successfully. ");
		
		// cleaning the data
		homePage.openToolsMenu().openOptionsPage().openPasswordPolicies().openEditPasswordPolicies().setMinPwdLength("1").setMaxPwdLength("25").saveChanges();

	}
}

