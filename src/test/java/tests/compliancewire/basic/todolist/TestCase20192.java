package tests.compliancewire.basic.todolist;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;
import ui.utils.Tools;

public class TestCase20192 extends BaseTest {

	private static final Logger logger = Logger.getLogger(TestCase20192.class);

	@Test(alwaysRun = true, description = "Control documents can be launched and completed from To-Do list. "
			+ "The user will have to click on the provided link to Control Documents before the training can be completed.", groups = {})
	public void To_Do_list_Launch_and_complete_the_CD() throws Exception {
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
		usersPage.openOptionsPage().openEsignatureRequirements().openEditRequirements().uncheckReqEsigCtrlDoc(
				getFromStorage("adminUser"), getData("GENERIC.PASSWORD"), getData("TC19721.SIGNATUREREASON"), "RecScr");
		Assert(esignatureRequirementsPage.getEsignatureRequirements(0).contains("images/UnChecked.jpg"),
				"Precondition 2: E-signature for CD completions is DISABLED");
		addToStorage("Training1", getRandomEntityID().substring(0, 6) + "CDTrng");
		optionsPage.openTrainingPage().openAddTrainingItem().openControlDocumentCourse()
				.enterCodeTitleSelectOrganization(getFromStorage("Training1"), getFromStorage("Training1"),
						"Auto Polaris 2.0")
				.enterApprovalEffectiveDates().enterAssortedFields("1").saveControlDocument("1");
		Assert(trainingManagementPage.getTrainingTitle().contains(getFromStorage("Training1"))
				&& trainingManagementPage.getWebAddress().contains("www.google.com"),
				"Precondition : Control document with web address");
		Assert(trainingManagementPage.openQuiz().getQuizRevisionPageContent().contains(
				"There are currently no quizzes available"), "Precondition : Control document without a quiz");
		trainingManagementPage.returnFromQuizRevision().openAssignmentsPage().openAddAssignmentDefinition()
				.addAnAssignmentDefinition("0", "1", getFromStorage("Training1"), getFromStorage("learnerUser") + "usr","").continueAndCheckAssignment()
				.returnToAssignmentPage().openAssignmentReportByTraining()
				.runReportForTrainingOrCurriculum(getFromStorage("Training1"), getFromStorage("Training1"),
						getFromStorage("learnerUser") + "usr");
		Assert(assignmentManagementPage.getPRINTTableBorder().contains(getFromStorage("Training1"))
				&& assignmentManagementPage.getPRINTTableBorder().contains(getFromStorage("learnerUser") + "usr"),
				"Precondition : Assigned above Control document to above Learner");
		assignmentManagementPage.logOut();
		Assert(loginPage.signIn(getFromStorage("learnerUser") + "usr", getData("GENERIC.PASSWORD"),
				getData("GENERIC.AUTOMATION.COMPANYCODE")).isHomePage(), "Learner will be able to login successfully");
		Assert(homePage.openUserProfile().openDateTimeFormat().changeTimeDisplaySettings("3|1:30:55 PM UTC-5")
				.getTimeDisplaySettings().contains("1:30:55 PM UTC-5"),
				"Precondition:User with the Preferred Date/Time format set as “h:mm:ss tt UTC-5");
		addToStorage("windowHandle", preferencesPage.getWindowHandle());
		Assert(preferencesPage.openToDoPage().openTrainingItem(getFromStorage("Training1")).testWebAddress()
				.getTestWebAddressPageTitle().contains("Google"), "The web address will open in a new browser window");
		todoPage.closeTestWebaddressBrowser(getFromStorage("windowHandle")).completeCDWithWebAddress();
		addToStorage("systemTime", Tools.getDateTime("MM/dd/yyyy hh:mm:ss a"));
		addToStorage("timeZone", Tools.getSystemTimeZone(false));
		Assert(todoPage.getToDoPageContent().contains("Your report contains 0 records"),
				"Learner will be able to launch the control document.Learner will be able to complete the control document");
		todoPage.refreshPage();
		Assert(todoPage.openHistoryPage().getHistoryPageContent().contains(getFromStorage("Training1")),
				"Completions for the control document will be recorded in History");
		Assert(historyPage.getTrainingCompletedOnDate().contains(getFromStorage("timeZone")),
				"Completion Date (Completed On) for control document in Learner's History will display date; time and offset as captured by the database in the User's Preferred Date/Time format");
		AssertTime("MM/dd/yyyy hh:mm:ss a", historyPage.getTrainingCompletedOnDate().substring(13, 36),
				getFromStorage("systemTime"),
				"Training completed date/time from ToDo Page and history page will match. PC SYSTEM TIME: "
						+ getFromStorage("systemTime"));
		addToStorage("trainingCreationDateTime", historyPage.getTrainingCompletedOnDate().substring(13, 47).trim());

		historyPage.openTrainingItemGeneralInfo(getFromStorage("Training1")).scrollDown();

		Assert(historyPage.getCompletionDate().contains(getFromStorage("trainingCreationDateTime")),
				"Completion Date (Completed On) for control document in Completion Information screen will display date,time and offset as captured by the database in the User's Preferred Date/Time format");
		AssertTime("MM/dd/yyyy hh:mm:ss a", historyPage.getCompletionDate().substring(0, 22),
				getFromStorage("systemTime"),
				"Training completed date/time from ToDo Page and history completion information screen will match. PC SYSTEM TIME: "
						+ getFromStorage("systemTime"));

	

	}

}
