package tests.compliancewire.cfr_f.passwordpolicies;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;
import initializer.DynamicDataProvider;

public class TestCase19723 extends BaseTest {

	private static final Logger logger = Logger.getLogger(TestCase19723.class);

	@Test(priority = 4,alwaysRun = true, description =
			"Password policies can be defined to require a password complexity that contains a"
			+ " combination of letters (uppercase, lowercase) and/or numbers", groups = {
			"cfr_f", "cfr_f.passwordpolicies" })
	public void Options_Password_policies_password_complexity_requirement() throws Exception {
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
		usersPage.openOptionsPage().openEsignatureRequirements().openEditRequirements()
				.uncheckReqEsigPasswordPolicies(getFromStorage("adminUser"), getData("GENERIC.PASSWORD"),
						getData("TC19721.SIGNATUREREASON"),"RecScr");
		esignatureRequirementsPage.openEditRequirements().checkReqEsigPasswordPolicies().saveChanges()
				.electronicallySignIn(getFromStorage("adminUser"), getData("GENERIC.PASSWORD"), getData("TC19721.SIGNATUREREASON"),"RecScr");
		esignatureRequirementsPage.returnOptionsPage().openPasswordPolicies().openEditPasswordPolicies();
		Assert(definePasswordPoliciesPage.isEditPasswordPoliciesPage(),
				"Edit password policies page should be displayed");
		definePasswordPoliciesPage.setPasswordComplexity().saveChanges().electronicallySignIn(getFromStorage("adminUser"),
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
		changePasswordPage.continuePwd().logOut().signIn(getFromStorage("adminUser"), "ABC1", getData("GENERIC.AUTOMATION.COMPANYCODE"));
		Assert(homePage.isHomePage(), "Admin user will be able to login successfully");

		// cleaning up the data
		homePage.openToolsMenu().openOptionsPage().openEsignatureRequirements().openEditRequirements()
				.uncheckReqEsigPasswordPolicies(getFromStorage("adminUser"), "ABC1",
						getData("TC19721.SIGNATUREREASON"),"RecScr")
				.returnOptionsPage().openPasswordPolicies().openEditPasswordPolicies().unsetPasswordComplexity()
				.saveChanges();

	}
}
