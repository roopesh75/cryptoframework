package tests.compliancewire.basic.catalog;


import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;
import ui.utils.Tools;

public class TestCase20197 extends BaseTest {
	private static final Logger logger = Logger.getLogger(TestCase20197.class);

	@Test(alwaysRun = true, description = "Custom exam can be launched and completed from Catalog", groups = {})
	public void Catalog_Custom_exam_complete() throws Exception {
		addToStorage("customExamTrng", getRandomEntityID().substring(0, 7) + "CEtrng");
		addToStorage("adminUser",
				getRandomEntityID().substring(0, 7) + getRandomEntityID().substring(0, 5) + "_AdminUsr");
		addToStorage("learnerUser", getRandomEntityID().substring(0, 7));
		loginPage
				.signIn(getData("GENERIC.USER"), getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openUsersPage().openAddUser()
				.addANewUser(getFromStorage("adminUser") + "fn", getFromStorage("adminUser") + "ln",
						getFromStorage("adminUser"), getData("GENERIC.TOP_ORGANIZATION"))
				.openSecurityRoles().openAssignSecurityRole()
				.assignRole(getData("GENERIC.TOP_ORGANIZATION"), getData("GENERIC.ROLE_ADMIN")).logOut()
				.signIn(getFromStorage("adminUser"), getData("GENERIC.PASSWORD"),
						getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openUsersPage().openAddUser()
				.addANewUser(getFromStorage("learnerUser") + "fn", getFromStorage("learnerUser") + "ln",
						getFromStorage("learnerUser") + "usr", getData("TestCase19751.ORGANIZATION"))
				.openSecurityRoles();
		Assert(userManagementPage.getSecurityRole().equalsIgnoreCase("Learner"), "Precondition :Learner User");

		userManagementPage.returnFromUserManagement().openOptionsPage().openEsignatureRequirements()
				.openEditRequirements()
				.uncheckReqEsigCustomExamComp(getFromStorage("adminUser"), getData("GENERIC.PASSWORD"),
						getData("TC19721.SIGNATUREREASON"), "RecScr")
				.openEditRequirements().checkReqEsigCustomExamComp().saveChanges()
				.electronicallySignIn(getFromStorage("adminUser"), getData("GENERIC.PASSWORD"),
						getData("TC19721.SIGNATUREREASON"), "RecScr");
		Assert(esignatureRequirementsPage.getEsignatureRequirements(5).contains("images/Checked.jpg"),
				"Precondition: E- signature for custom Exam is enabled");

		Assert(optionsPage.openTrainingPage().openAddTrainingItem().openCustomExamCourse()
				.addCustomExam(getFromStorage("customExamTrng"), getFromStorage("customExamTrng"), "Auto Polaris 2.0")
				.openCustomExam().openSingleResponseQuestion().addSingleResponseQuestion(true).openReturn()
				.openActivateVersion().activateTraining().getTrainingStatus().contains("Effective")
				&& trainingManagementPage.getCustomExamTrainingType().contains("Custom Exam"),
				"Precondition :Custom Exam");
		Assert(trainingManagementPage.openReturn().openCompletionReportByTraining()
				.runCompletionReport(getFromStorage("learnerUser") + "usr", getFromStorage("customExamTrng"))
				.getReturnedRecordsCount().contains("Your report contains 0 records"),
				"Custom Exam not yet completed by the User");
		trainingManagementPage.logOut();
		Assert(loginPage.signIn(getFromStorage("learnerUser") + "usr", getData("GENERIC.PASSWORD"),
				getData("GENERIC.AUTOMATION.COMPANYCODE")).isHomePage(), "User will be able to login successfully");
		Assert(homePage.openUserProfile().openDateTimeFormat().changeTimeDisplaySettings("3|1:30:55 PM UTC-5")
				.getTimeDisplaySettings().contains("1:30:55 PM UTC-5"),
				"Precondition:User with the Preferred Date/Time format set as “h:mm:ss tt UTC-5");
		Assert(homePage.openCatalog().isCatalogPage(), "User  will be able to navigated to Catalog page");
		Assert(catalogPage.searchTrainingItem(getFromStorage("customExamTrng"))
				.trainingItemGeneralInfo(getFromStorage("customExamTrng")).getcatalogPageContent()
				.contains(getFromStorage("customExamTrng")), "The user will be able to view the course information");
		catalogPage.completeCustomExam().electronicallySign(getFromStorage("learnerUser") + "usr",
				getData("GENERIC.PASSWORD"));
		addToStorage("systemTime", Tools.getDateTime("MM/dd/yyyy hh:mm:ss a"));
		addToStorage("timeZone", Tools.getSystemTimeZone(false));
		Assert(catalogPage.openHistoryPage().getHistoryPageContent().contains(getFromStorage("customExamTrng")),"The user will be able to launch the Custom exam from Catalog.The user will be able to complete the Custom exam from Catalog.");
		Assert(historyPage.getTrainingCompletedOnDate().contains(getFromStorage("timeZone")),
				"Completion Date (Completed On) in Learner's History display date, time and offset as captured by the database in the User's Preferred Date/Time format");
		AssertTime("MM/dd/yyyy hh:mm:ss a", historyPage.getTrainingCompletedOnDate().substring(13, 36),
				getFromStorage("systemTime"),
				"Electronic signature applied at Catalog page and training completed on date/time in history page match. PC SYSTEM TIME: "
						+ getFromStorage("systemTime"));
		historyPage.openTrainingItemGeneralInfo(getFromStorage("customExamTrng")).scrollDown();
		Assert(historyPage.getCompletionInfoPageContent().contains(getFromStorage("customExamTrng")),
				"The completion information for the Custom Exam will be displayed.");
		Assert(historyPage.getCompletionDate()
				.contains(historyPage.getSignature().replace("|", "-").split("-")[1].trim())
				&& historyPage.getCompletionDate().contains(getFromStorage("timeZone")),
				"Completion Information screen will display date; time and offset as captured by the database in the User's Preferred Date/Time format");
		AssertTime("MM/dd/yyyy hh:mm:ss a", historyPage.getCompletionDate().substring(0, 22),
				getFromStorage("systemTime"),
				"Completion date/time in history completion information screen and actual date/time the training is completed will match. PC SYSTEM TIME: "
						+ getFromStorage("systemTime"));

	}

}
