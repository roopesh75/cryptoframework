package tests.compliancewire.cfr_f.passwordpolicies;

/*
 * @roopesh
 * 1.Analyze why return link is taking time to move back
 * 2.
 */
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;
import initializer.DynamicDataProvider;

public class TestCase19723 extends BaseTest {

	private static final Logger logger = Logger.getLogger(TestCase19723.class);

	@Test(priority = 4,alwaysRun = true, dataProviderClass = DynamicDataProvider.class, dataProvider = "createAdminUser", description = "Options_Password policies_password complexity requirement", groups = {
			"cfr_f", "cfr_f.passwordpolicies" })
	public void Options_Password_policies_password_complexity_requirement(String userName) throws Exception {
		loginPage.signIn(userName, getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"));
		Assert(homePage.isHomePage(), "Admin user will be able to login successfully");
		homePage.openToolsMenu().openUsersPage();
		usersPage.searchUser(userName).openSecurityRoles();
		Assert(true, "Precondition: Admin User");
		usersPage.openOptionsPage().openEsignatureRequirements().openEditRequirements()
				.uncheckReqEsigPasswordPolicies(userName, getData("GENERIC.PASSWORD"),
						getData("TC19721.SIGNATUREREASON"),"RecScr");
		esignatureRequirementsPage.openEditRequirements().checkReqEsigPasswordPolicies().saveChanges()
				.electronicallySignIn(userName, getData("GENERIC.PASSWORD"), getData("TC19721.SIGNATUREREASON"),"RecScr");
		esignatureRequirementsPage.returnOptionsPage().openPasswordPolicies().openEditPasswordPolicies();
		Assert(definePasswordPoliciesPage.isEditPasswordPoliciesPage(),
				"Edit password policies page should be displayed");
		definePasswordPoliciesPage.setPasswordComplexity().saveChanges().electonicallySignIn(userName,
				getData("GENERIC.PASSWORD"), getData("TC19721.SIGNATUREREASON"),"RecScr");
		Assert(definePasswordPoliciesPage.getPasswordComplexity().contains("Require Letters in Uppercase")
				&& definePasswordPoliciesPage.getPasswordComplexity().contains("Require Passwords contain Numbers"),
				"The password policies will be defined to require a combination of letters and/or numbers");
		definePasswordPoliciesPage.knowledgeCenter().openUserProfile().openChangePwdPage()
				.changePassword(getData("GENERIC.PASSWORD"), "ABCD", "ABCD");
		Assert(changePasswordPage.getAlertMessage()
				.contains("The new password must contain at least one numerical character"),
				"When attempting to change to a nonconforming password, user will see a message indicating what the password must contain");
		changePasswordPage.changePassword(getData("GENERIC.PASSWORD"), "ABC1", "ABC1");
		Assert(changePasswordPage.getSuccessMessage().contains("Your Password has been changed"),
				"The user's password will be changed to a new password.");
		changePasswordPage.continuePwd().logOut().signIn(userName, "ABC1", getData("GENERIC.AUTOMATION.COMPANYCODE"));
		Assert(homePage.isHomePage(), "Admin user will be able to login successfully");

		// cleaning up the data
		homePage.openToolsMenu().openOptionsPage().openEsignatureRequirements().openEditRequirements()
				.uncheckReqEsigPasswordPolicies(userName, "ABC1",
						getData("TC19721.SIGNATUREREASON"),"RecScr")
				.returnOptionsPage().openPasswordPolicies().openEditPasswordPolicies().unsetPasswordComplexity()
				.saveChanges();

	}
}
