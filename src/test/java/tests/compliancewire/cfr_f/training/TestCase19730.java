package tests.compliancewire.cfr_f.training;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;
import initializer.DynamicDataProvider;
import ui.utils.Tools;

public class TestCase19730 extends BaseTest {

	private static final Logger logger = Logger.getLogger(TestCase19730.class);

	@Test(alwaysRun = true, dataProviderClass = DynamicDataProvider.class, dataProvider = "createAdminUser", description = 
			"Training_Exam completions_E-signatures for exam completions", groups = {"cfr_f","cfr_f.training"})
	public void Training_Exam_completions_E_signatures_for_exam_completions(String userName) throws Exception {
		loginPage.signIn(userName, getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openOptionsPage().openEsignatureRequirements().openEditRequirements()
				.uncheckReqEsigCustomExamComp(userName, getData("GENERIC.PASSWORD"), getData("TC19721.SIGNATUREREASON"),
						"RecScr")
				.openEditRequirements()
				.checkReqEsigCustomExamComp()
				.saveChanges().electronicallySignIn(userName, getData("GENERIC.PASSWORD"),
						getData("TC19721.SIGNATUREREASON"), "RecScr");
		Assert(esignatureRequirementsPage.getEsignatureRequirements(5).contains("images/Checked.jpg"),
				"Precondition 1: e-signatures enabled for custom exam completions");
		optionsPage.openTrainingPage().searchTraining(getData("TC19752.TRAININGITEM"));
		Assert(trainingManagementPage.getCustomExamTrainingType().contains("Custom Exam"),
				"PreCondition 2:Custom Exam");
		addToStorage("randomTag1", getRandomEntityID().substring(0, 7));
		classesPage.openUsersPage().openAddUser()
				.addANewUser(getFromStorage("randomTag1") + "usr2", getFromStorage("randomTag1") + "usr2",
						getFromStorage("randomTag1") + "usr2", getData("TestCase19751.ORGANIZATION"))
				.openSecurityRoles();

		Assert(userManagementPage.getSecurityRole().equalsIgnoreCase("Learner"), "PreCondition:Learner");
		userManagementPage.logOut().signIn(getFromStorage("randomTag1") + "usr2", getData("GENERIC.PASSWORD"),
				getData("GENERIC.AUTOMATION.COMPANYCODE")).openCatalog().searchTrainingItem(getData("TC19752.TRAININGITEM"))
		.trainingItemGeneralInfo(getData("TC19752.TRAININGITEM")).completeCustomExam().electronicallySign(getFromStorage("randomTag1") + "usr2", getData("GENERIC.PASSWORD"));

		Assert(catalogPage.isCatalogPage(), "The user will be able to complete the exam and e-sign. ");
		Assert(catalogPage.openHistoryPage().openTrainingItemGeneralInfo(getData("TC19752.TRAININGITEM")).getCompletionInfoPageContent().contains(getFromStorage("randomTag1") + "usr2")
				&& historyPage.getCompletionInfoPageContent().contains(Tools.getDateTime1()),
				"The history of the TI will display the Completed on Date/Time stamp.");
		homePage.logOut().signIn(userName, getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
		.openToolsMenu().openTrainingPage().openCompletionReportByTraining().
		runCompletionReport(getFromStorage("randomTag1") + "usr2",getData("TC19752.TRAININGITEM")).
		openQualifiedTraining(getFromStorage("randomTag1") + "usr2");
		Assert(trainingManagementPage.getCompletionDate().contains(Tools.getDateTime())
				&& trainingManagementPage.getSigniture().contains(Tools.getDateTime()) &&
				trainingManagementPage.getCompletionDateTimeZone().contains(Tools.getSystemTimeZone()) &&
				trainingManagementPage.getSignatureTimeZone().contains(Tools.getSystemTimeZone())
				, "Completion Report displays Completed On Date based on User's OTZ.Completion date will be displayed based on the User's OTZ and e-signature field will display a computer generated date and time stamp based on User's PC time zone in the Completion Information page.");

		
	}

}
