package tests.compliancewire.cfr_f.esignature;

/**
 * @Roopesh
 * 1.Time zone is not captured as part of expected result 6 and 7 
 * 2.Test step 5 should be split to 2 separate steps "Verify that Password is displayed in encrypted form for e-signature"
 * 3.Create web services to check and uncheck EsignatureRequirements 
 * 4.getesignature method id taking time. so need to verify it
 */
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;
import initializer.DynamicDataProvider;
import tests.compliancewire.cfr_f.passwordpolicies.TestCase19721;
import ui.utils.Tools;

public class TestCase19719 extends BaseTest {
	private static final Logger logger = Logger.getLogger(TestCase19721.class);

	@Test(priority = 6, alwaysRun = true, dataProviderClass = DynamicDataProvider.class, dataProvider = "createAdminUser", description = "Options_e-signature_editing", groups = {
			"cfr_f", "cfr_f.esignature" })
	public void Options_esignature_editing(String userName) throws Exception {
		loginPage.signIn(userName, getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"));
		Assert(homePage.isHomePage(),true, "Admin user will be able to login successfully");
		homePage.openToolsMenu().openUsersPage().searchUser(userName).openSecurityRoles();
		Assert(userManagementPage.getUserId(), userName, "Precondition: Admin User");
		usersPage.openOptionsPage();
		Assert(optionsPage.isOptionsPage(),true, "Admin user will be navigated to Options tab");
		optionsPage.openEsignatureRequirements();
		Assert(esignatureRequirementsPage.isEsignautreRequirementsPage(),true,
				"Admin user will be navigated to e-Signature Requirements page");
		esignatureRequirementsPage.openEditRequirements().uncheckReqEsigPasswordPolicies(userName,
				getData("GENERIC.PASSWORD"), getData("TC19721.SIGNATUREREASON"), "RecScr");
		esignatureRequirementsPage.openEditRequirements().checkReqEsigPasswordPolicies().saveChanges();
		Assert(esignatureRequirementsPage.getEsignatureOverlay("RecScr"),("User Id"),
				"e-Signature Required popup window will be displayed");
		Assert(esignatureRequirementsPage.getPwdBoxAttribute(),("password"),
				"On entering Password for e-signature, password will be displayed in encrypted form");
		definePasswordPoliciesPage.electonicallySignIn(userName, getData("GENERIC.PASSWORD"),
				getData("TC19721.SIGNATUREREASON"), "RecScr");
		addToStorage("systemTime", Tools.getDateTime("MM/dd/yyyy hh:mm:ss a"));
		addToStorage("eSigPageTime", esignatureRequirementsPage.getEsignature(userName).split("-")[1].trim());
		AssertTime("MM/dd/yyyy hh:mm:ss a",esignatureRequirementsPage.getEsignature(userName).split("-")[1].trim(),getFromStorage("systemTime"),
				"Electronic signature comprised of signer information (First Name, Last Name, User ID), date and time stamp will display User's PC time zone, and reason associated with signature . PC SYSTEM TIME: "+ getFromStorage("systemTime"));
		Assert(esignatureRequirementsPage.getEsignature(userName),("Genz"),"The electronic signature will be comprised of the signer information (First Name, Last Name, User ID), date and time stamp will display the User's PC time zone, and the meaning/reason associated with signature");
		Assert(esignatureRequirementsPage.getEsignature(userName),("Pinaa"),"The electronic signature will be comprised of the signer information (First Name, Last Name, User ID), date and time stamp will display the User's PC time zone, and the meaning/reason associated with signature");
		Assert(esignatureRequirementsPage.getEsignature(userName),(userName)
				,
				"The electronic signature will be comprised of the signer information (First Name, Last Name, User ID), date and time stamp will display the User's PC time zone, and the meaning/reason associated with signature");	
		Assert(esignatureRequirementsPage.getEsignature(userName),(getData("TC19721.SIGNATUREREASON"))
				,
				"The electronic signature will be comprised of the signer information (First Name, Last Name, User ID), date and time stamp will display the User's PC time zone, and the meaning/reason associated with signature");	
		optionsPage.openLogsPage().openEventLogReport().selectEvent("52").openRunThisReport().sortByDateTime()
				.sortByDateTime();
		Assert(eventLogReportPage.getEventLogTopRowTime(),(getFromStorage("eSigPageTime")),"The Event Log report with the 'e-signature applied' event is displayed with date/ time stamp based on User PC time zone.");				
		
		Assert(eventLogReportPage.getEventLogTopRow(),("e-Signature Applied")
               ,"The Event Log report with the 'e-signature applied' event is displayed with date/ time stamp based on User PC time zone.");
		
		Assert(eventLogReportPage.getEventLogTopRow(),(userName)
              ,"The Event Log report with the 'e-signature applied' event is displayed with date/ time stamp based on User PC time zone.");

		
		Assert(eventLogReportPage.getEventLogTopRow(),("Pinaa")
              ,"The Event Log report with the 'e-signature applied' event is displayed with date/ time stamp based on User PC time zone.");

		Assert(eventLogReportPage.getEventLogTopRow(),("Genz"),"The Event Log report with the 'e-signature applied' event is displayed with date/ time stamp based on User PC time zone.");
		logsPage.openOptionsPage().openEsignatureRequirements().openEditRequirements().uncheckReqEsigPasswordPolicies(
				userName, getData("GENERIC.PASSWORD"), getData("TC19721.SIGNATUREREASON"), "RecScr");

	}

}
