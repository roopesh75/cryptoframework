package tests.compliancewire.cfr_f.training;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;
import ui.utils.Tools;

public class TestCase19730 extends BaseTest {

	private static final Logger logger = Logger.getLogger(TestCase19730.class);

	@Test(alwaysRun = true, description = "Electronic signatures are shown for Exam completions if electronic signatures for "
			+ "exam completions enabled at the time the training was completed", groups = { })
	public void Training_Exam_completions_E_signatures_for_exam_completions() throws Exception {
		addToStorage("adminUser",
				getRandomEntityID().substring(0, 7) + getRandomEntityID().substring(0, 5) + "_AdminUsr");
		loginPage
				.signIn(getData("GENERIC.USER"), getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openUsersPage().openAddUser()
				.addANewUser(getFromStorage("adminUser") + "fn", getFromStorage("adminUser") + "ln",
						getFromStorage("adminUser"), getData("GENERIC.TOP_ORGANIZATION"))
				.openSecurityRoles().openAssignSecurityRole()
				.assignRole(getData("GENERIC.TOP_ORGANIZATION"), getData("GENERIC.ROLE_ADMIN")).logOut()
				.signIn(getFromStorage("adminUser"), getData("GENERIC.PASSWORD"),
						getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openOptionsPage().openEsignatureRequirements().openEditRequirements()
				.uncheckReqEsigCustomExamComp(getFromStorage("adminUser"), getData("GENERIC.PASSWORD"),
						getData("TC19721.SIGNATUREREASON"), "RecScr")
				.openEditRequirements().checkReqEsigCustomExamComp().saveChanges()
				.electronicallySignIn(getFromStorage("adminUser"), getData("GENERIC.PASSWORD"),
						getData("TC19721.SIGNATUREREASON"), "RecScr");
		Assert(esignatureRequirementsPage.getEsignatureRequirements(5).contains("images/Checked.jpg"),
				"Precondition 1: e-signatures enabled for custom exam completions");
		optionsPage.openTrainingPage().searchTraining(getData("TC19752.TRAININGITEM"));
		Assert(trainingManagementPage.getCustomExamTrainingType().contains("Custom Exam"),
				"Precondition 2:Custom Exam");
		addToStorage("randomTag1", getRandomEntityID().substring(0, 7));
		classesPage.openUsersPage().openAddUser()
				.addANewUser(getFromStorage("randomTag1") + "usr2", getFromStorage("randomTag1") + "usr2",
						getFromStorage("randomTag1") + "usr2", getData("TestCase19751.ORGANIZATION"))
				.openSecurityRoles();

		Assert(userManagementPage.getSecurityRole().equalsIgnoreCase("Learner"), "Precondition:Learner");
		userManagementPage.logOut()
				.signIn(getFromStorage("randomTag1") + "usr2", getData("GENERIC.PASSWORD"),
						getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openCatalog().searchTrainingItem(getData("TC19752.TRAININGITEM"))
				.trainingItemGeneralInfo(getData("TC19752.TRAININGITEM")).completeCustomExam()
				.electronicallySign(getFromStorage("randomTag1") + "usr2", getData("GENERIC.PASSWORD"));

		Assert(catalogPage.isCatalogPage(), "The user will be able to complete the exam and e-sign. ");
		Assert(catalogPage.openHistoryPage().openTrainingItemGeneralInfo(getData("TC19752.TRAININGITEM"))
				.getCompletionInfoPageContent().contains(getFromStorage("randomTag1") + "usr2")
				&& historyPage.getCompletionInfoPageContent().contains(Tools.getDateTime1()),
				"The history of the TI will display the Completed on Date/Time stamp.");
		homePage.logOut()
				.signIn(getFromStorage("adminUser"), getData("GENERIC.PASSWORD"),
						getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openTrainingPage().openCompletionReportByTraining()
				.runCompletionReport(getFromStorage("randomTag1") + "usr2", getData("TC19752.TRAININGITEM"))
				.openQualifiedTraining(getFromStorage("randomTag1") + "usr2");
		Assert(trainingManagementPage.getCompletionDate().contains(Tools.getDateTime())
				&& trainingManagementPage.getSigniture().contains(Tools.getDateTime())
				&& trainingManagementPage.getCompletionDateTimeZone().contains(Tools.getSystemTimeZone(true))
				&& trainingManagementPage.getSignatureTimeZone().contains(Tools.getSystemTimeZone(true)),
				"Completion Report displays Completed On Date based on User's OTZ.Completion date will be displayed based on the User's OTZ and e-signature field will display a computer generated date and time stamp based on User's PC time zone in the Completion Information page.");

	}

}
