package initializer;

import org.apache.log4j.Logger;

import ui.BrowserDriver;
import ui.UiBase;
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

public class PageFactory {
	private static final Logger logger = Logger.getLogger(PageFactory.class);

	public static UiBase getPage(Class pageType, BrowserDriver driver) {

		switch (pageType.getSimpleName()) {

		case "LoginPage":
			return new LoginPage(driver);
		case "DefineAssignmentReportPage":
			return new DefineAssignmentReportPage(driver);
		case "DefineCompletionReportPage":
			return new DefineCompletionReportPage(driver);
		case "LogsManagementPage":
			return new LogsManagementPage(driver);
		case "UsersPage":
			return new UsersPage(driver);
		case "HomePage":
			return new HomePage(driver);
		case "GroupCriteriaChangeLogPage":
			return new GroupCriteriaChangeLogPage(driver);
		case "CurriculumVitaePage":
			return new CurriculumVitaePage(driver);
		case "GroupManagementPage":
			return new GroupManagementPage(driver);
		case "UserManagementPage":
			return new UserManagementPage(driver);
		case "EditGroupCriteriaPage":
			return new EditGroupCriteriaPage(driver);
		case "AddNewUserPage":
			return new AddNewUserPage(driver);
		case "QuizPage":
			return new QuizPage(driver);
		case "TrainingManagementPage":
			return new TrainingManagementPage(driver);
		case "TrainingPage":
			return new TrainingPage(driver);
		case "OptionsPage":
			return new OptionsPage(driver);
		case "DefinePasswordPoliciesPage":
			return new DefinePasswordPoliciesPage(driver);
		case "LogsPage":
			return new LogsPage(driver);
		case "DefineEventLogReportPage":
			return new DefineEventLogReportPage(driver);
		case "EventLogReportPage":
			return new EventLogReportPage(driver);
		case "SecuritySettingsPage":
			return new SecuritySettingsPage(driver);
		case "SecurityRolePage":
			return new SecurityRolePage(driver);
		case "EsignatureRequirementsPage":
			return new EsignatureRequirementsPage(driver);
		case "ChangePasswordPage":
			return new ChangePasswordPage(driver);
		case "GroupMembershipHistoryPage":
			return new GroupMembershipHistoryPage(driver);
		case "PreferencesPage":
			return new PreferencesPage(driver);
		case "ClassesPage":
			return new ClassesPage(driver);
		case "CatalogPage":
			return new CatalogPage(driver);
		case "ToDoPage":
			return new ToDoPage(driver);
		case "HistoryPage":
			return new HistoryPage(driver);
		case "AddTrainingItemPage":
			return new AddTrainingItemPage(driver);
		case "InstructorLedCoursePage":
			return new InstructorLedCoursePage(driver);
		case "AddClassPage":
			return new AddClassPage(driver);
		case "OptionsManagementPage":
			return new OptionsManagementPage(driver);
		case "AssignmentPage":
			return new AssignmentPage(driver);
		case "AssignmentManagementPage":
			return new AssignmentManagementPage(driver);
		case "AddAnAssignmentDefinitionPage":
			return new AddAnAssignmentDefinitionPage(driver);
		case "AddCurriculumPage":
			return new AddCurriculumPage(driver);
		case "CurriculumReportPage":
			return new CurriculumReportPage(driver);
		case "ViewCurriculumPage":
			return new ViewCurriculumPage(driver);
		case "CustomExamPage":
			return new CustomExamPage(driver);
		case "AddQuestionCustomExamPage":
			return new AddQuestionCustomExamPage(driver);
		case "ControlDocumentTrainingPage":
			return new ControlDocumentTrainingPage(driver);
		default:
			return new UiBase();
		}
	}
}
