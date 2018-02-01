package tests.compliancewire.basic.catalog;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;
import ui.utils.Tools;

public class TestCase20196 extends BaseTest {
	private static final Logger logger = Logger.getLogger(TestCase20196.class);

	@Test(alwaysRun = true, description = "Launch and complete a form from Catalog", groups = {})
	public void Catalog_Form_Training_Item_Launch_and_complete() throws Exception {
		addToStorage("randomTag1", getRandomEntityID().substring(0, 7));
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
		userManagementPage.returnFromUserManagement().openTrainingPage().openAddTrainingItem().openFormCourse().addForm(
				getFromStorage("randomTag1") + "Form", getFromStorage("randomTag1") + "Form", "Auto Polaris 2.0");
		Assert(trainingManagementPage.getTrainingTitle().contains(getFromStorage("randomTag1")),
				"Precondition: Form training item will be created.");
		trainingManagementPage.openAddQuestion().openQuestionWithSingleResponse()
				.addQuestionAnswer("What is your favorite color?", "Red", "Blue", "Green").openSaveQuestion()
				.openAddQuestion().openQuestionWithSingleResponse()
				.addQuestionAnswer("What is your favorite game?", "Football", "Cricket", "Golf").openSaveQuestion();
		Assert(trainingManagementPage.getPRINTTableBorder(), ("What is your favorite color?"),
				"Precondition: Form with questions");
		trainingManagementPage.logOut();
		Assert(loginPage.signIn(getFromStorage("learnerUser") + "usr", getData("GENERIC.PASSWORD"),
				getData("GENERIC.AUTOMATION.COMPANYCODE")).isHomePage(), "User will be able to login successfully");
		Assert(homePage.openUserProfile().openDateTimeFormat().changeTimeDisplaySettings("3|1:30:55 PM UTC-5")
				.getTimeDisplaySettings().contains("1:30:55 PM UTC-5"),
				"Precondition:User with the Preferred Date/Time format set as “h:mm:ss tt UTC-5");
		Assert(preferencesPage.openCatalog().isCatalogPage(), "User  will be able to navigated to Catalog page");
		Assert(catalogPage.searchTrainingItem(getFromStorage("randomTag1") + "Form")
				.trainingItemGeneralInfo(getFromStorage("randomTag1") + "Form").getcatalogPageContent().contains(
						getFromStorage("randomTag1") + "Form"),
				"The user will be able to view the course information");
		catalogPage.completeFormWithQuestions();
		addToStorage("systemTime", Tools.getDateTime("MM/dd/yyyy hh:mm:ss a"));
		addToStorage("timeZone", Tools.getSystemTimeZone(false));
		Assert(catalogPage.getcatalogPageContent().contains("Your completion of the Form has been saved"),
				"A form will be launched from the catalog .The user will be able to complete the form");
		Assert(catalogPage.recordCompletionFormWithQuestions().openHistoryPage().getHistoryPageContent().contains(
				getFromStorage("randomTag1") + "Form"), "The completion information for the form will be displayed.");
		Assert(historyPage.getTrainingCompletedOnDate().contains(getFromStorage("timeZone")),
				"Completion Date (Completed On) for Form in Learner's History will display date, time and offset as captured by the database in the User's Preferred Date/Time format ");
		AssertTime("MM/dd/yyyy hh:mm:ss a", historyPage.getTrainingCompletedOnDate().substring(13, 36),
				getFromStorage("systemTime"),
				"Form completed date/time from Catalog Page and history page will match. PC SYSTEM TIME: "
						+ getFromStorage("systemTime"));
		addToStorage("trainingCreationDateTime", historyPage.getTrainingCompletedOnDate().substring(13, 47).trim());

		historyPage.openTrainingItemGeneralInfo(getFromStorage("randomTag1") + "Form").scrollDown();

		Assert(historyPage.getCompletionDate().contains(getFromStorage("trainingCreationDateTime")),
				"Completion Date (Completed On) for Form in Learner's History will display date, time and offset as captured by the database in the User's Preferred Date/Time format ");
		AssertTime("MM/dd/yyyy hh:mm:ss a", historyPage.getCompletionDate().substring(0, 22),
				getFromStorage("systemTime"),
				"Form completed date/time from Catalog Page and history completion information screen will match. PC SYSTEM TIME: "
						+ getFromStorage("systemTime"));
	}
}
