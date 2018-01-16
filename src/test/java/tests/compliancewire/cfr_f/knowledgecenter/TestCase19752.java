package tests.compliancewire.cfr_f.knowledgecenter;

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
import ui.utils.Tools;

public class TestCase19752 extends BaseTest {
	private static final Logger logger = Logger.getLogger(TestCase19752.class);

	@Test(alwaysRun = true, dataProviderClass = DynamicDataProvider.class, dataProvider = "createAdminUser", description = "Knowledge Center_Exam_e-sign", 
			groups = {"cfr_f","cfr_f.knowledgecenter"})
	public void KnowledgeCenterExam_esign(String userName) throws Exception {

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
		userManagementPage.logOut();
		loginPage.signIn(getFromStorage("randomTag1") + "usr2", getData("GENERIC.PASSWORD"),
				getData("GENERIC.AUTOMATION.COMPANYCODE"));
		Assert(homePage.isHomePage(), "Learner will be able to login successfully");
		homePage.openCatalog();
		Assert(catalogPage.isCatalogPage(), "Learner  will be able to navigated to Catalog page");
		catalogPage.searchTrainingItem(getData("TC19752.TRAININGITEM"))
				.trainingItemGeneralInfo(getData("TC19752.TRAININGITEM")).completeCustomExam();
		Assert(catalogPage.isEsignatureWindow(), "e-Signature window will be displayed");
		Assert(catalogPage.getPasswordBoxAttribute().equalsIgnoreCase("password"),
				"On entering Password for e-signature, password will be displayed in encrypted form");

		catalogPage.electronicallySign(getFromStorage("randomTag1") + "usr2", getData("GENERIC.PASSWORD"))
				.openHistoryPage();
		Assert(historyPage.isHistoryPage(), "Learner  will be able to navigated to History page.");
		historyPage.openTrainingItemGeneralInfo(getData("TC19752.TRAININGITEM")).scrollDown();
		Assert(historyPage.getCompletionInfoPageContent().contains(getFromStorage("randomTag1") + "usr2"),
				"Completion information screen will display the User, Training and the completion Information");
//&& historyPage.getCompletionInfoPageContent().contains(Tools.getDateTime())
	}
}
