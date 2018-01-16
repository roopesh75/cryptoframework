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

	@Test(priority = 3, alwaysRun = true, dataProviderClass = DynamicDataProvider.class, dataProvider = "createAdminUser", description = "Options_Password Policies_Authentication", groups = {
			"cfr_f", "cfr_f.passwordpolicies" })
	public void Options_Password_Policies_Authentication(String userName) throws Exception {

		loginPage.signIn(userName, getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"));
		Assert(homePage.isHomePage(), "Admin user will be able to login successfully");
		homePage.openToolsMenu().openUsersPage();
		usersPage.searchUser(userName).openSecurityRoles();
		Assert(true, "Precondition: Admin User");
		usersPage.openOptionsPage();
		Assert(optionsPage.isOptionsPage(), "Admin user will be navigated to Options tab");
		optionsPage.openEsignatureRequirements().openEditRequirements().uncheckReqEsigPasswordPolicies(userName,
				getData("GENERIC.PASSWORD"), getData("TC19721.SIGNATUREREASON"), "RecScr");
		esignatureRequirementsPage.openEditRequirements().checkReqEsigPasswordPolicies().saveChanges()
				.electronicallySignIn(userName, getData("GENERIC.PASSWORD"), getData("TC19721.SIGNATUREREASON"),
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
		definePasswordPoliciesPage.electonicallySignIn(userName, getData("GENERIC.PASSWORD"),
				getData("TC19721.SIGNATUREREASON"), "RecScr");
		optionsPage.openLogsPage().openEventLogReport().selectEvent("61").openRunThisReport().sortByDateTime()
				.sortByDateTime();
		Assert(eventLogReportPage.getEventLogTopRow().contains("Password Policy Updated")
				&& eventLogReportPage.getEventLogTopRow().contains(userName)
				&& eventLogReportPage.getEventLogTopRow().contains("Pinaa")
				&& eventLogReportPage.getEventLogTopRow().contains("Genz"),

				"The Password policy Update event will be displayed with date/time stamp based on User's PC Time Zone.");
		// &&eventLogReportPage.getEventLogTopRow().contains(Tools.getDateTime())
		// ,
		// cleaning the data
		logsPage.openOptionsPage().openEsignatureRequirements().openEditRequirements()
				.uncheckReqEsigPasswordPolicies(userName, getData("GENERIC.PASSWORD"),
						getData("TC19721.SIGNATUREREASON"), "RecScr")
				.returnOptionsPage().openPasswordPolicies().openEditPasswordPolicies().setMinPwdLength("1")
				.saveChanges();

	}
}

