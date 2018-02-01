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
import ui.utils.Tools;

public class TestCase19752 extends BaseTest {
	private static final Logger logger = Logger.getLogger(TestCase19752.class);

	@Test(alwaysRun = true, description = "Electronic signatures will be displayed within the system", groups = {
			"cfr_f", "cfr_f.knowledgecenter" })
	public void KnowledgeCenterExam_esign() throws Exception {
		addToStorage("customExamTrng", getRandomEntityID().substring(0, 7) + "CEtrng");
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

		Assert(optionsPage.openTrainingPage().openAddTrainingItem().openCustomExamCourse()
				.addCustomExam(getFromStorage("customExamTrng"), getFromStorage("customExamTrng"), "Auto Polaris 2.0")
				.openCustomExam().openSingleResponseQuestion().addSingleResponseQuestion(true)
				.openReturn().openActivateVersion().activateTraining().getTrainingStatus().contains("Effective")
				&& trainingManagementPage.getCustomExamTrainingType().contains("Custom Exam"),
				"Precondition 2:Custom Exam");
		addToStorage("randomTag1", getRandomEntityID().substring(0, 7));
		classesPage.openUsersPage().openAddUser()
				.addANewUser(getFromStorage("randomTag1") + "fn", getFromStorage("randomTag1") + "ln",
						getFromStorage("randomTag1") + "usr", getData("TestCase19751.ORGANIZATION"))
				.openSecurityRoles();

		Assert(userManagementPage.getSecurityRole().equalsIgnoreCase("Learner"), "Precondition 3:Learner");
		userManagementPage.logOut();
		loginPage.signIn(getFromStorage("randomTag1") + "usr", getData("GENERIC.PASSWORD"),
				getData("GENERIC.AUTOMATION.COMPANYCODE"));
		Assert(homePage.isHomePage(), "Learner will be able to login successfully");
		
		Assert(homePage.openCatalog().isCatalogPage(), "Learner  will be able to navigated to Catalog page");
		catalogPage.searchTrainingItem(getFromStorage("customExamTrng"))
				.trainingItemGeneralInfo(getFromStorage("customExamTrng")).completeCustomExam();
		Assert(catalogPage.isEsignatureWindow(), "e-Signature window will be displayed");
		Assert(catalogPage.getPasswordBoxAttribute().equalsIgnoreCase("password"),
				"On entering Password for e-signature, password will be displayed in encrypted form");

		catalogPage.electronicallySign(getFromStorage("randomTag1") + "usr", getData("GENERIC.PASSWORD"));
		addToStorage("systemTime", Tools.getDateTime("MM/dd/yyyy hh:mm:ss a"));

		Assert(catalogPage.openHistoryPage().isHistoryPage(), "Learner  will be able to navigated to History page.");
		historyPage.openTrainingItemGeneralInfo(getFromStorage("customExamTrng")).scrollDown();
		Assert(historyPage.getCompletionInfoPageContent().contains(getFromStorage("customExamTrng"))
				&& historyPage.getCompletionDate()
						.contains(historyPage.getSignature().replace("|", "-").split("-")[1].trim())
				&& historyPage.getSignature().contains(getFromStorage("randomTag1") + "fn")
				&& historyPage.getSignature().contains(getFromStorage("randomTag1") + "ln")
				&& historyPage.getSignature().contains(getFromStorage("randomTag1") + "usr")
				&& historyPage.getSignature().contains(getFromStorage("systemTime").split(" ")[0])
				&& historyPage.getSignature().contains("I Acknowledge"),
				"Completion information screen will display the User, Training and the completion Information");
		AssertTime("MM/dd/yyyy hh:mm:ss a", historyPage.getSignature().replace("|", "-").split("-")[1].trim(),
				getFromStorage("systemTime"),
				"Electronic signature applied at Catalog page and the one displayed in History page match. PC SYSTEM TIME: "
						+ getFromStorage("systemTime"));
	}

}
