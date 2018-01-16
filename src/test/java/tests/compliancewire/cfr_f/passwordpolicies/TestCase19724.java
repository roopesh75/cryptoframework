package tests.compliancewire.cfr_f.passwordpolicies;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;
import initializer.DynamicDataProvider;

public class TestCase19724 extends BaseTest {
	private static final Logger logger = Logger.getLogger(TestCase19724.class);

	@Test(priority = 1, alwaysRun = true, dataProviderClass = DynamicDataProvider.class, dataProvider = "createAdminUser", description = "Options_Password policies_Restrict reuse of passwords", groups = {
			"cfr_f", "cfr_f.passwordpolicies" })
	public void Options_Password_policies_Restrict_reuse_of_passwords(String userName) throws Exception {

		loginPage.signIn(userName, getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"));
		Assert(homePage.isHomePage(), "Admin user will be able to login successfully");
		homePage.openToolsMenu().openUsersPage();
		usersPage.searchUser(userName).openSecurityRoles();
		Assert(true, "Precondition: Admin User");
		usersPage.openOptionsPage().openPasswordPolicies();
		Assert(definePasswordPoliciesPage.isDefinePasswordPoliciesPage(), "Password policies page should be displayed");
		definePasswordPoliciesPage.openEditPasswordPolicies().setReusePwdPolicy("2").saveChanges();
		Assert(definePasswordPoliciesPage.getReusePwdPolicy().contains("Do not allow reuse of the past"),
				"The Password policies will be defined to not allow reuse of a selected number of passwords");
		definePasswordPoliciesPage.knowledgeCenter().openUserProfile().openChangePwdPage()
				.changePassword(getData("GENERIC.PASSWORD"), "ABC1", "ABC1");

		Assert(changePasswordPage.getSuccessMessage().contains("Your Password has been changed"),
				"User will attempt to change password reusing his current password, until the total # of attempts is 1 more than the # of reuses selected");
		changePasswordPage.continuePwd().openUserProfile().openChangePwdPage().changePassword("ABC1",
				getData("GENERIC.PASSWORD"), getData("GENERIC.PASSWORD"));
		Assert(changePasswordPage.getAlertMessage()
				.contains("You have already used the new password you entered. Passwords cannot be reused."),
				"User will not be able to change the password");
		changePasswordPage.changePassword("ABC1", "ABC2", "ABC2").continuePwd().openUserProfile().openChangePwdPage()
				.changePassword("ABC2", "ABC3", "ABC3");
		changePasswordPage.continuePwd().openUserProfile().openChangePwdPage().changePassword("ABC3",
				getData("GENERIC.PASSWORD"), getData("GENERIC.PASSWORD"));

		Assert(changePasswordPage.getSuccessMessage().contains("Your Password has been changed"),
				"When attempting the last	reuse, the password will be accepted and his password will be changed.");

		// Cleaning up the data
		changePasswordPage.continuePwd().openToolsMenu().openOptionsPage().openPasswordPolicies()
				.openEditPasswordPolicies().allowOlderPassword().saveChanges();

	}
}
