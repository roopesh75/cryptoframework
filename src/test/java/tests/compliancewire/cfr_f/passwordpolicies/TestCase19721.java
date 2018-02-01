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

public class TestCase19721 extends BaseTest {
	private static final Logger logger = Logger.getLogger(TestCase19721.class);

	@Test(priority = 3, alwaysRun = true, description = "Making a change to the password policies can require "
					+ "authentication (electronic signature)", groups = {
			"cfr_f", "cfr_f.passwordpolicies" })
	public void Options_Password_Policies_Authentication() throws Exception {

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
		optionsPage.openEsignatureRequirements().openEditRequirements().uncheckReqEsigPasswordPolicies(getFromStorage("adminUser"),
				getData("GENERIC.PASSWORD"), getData("TC19721.SIGNATUREREASON"), "RecScr");
		esignatureRequirementsPage.openEditRequirements().checkReqEsigPasswordPolicies().saveChanges()
				.electronicallySignIn(getFromStorage("adminUser"), getData("GENERIC.PASSWORD"), getData("TC19721.SIGNATUREREASON"),
						"RecScr");

		Assert(esignatureRequirementsPage.getEsignatureRequirements(9).contains("images/Checked.jpg"),
				"Precondition: Require e-Signatures for Password Policies enabled");
		esignatureRequirementsPage.returnOptionsPage().openPasswordPolicies();
		Assert(definePasswordPoliciesPage.isDefinePasswordPoliciesPage(),
				"Admin user will be navigated to Define Password Policies page");
		definePasswordPoliciesPage.openEditPasswordPolicies().setMinPwdLength("3").saveChanges();
		Assert(esignatureRequirementsPage.getEsignatureOverlay("RecScr").indexOf("User Id") >= 0,
				"e-Signature Required popup window will be displayed");
		Assert(esignatureRequirementsPage.getPwdBoxAttribute().equalsIgnoreCase("password"),
				"On entering Password for e-signature, password will be displayed in encrypted form");
		definePasswordPoliciesPage.electronicallySignIn(getFromStorage("adminUser"), getData("GENERIC.PASSWORD"),
				getData("TC19721.SIGNATUREREASON"), "RecScr");
		optionsPage.openLogsPage().openEventLogReport().addUsr(getFromStorage("adminUser")).selectEvent("61").openRunThisReport().sortByDateTime()
				.sortByDateTime();
		Assert(eventLogReportPage.getEventLogTopRow().contains("Password Policy Updated")
				&& eventLogReportPage.getEventLogTopRow().contains(getFromStorage("adminUser"))
				&& eventLogReportPage.getEventLogTopRow().contains(getFromStorage("adminUser") + "fn")
				&& eventLogReportPage.getEventLogTopRow().contains(getFromStorage("adminUser") + "ln"),

				"The Password policy Update event will be displayed with date/time stamp based on User's PC Time Zone.");
		// &&eventLogReportPage.getEventLogTopRow().contains(Tools.getDateTime())
		// ,
		// cleaning the data
		logsPage.openOptionsPage().openEsignatureRequirements().openEditRequirements()
				.uncheckReqEsigPasswordPolicies(getFromStorage("adminUser"), getData("GENERIC.PASSWORD"),
						getData("TC19721.SIGNATUREREASON"), "RecScr")
				.returnOptionsPage().openPasswordPolicies().openEditPasswordPolicies().setMinPwdLength("1")
				.saveChanges();

	}
}

