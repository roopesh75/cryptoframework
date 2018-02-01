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

	@Test(priority = 6, alwaysRun = true,
			description = "Electronic signatures are comprised of signer info (First Name, Last Name, User ID), computer "
					+ "generated date and time stamp when signature was executed, and the "
					+ "meaning/reason associated with signature", groups = {
			"cfr_f", "cfr_f.esignature" })
	public void Options_esignature_editing() throws Exception {
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
		Assert(homePage.isHomePage(),true, "Admin user will be able to login successfully");
		homePage.openToolsMenu().openUsersPage().searchUser(getFromStorage("adminUser")).openSecurityRoles();
		Assert(userManagementPage.getUserId(), getFromStorage("adminUser"), "Precondition: Admin User");
		usersPage.openOptionsPage();
		Assert(optionsPage.isOptionsPage(),true, "Admin user will be navigated to Options tab");
		optionsPage.openEsignatureRequirements();
		Assert(esignatureRequirementsPage.isEsignautreRequirementsPage(),true,
				"Admin user will be navigated to e-Signature Requirements page");
		esignatureRequirementsPage.openEditRequirements().uncheckReqEsigPasswordPolicies(getFromStorage("adminUser"),
				getData("GENERIC.PASSWORD"), getData("TC19721.SIGNATUREREASON"), "RecScr");
		esignatureRequirementsPage.openEditRequirements().checkReqEsigPasswordPolicies().saveChanges();
		Assert(esignatureRequirementsPage.getEsignatureOverlay("RecScr"),("User Id"),
				"e-Signature Required popup window will be displayed");
		Assert(esignatureRequirementsPage.getPwdBoxAttribute(),("password"),
				"On entering Password for e-signature, password will be displayed in encrypted form");
		definePasswordPoliciesPage.electronicallySignIn(getFromStorage("adminUser"), getData("GENERIC.PASSWORD"),
				getData("TC19721.SIGNATUREREASON"), "RecScr");
		addToStorage("systemTime", Tools.getDateTime("MM/dd/yyyy hh:mm:ss a"));
		addToStorage("eSigPageTime", esignatureRequirementsPage.getEsignature(getFromStorage("adminUser")).split("-")[1].trim());
		AssertTime("MM/dd/yyyy hh:mm:ss a",esignatureRequirementsPage.getEsignature(getFromStorage("adminUser")).split("-")[1].trim(),getFromStorage("systemTime"),
				"Electronic signature comprised of signer information (First Name, Last Name, User ID), date and time stamp will display User's PC time zone, and reason associated with signature . PC SYSTEM TIME: "+ getFromStorage("systemTime"));
		Assert(esignatureRequirementsPage.getEsignature(getFromStorage("adminUser")),
				getFromStorage("adminUser")+"ln","The electronic signature will be comprised of the signer information (First Name, Last Name, User ID), date and time stamp will display the User's PC time zone, and the meaning/reason associated with signature");
		Assert(esignatureRequirementsPage.getEsignature(getFromStorage("adminUser")),
				getFromStorage("adminUser")+"fn","The electronic signature will be comprised of the signer information (First Name, Last Name, User ID), date and time stamp will display the User's PC time zone, and the meaning/reason associated with signature");
		Assert(esignatureRequirementsPage.getEsignature(getFromStorage("adminUser")),
				(getFromStorage("adminUser"))
				,
				"The electronic signature will be comprised of the signer information (First Name, Last Name, User ID), date and time stamp will display the User's PC time zone, and the meaning/reason associated with signature");	
		Assert(esignatureRequirementsPage.getEsignature(getFromStorage("adminUser")),(getData("TC19721.SIGNATUREREASON"))
				,
				"The electronic signature will be comprised of the signer information (First Name, Last Name, User ID), date and time stamp will display the User's PC time zone, and the meaning/reason associated with signature");	
		optionsPage.openLogsPage().openEventLogReport().addUsr(getFromStorage("adminUser")).selectEvent("52").openRunThisReport().sortByDateTime()
				.sortByDateTime();
		Assert(eventLogReportPage.getEventLogTopRowTime(),(getFromStorage("eSigPageTime")),"The Event Log report with the 'e-signature applied' event is displayed with date/ time stamp based on User PC time zone.");				
		
		Assert(eventLogReportPage.getEventLogTopRow(),("e-Signature Applied")
               ,"The Event Log report with the 'e-signature applied' event is displayed with date/ time stamp based on User PC time zone.");
		
		Assert(eventLogReportPage.getEventLogTopRow(),(getFromStorage("adminUser"))
              ,"The Event Log report with the 'e-signature applied' event is displayed with date/ time stamp based on User PC time zone.");

		
		Assert(eventLogReportPage.getEventLogTopRow(),getFromStorage("adminUser")+"fn"
              ,"The Event Log report with the 'e-signature applied' event is displayed with date/ time stamp based on User PC time zone.");

		Assert(eventLogReportPage.getEventLogTopRow(),getFromStorage("adminUser")+"ln","The Event Log report with the 'e-signature applied' event is displayed with date/ time stamp based on User PC time zone.");
		logsPage.openOptionsPage().openEsignatureRequirements().openEditRequirements().uncheckReqEsigPasswordPolicies(
				getFromStorage("adminUser"), getData("GENERIC.PASSWORD"), getData("TC19721.SIGNATUREREASON"), "RecScr");

	}

}
