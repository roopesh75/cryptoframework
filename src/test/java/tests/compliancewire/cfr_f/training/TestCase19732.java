package tests.compliancewire.cfr_f.training;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;
import initializer.DynamicDataProvider;
import ui.utils.Tools;

public class TestCase19732 extends BaseTest {

	private static final Logger logger = Logger.getLogger(TestCase19732.class);

	@Test(alwaysRun = true,
			description = "Electronic signatures are shown for control document completions"
					+ " if an electronic signature for control document completions was enabled"
					+ " at the time the training was completed.",  groups = {"cfr_f","cfr_f.training"
			})
	public void Training_CD_completions_E_signatures_for_completions() throws Exception {
		addToStorage("adminUser",
				getRandomEntityID().substring(0, 7) + getRandomEntityID().substring(0, 5) + "_AdminUsr");
		loginPage
				.signIn(getData("GENERIC.USER"), getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openUsersPage().openAddUser()
				.addANewUser(getFromStorage("adminUser") + "fn", getFromStorage("adminUser") + "ln",
						getFromStorage("adminUser"), getData("GENERIC.TOP_ORGANIZATION"))
				.openSecurityRoles().openAssignSecurityRole()
				.assignRole(getData("GENERIC.TOP_ORGANIZATION"), getData("GENERIC.ROLE_ADMIN")).logOut()
				.signIn(getFromStorage("adminUser"), getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openUsersPage();
		usersPage.searchUser(getFromStorage("adminUser")).openSecurityRoles();
		Assert(userManagementPage.openSecurityRoles().getSecurityRole().contains("Organization Administrator"), "Precondition: Admin User -" + getFromStorage("adminUser"));
		
		usersPage.openOptionsPage().openEsignatureRequirements().openEditRequirements()
				.uncheckReqEsigCtrlDoc(getFromStorage("adminUser"), getData("GENERIC.PASSWORD"), getData("TC19721.SIGNATUREREASON"),
						"RecScr")
				.openEditRequirements()
				.checkReqEsigCtrlDoc()
				.saveChanges().electronicallySignIn(getFromStorage("adminUser"), getData("GENERIC.PASSWORD"),
						getData("TC19721.SIGNATUREREASON"), "RecScr");
		Assert(esignatureRequirementsPage.getEsignatureRequirements(0).contains("images/Checked.jpg"),
				"Precondition 2: E-signature for CD completions is enabled");
		optionsPage.openTrainingPage().searchTraining(getData("TC19732.TRAINING"));
		Assert(trainingManagementPage.getTrainingTitle().contains(getData("TC19732.TRAINING")), "Precondition 3: Control Document");
		addToStorage("randomTag1", getRandomEntityID().substring(0,8));
		trainingManagementPage.openUsersPage().openAddUser().addANewUser(getFromStorage("randomTag1") + "usr1", getFromStorage("randomTag1") + "usr1",
				getFromStorage("randomTag1") + "usr1", getData("TestCase19732.ORGANIZATION")).returnFromUserManagement().openAssignmentPage().
		openAddAssignmentDefinition().addAssignment(getData("TC19732.TRAINING"),getFromStorage("randomTag1") + "usr1");
		
		Assert(assignmentManagementPage.getPRINTTableBorder().contains(getData("TC19732.TRAINING")), "Precondition 4: Training has been assigned");
		assignmentManagementPage.returnToAssignmentPage();	
		assignmentPage.logOut().signIn(getFromStorage("randomTag1") + "usr1", "Pass1234", getData("GENERIC.AUTOMATION.COMPANYCODE")).getToDoPage().
		openTrainingItem(getData("TC19732.TRAINING")).electronicallySign(getFromStorage("randomTag1") + "usr1", "Pass1234");
		Assert(homePage.getPendingReports().contains("Your report contains 0 records."), "The user will be able to complete the CD and E-sign. ");
		Assert(homePage.openHistoryPage().openTrainingItemGeneralInfo(getData("TC19732.TRAINING")).getCompletionDate().contains(Tools.getDateTime1())
				&& historyPage.getSignature().contains(Tools.getDateTime1()) &&
				historyPage.getCompletionDateTimeZone().contains(Tools.getSystemTimeZone(true)) && historyPage.getSignatureTimeZone().contains(Tools.getSystemTimeZone(true)),
				"History TI will display the Completed on Date/Time stamp;Completion date will be displayed based on User's Operative Time zone and e-signature field  display a computer generated date and time stamp based on User's PC time zone. ");
		homePage.logOut().signIn(getFromStorage("adminUser"), getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE")).openToolsMenu().
		openTrainingPage().openCompletionReportByTraining().runCompletionReport(getFromStorage("randomTag1") + "usr1",getData("TC19732.TRAINING")).openQualifiedTraining(getFromStorage("randomTag1") + "usr1").getCompletionDate();
		Assert(trainingManagementPage.getCompletionDate().contains(Tools.getDateTime())
				&& trainingManagementPage.getSigniture().contains(Tools.getDateTime()) &&
				trainingManagementPage.getCompletionDateTimeZone().contains(Tools.getSystemTimeZone(true)) &&
				trainingManagementPage.getSignatureTimeZone().contains(Tools.getSystemTimeZone(true))
				, "Completion Report displays Completed On Date based on User's OTZ.Completion date will be displayed based on the User's OTZ and e-signature field will display a computer generated date and time stamp based on User's PC time zone in the Completion Information page.");
		
		/*Assert(true
				, "Completion Report displays Completed On Date based on User's OTZ.Completion date will be displayed based on the User's OTZ and e-signature field will display a computer generated date and time stamp based on User's PC time zone in the Completion Information page.");
*/
	}

}
