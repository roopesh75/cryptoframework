package initializer;

import org.apache.log4j.Logger;

import ui.BrowserDriver;
import ui.pages.actions.AddAnAssignmentDefinitionPage;
import ui.pages.actions.AddClassPage;
import ui.pages.actions.AddCurriculumPage;
import ui.pages.actions.AddNewUserPage;
import ui.pages.actions.AddQuestionCustomExamPage;
import ui.pages.actions.AddTrainingItemPage;
import ui.pages.actions.AssignmentManagementPage;
import ui.pages.actions.AssignmentPage;
import ui.pages.actions.CatalogPage;
import ui.pages.actions.ChangePasswordPage;
import ui.pages.actions.ClassesPage;
import ui.pages.actions.ControlDocumentTrainingPage;
import ui.pages.actions.CurriculumReportPage;
import ui.pages.actions.CurriculumVitaePage;
import ui.pages.actions.CustomExamPage;
import ui.pages.actions.DefineAssignmentReportPage;
import ui.pages.actions.DefineCompletionReportPage;
import ui.pages.actions.DefineEventLogReportPage;
import ui.pages.actions.DefinePasswordPoliciesPage;
import ui.pages.actions.EditGroupCriteriaPage;
import ui.pages.actions.EsignatureRequirementsPage;
import ui.pages.actions.EventLogReportPage;
import ui.pages.actions.GroupCriteriaChangeLogPage;
import ui.pages.actions.GroupManagementPage;
import ui.pages.actions.GroupMembershipHistoryPage;
import ui.pages.actions.HistoryPage;
import ui.pages.actions.HomePage;
import ui.pages.actions.InstructorLedCoursePage;
import ui.pages.actions.LoginPage;
import ui.pages.actions.LogsManagementPage;
import ui.pages.actions.LogsPage;
import ui.pages.actions.OptionsManagementPage;
import ui.pages.actions.OptionsPage;
import ui.pages.actions.PreferencesPage;
import ui.pages.actions.QuizPage;
import ui.pages.actions.SecurityRolePage;
import ui.pages.actions.SecuritySettingsPage;
import ui.pages.actions.ToDoPage;
import ui.pages.actions.TrainingManagementPage;
import ui.pages.actions.TrainingPage;
import ui.pages.actions.UserManagementPage;
import ui.pages.actions.UsersPage;
import ui.pages.actions.ViewCurriculumPage;

public class PageInitializer extends BrowserInitializer {
	private static final Logger logger = Logger.getLogger(PageInitializer.class);
	protected UsersPage usersPage;
	protected HomePage homePage;
	protected DefineCompletionReportPage defineCompletionReportPage;
	protected LogsManagementPage logsManagementPage;
	protected GroupManagementPage groupManagementPage;
	protected GroupCriteriaChangeLogPage groupCriteriaChangeLogPage;
	protected EditGroupCriteriaPage editGroupCriteriaPage;
	protected LoginPage loginPage;
	protected AddNewUserPage addNewUserPage;
	protected UserManagementPage userManagementPage;
	protected TrainingManagementPage trainingManagementPage;
	protected TrainingPage trainingPage;
	protected OptionsPage optionsPage;
	protected LogsPage logsPage;
	protected DefinePasswordPoliciesPage definePasswordPoliciesPage;
	protected DefineEventLogReportPage defineEventLogReportPage;
	protected EventLogReportPage eventLogReportPage;
	protected SecuritySettingsPage securitySettingsPage;
	protected OptionsManagementPage optionsManagementPage;
	protected SecurityRolePage securityRolePage;
	protected EsignatureRequirementsPage esignatureRequirementsPage;
	protected ChangePasswordPage changePasswordPage;
	protected GroupMembershipHistoryPage groupMembershipHistoryPage;
	protected PreferencesPage preferencesPage;
	protected ClassesPage classesPage;
	protected CatalogPage catalogPage;
	protected ToDoPage todoPage;
	protected DefineAssignmentReportPage defineAssignmentReportPage;
	protected CurriculumVitaePage curriculumVitaePage;
	protected HistoryPage historyPage;
	protected AddTrainingItemPage addTrainingItemPage;
	protected AssignmentPage assignmentPage;
	protected AssignmentManagementPage assignmentManagementPage;
	protected AddAnAssignmentDefinitionPage addAnAssignmentDefinitionPage;
	protected InstructorLedCoursePage instructorLedCoursePage;
	protected AddClassPage addClassPage;
	protected AddCurriculumPage addCurriculumPage;
	protected CurriculumReportPage curriculumReportPage;
	protected ViewCurriculumPage viewCurriculumPage;
	protected CustomExamPage customExamPage;
	protected AddQuestionCustomExamPage addQuestionCustomExamPage;
	protected ControlDocumentTrainingPage controlDocumentTrainingPage;
	protected QuizPage quizPage;
	public static int counter;

	public void initializePages(BrowserDriver driver, Class testClass) {
		logger.info("CLASS BEING TESTED :" + testClass + " " + Thread.currentThread().getStackTrace()[1] + "ThreadID: "
				+ Thread.currentThread().getId() + "  " + Thread.currentThread().getName() + " "
				+ "Begin instantiating page objects");
		loginPage = (LoginPage) PageFactory.getPage(LoginPage.class, driver);
		assignmentManagementPage = (AssignmentManagementPage) PageFactory.getPage(AssignmentManagementPage.class,
				driver);
		addAnAssignmentDefinitionPage = (AddAnAssignmentDefinitionPage) PageFactory
				.getPage(AddAnAssignmentDefinitionPage.class, driver);
		quizPage = (QuizPage) PageFactory.getPage(QuizPage.class, driver);
		addNewUserPage = (AddNewUserPage) PageFactory.getPage(AddNewUserPage.class, driver);
		userManagementPage = (UserManagementPage) PageFactory.getPage(UserManagementPage.class, driver);
		usersPage = (UsersPage) PageFactory.getPage(UsersPage.class, driver);
		homePage = (HomePage) PageFactory.getPage(HomePage.class, driver);
		groupCriteriaChangeLogPage = (GroupCriteriaChangeLogPage) PageFactory.getPage(GroupCriteriaChangeLogPage.class,
				driver);
		groupManagementPage = (GroupManagementPage) PageFactory.getPage(GroupManagementPage.class, driver);
		editGroupCriteriaPage = (EditGroupCriteriaPage) PageFactory.getPage(EditGroupCriteriaPage.class, driver);
		trainingPage = (TrainingPage) PageFactory.getPage(TrainingPage.class, driver);
		trainingManagementPage = (TrainingManagementPage) PageFactory.getPage(TrainingManagementPage.class, driver);
		optionsPage = (OptionsPage) PageFactory.getPage(OptionsPage.class, driver);
		definePasswordPoliciesPage = (DefinePasswordPoliciesPage) PageFactory.getPage(DefinePasswordPoliciesPage.class,
				driver);
		logsPage = (LogsPage) PageFactory.getPage(LogsPage.class, driver);
		defineEventLogReportPage = (DefineEventLogReportPage) PageFactory.getPage(DefineEventLogReportPage.class,
				driver);
		eventLogReportPage = (EventLogReportPage) PageFactory.getPage(EventLogReportPage.class, driver);
		securitySettingsPage = (SecuritySettingsPage) PageFactory.getPage(SecuritySettingsPage.class, driver);
		securityRolePage = (SecurityRolePage) PageFactory.getPage(SecurityRolePage.class, driver);
		esignatureRequirementsPage = (EsignatureRequirementsPage) PageFactory.getPage(EsignatureRequirementsPage.class,
				driver);
		changePasswordPage = (ChangePasswordPage) PageFactory.getPage(ChangePasswordPage.class, driver);
		groupMembershipHistoryPage = (GroupMembershipHistoryPage) PageFactory.getPage(GroupMembershipHistoryPage.class,
				driver);
		logsManagementPage = (LogsManagementPage) PageFactory.getPage(LogsManagementPage.class, driver);
		defineCompletionReportPage = (DefineCompletionReportPage) PageFactory.getPage(DefineCompletionReportPage.class,
				driver);
		curriculumVitaePage = (CurriculumVitaePage) PageFactory.getPage(CurriculumVitaePage.class, driver);
		optionsManagementPage = (OptionsManagementPage) PageFactory.getPage(OptionsManagementPage.class, driver);
		preferencesPage = (PreferencesPage) PageFactory.getPage(PreferencesPage.class, driver);
		classesPage = (ClassesPage) PageFactory.getPage(ClassesPage.class, driver);
		catalogPage = (CatalogPage) PageFactory.getPage(CatalogPage.class, driver);
		todoPage = (ToDoPage) PageFactory.getPage(ToDoPage.class, driver);
		assignmentPage = (AssignmentPage) PageFactory.getPage(AssignmentPage.class, driver);
		defineAssignmentReportPage=(DefineAssignmentReportPage) PageFactory.getPage(DefineAssignmentReportPage.class, driver);
		historyPage = (HistoryPage) PageFactory.getPage(HistoryPage.class, driver);
		addTrainingItemPage = (AddTrainingItemPage) PageFactory.getPage(AddTrainingItemPage.class, driver);
		instructorLedCoursePage = (InstructorLedCoursePage) PageFactory.getPage(InstructorLedCoursePage.class, driver);
		addClassPage = (AddClassPage) PageFactory.getPage(AddClassPage.class, driver);
		addCurriculumPage = (AddCurriculumPage) PageFactory.getPage(AddCurriculumPage.class, driver);
		curriculumReportPage = (CurriculumReportPage) PageFactory.getPage(CurriculumReportPage.class, driver);
		viewCurriculumPage = (ViewCurriculumPage) PageFactory.getPage(ViewCurriculumPage.class, driver);
		customExamPage = (CustomExamPage) PageFactory.getPage(CustomExamPage.class, driver);
		addQuestionCustomExamPage = (AddQuestionCustomExamPage) PageFactory.getPage(AddQuestionCustomExamPage.class,
				driver);
		controlDocumentTrainingPage = (ControlDocumentTrainingPage) PageFactory
				.getPage(ControlDocumentTrainingPage.class, driver);
		logger.info("ThreadID: " + Thread.currentThread().getId() + "  " + Thread.currentThread().getName() + " "
				+ "Done instantiating page objects");
	}
}
